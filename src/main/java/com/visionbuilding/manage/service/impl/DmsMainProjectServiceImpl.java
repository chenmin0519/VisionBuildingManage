package com.visionbuilding.manage.service.impl;

import com.visionbuilding.manage.dao.mapper.DmsAmountLogMapper;
import com.visionbuilding.manage.dao.mapper.DmsChildProjectMapper;
import com.visionbuilding.manage.dao.mapper.DmsMainProjectMapper;
import com.visionbuilding.manage.dao.mapper.DmsSettlementMapper;
import com.visionbuilding.manage.modle.ResultPOListBean;
import com.visionbuilding.manage.modle.entity.DmsAmountLog;
import com.visionbuilding.manage.modle.entity.DmsChildProject;
import com.visionbuilding.manage.modle.entity.DmsMainProject;
import com.visionbuilding.manage.modle.entity.DmsSettlement;
import com.visionbuilding.manage.modle.query.QueryBean;
import com.visionbuilding.manage.service.DmsMainProjectService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class DmsMainProjectServiceImpl implements DmsMainProjectService {

    @Autowired
    private DmsMainProjectMapper dmsMainProjectMapper;

    @Autowired
    private DmsChildProjectMapper dmsChildProjectMapper;

    @Autowired
    private DmsSettlementMapper dmsSettlementMapper;

    @Autowired
    private DmsAmountLogMapper dmsAmountLogMapper;

    @Override
    public DmsMainProject selectByPrimaryKey(Long id) {
        return dmsMainProjectMapper.selectByPrimaryKey(id);
    }

    @Override
    public void deleteByPrimaryKey(Long id) {
        // 1.查询该主项目包含的子项目
        List<DmsChildProject> list = dmsChildProjectMapper.queryAllSub(id);
        // 2.判断如果里面有子项目的状态是审核已通过(2),就不能删除
        for(DmsChildProject dmsChildProject : list) {
            if(dmsChildProject.getConfirmStatus() != null && dmsChildProject.getConfirmStatus() == 2) {
                throw new RuntimeException("有子项目审核已通过,该项目不能被删除!");
            }
        }
        // 3.删除主项目
        dmsMainProjectMapper.deleteByPrimaryKey(id);
        // 4.删除所有主项目包含的子项目
        for(DmsChildProject dmsChildProject : list) {
            if(dmsChildProject.getConfirmStatus() != null) {
                dmsChildProjectMapper.deleteByPrimaryKey(dmsChildProject.getId());
            }
        }
    }

    @Override
    public void insertSelective(DmsMainProject dmsMainProject) throws ParseException {
        //1.获取客户来源的名称
        // 2.获取客户来源名称的简写
        String jx = dmsMainProject.getCustomerCode()+"-";
        // 3.获取当天(年月日.yyyyMMdd格式)
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String dateStr = sdf.format(date);
        // 获取当前年的字符串
        SimpleDateFormat customerSdf = new SimpleDateFormat("yyyy");
        String customerDate = customerSdf.format(date);

        // 4.把客户来源简称和年月日拼在一起(如 : zc-20200420),根据这个数据获取数据库(主项目表)这个开头的所有数据,
        // 根据id倒序,取第一个,然后截取最后四位(就是编号),把最后一位加1,判断最后一位如果是9,就进10,最后两位是99,就进100,以此类推.
        String newCode = jx + dateStr + "-"; //项目编号(又名 项目建档时间)
        String newCustomerCode = jx + customerDate + "-"; //客户编码
        DmsMainProject dmsMainProjectTemp = new DmsMainProject();
        dmsMainProjectTemp.setProjectCreationTime(newCode);
        // 5.模糊查询,前缀等于 data的,去主项目表,查projectCreationTime字段
        dmsMainProjectTemp = dmsMainProjectMapper.getMaxCode(dmsMainProjectTemp);
        if(dmsMainProjectTemp == null) { //说明当天该客户还未创建主项目,编号从0001开始
            newCode = newCode + "0001";
        } else {
            // 获取最后四位
            String projectCreationTime = dmsMainProjectTemp.getProjectCreationTime();
            String oldCode = projectCreationTime.substring(projectCreationTime.length()-4);
            int oldCodeNum = Integer.parseInt(oldCode);
            // 当前设定,主项目编号只取四位,一个客户当天最多可以有创建9999个主项目
            int newCodeNum = oldCodeNum + 1;
            if(newCodeNum < 10) {
                newCode = newCode + "000" + newCodeNum;
            } else if(newCodeNum < 100) {
                newCode = newCode + "00" + newCodeNum;
            } else if(newCodeNum < 1000) {
                newCode = newCode + "0" + newCodeNum;
            } else {
                newCode = newCode + newCodeNum;
            }
        }
        dmsMainProject.setProjectCreationTime(newCode); //项目编号

        // 6.生成客户编码
        dmsMainProjectTemp = new DmsMainProject();
        dmsMainProjectTemp.setCustomerCode(newCustomerCode);
        dmsMainProjectTemp = dmsMainProjectMapper.getMaxCode(dmsMainProjectTemp);
        if(dmsMainProjectTemp == null) { //说明当天该客户还未创建主项目,编号从0001开始
            newCustomerCode = newCustomerCode + "0001";
        } else {
            // 获取最后四位
            String customerCode = dmsMainProjectTemp.getCustomerCode();
            String oldCode = customerCode.substring(customerCode.length()-4);
            int oldCodeNum = Integer.parseInt(oldCode);
            // 当前设定,主项目编号只取四位,一个客户当天最多可以有创建9999个主项目
            int newCodeNum = oldCodeNum + 1;
            if(newCodeNum < 10) {
                newCustomerCode = newCustomerCode + "000" + newCodeNum;
            } else if(newCodeNum < 100) {
                newCustomerCode = newCustomerCode + "00" + newCodeNum;
            } else if(newCodeNum < 1000) {
                newCustomerCode = newCustomerCode + "0" + newCodeNum;
            } else {
                newCustomerCode = newCustomerCode + newCodeNum;
            }
        }

        dmsMainProject.setCustomerCode(newCustomerCode);
        dmsMainProjectMapper.insertSelective(dmsMainProject);

        DmsAmountLog dmsAmountLog = new DmsAmountLog();
        dmsAmountLog.setProjectId(dmsMainProject.getId());
        dmsAmountLog.setAmountReceivable(dmsMainProject.getAmountReceivable());
        dmsAmountLog.setAmountReturned(dmsMainProject.getAmountReturned());
        dmsAmountLog.setAmountsPayable(dmsMainProject.getAmountsPayable());
        dmsAmountLog.setAmountSpent(dmsMainProject.getAmountSpent());
        LocalDate today = LocalDate.now();
        LocalDate kssj = today.with(TemporalAdjusters.firstDayOfMonth());
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        SimpleDateFormat sdfs = new SimpleDateFormat("yyyy-MM-dd");
        dmsAmountLog.setEventDate(sdfs.parse(kssj.format(dtf)));
        dmsAmountLogMapper.insertOrUpdata(dmsAmountLog);
    }

    @Override
    public void updateByPrimaryKeySelective(DmsMainProject dmsMainProject) throws ParseException {
        dmsMainProjectMapper.updateByPrimaryKeySelective(dmsMainProject);
        DmsAmountLog dmsAmountLog = new DmsAmountLog();
        dmsAmountLog.setProjectId(dmsMainProject.getId());
        dmsAmountLog.setAmountReceivable(dmsMainProject.getAmountReceivable());
        dmsAmountLog.setAmountReturned(dmsMainProject.getAmountReturned());
        dmsAmountLog.setAmountsPayable(dmsMainProject.getAmountsPayable());
        dmsAmountLog.setAmountSpent(dmsMainProject.getAmountSpent());
        LocalDate today = LocalDate.now();
        LocalDate kssj = today.with(TemporalAdjusters.firstDayOfMonth());
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        SimpleDateFormat sdfs = new SimpleDateFormat("yyyy-MM-dd");
        dmsAmountLog.setEventDate(sdfs.parse(kssj.format(dtf)));
        dmsAmountLogMapper.insertOrUpdata(dmsAmountLog);
    }

    @Override
    public ResultPOListBean<DmsMainProject> queryPage(DmsMainProject dmsMainProject) {
        ResultPOListBean<DmsMainProject> result = new ResultPOListBean<>();
        //分页参数
        QueryBean queryBean = new QueryBean();
        queryBean.setPageNo(dmsMainProject.getPageNo());
        queryBean.setPageRows(dmsMainProject.getPageRows());
        queryBean.setF(dmsMainProject.getPagingMap());

        int count = 0;
        count = dmsMainProjectMapper.queryPageCount(queryBean);
        queryBean.resetTotalCount(count);
        List<DmsMainProject> dmsMainProjects = new ArrayList<>();
        if(count > 0){
            dmsMainProjects = dmsMainProjectMapper.queryPage(queryBean);
        }
        result.success(dmsMainProjects,count);
        //分页信息
        BeanUtils.copyProperties(queryBean, result);
        return result;
    }

    @Override
    public void insertSelectiveChild(DmsChildProject dmsChildProject) {
        // 1.获取parentId,根据获取parentId去查子项目表,所有parentId为该parentId的数据的list
        List<DmsChildProject> list = dmsChildProjectMapper.querySubProjectList(dmsChildProject);

        // 2.拿到当前数据的项目类型code,如果在前面查出来的list里面已经有了,就不能存
        for(DmsChildProject dmsChildProject1 : list) {
            if(dmsChildProject1.getProjectTypeCode() != null && dmsChildProject.getProjectTypeCode() != null
                    && dmsChildProject.getProjectTypeCode().equals(dmsChildProject1.getProjectTypeCode())) {

                throw new RuntimeException("子项目类型已存在,请勿重复添加!");
            }
        }

        // 3.插入数据
        dmsChildProjectMapper.insertSelective(dmsChildProject);
    }

    @Override
    public void updateByPrimaryKeySelectiveChild(DmsChildProject dmsChildProject) {
        // 1.获取parentId,根据获取parentId去查子项目表,所有parentId为该parentId的数据的list
        List<DmsChildProject> list = dmsChildProjectMapper.querySubProjectList(dmsChildProject);

        // 2.拿到当前数据的项目类型code,如果在前面查出来的list里面已经有了(并且不是当前修改的这条数据),就不能存
        for(DmsChildProject dmsChildProject1 : list) {
            if(dmsChildProject1.getProjectTypeCode() != null && dmsChildProject.getProjectTypeCode() != null
                    && dmsChildProject.getProjectTypeCode().equals(dmsChildProject1.getProjectTypeCode())
                    && !dmsChildProject.getId().equals(dmsChildProject1.getId())) {

                throw new RuntimeException("子项目类型已存在,请勿重复添加!");
            }
        }

        dmsChildProjectMapper.updateByPrimaryKeySelective(dmsChildProject);
    }

    @Override
    public ResultPOListBean<DmsChildProject> querySubPage(DmsChildProject dmsChildProject) {
        ResultPOListBean<DmsChildProject> result = new ResultPOListBean<>();
        //分页参数
        QueryBean queryBean = new QueryBean();
        queryBean.setPageNo(dmsChildProject.getPageNo());
        queryBean.setPageRows(dmsChildProject.getPageRows());
        queryBean.setF(dmsChildProject.getPagingMap());

        int count = 0;
        count = dmsChildProjectMapper.queryPageCount(queryBean);
        queryBean.resetTotalCount(count);
        List<DmsChildProject> dmsChildProjects = new ArrayList<>();
        if(count > 0){
            dmsChildProjects = dmsChildProjectMapper.querySubPage(queryBean);
        }
        result.success(dmsChildProjects,count);
        //分页信息
        BeanUtils.copyProperties(queryBean, result);
        return result;
    }

    @Override
    public DmsChildProject selectSubByPrimaryKey(Long id) {
        return dmsChildProjectMapper.selectByPrimaryKey(id);
    }

    @Override
    public void auditSubProject(Long id) {
        // 1.根据子项目id获取到该子项目
        DmsChildProject dmsChildProject = dmsChildProjectMapper.selectByPrimaryKey(id);
        if(dmsChildProject == null) {
          throw new RuntimeException("未找到该子项目!");
        }
        // 拿到要保存的时间(确认时间)
        Date date = new Date();
        // 2.根据parentId查出大项目
        Long parentId = dmsChildProject.getParentId();
        DmsMainProject dmsMainProject = dmsMainProjectMapper.selectByPrimaryKey(parentId);

        // 3.拿到小项目和大项目的销售总价,成本总价, 然后累加
        Long smallTotalSalesPrice = Optional.ofNullable(dmsChildProject.getTotalSalesPrice()).orElse(0l); //小项目销售总价
        Long bigTotalSalesPrice = Optional.ofNullable(dmsMainProject.getDesignSalesCost()).orElse(0l); //大项目销售总价(设计销售费用)
        bigTotalSalesPrice += smallTotalSalesPrice;
        dmsMainProject.setDesignSalesCost(bigTotalSalesPrice);

        Long smallTotalCost = Optional.ofNullable(dmsChildProject.getTotalCost()).orElse(0l); //小项目成本总价
        Long bigTotalCost = Optional.ofNullable(dmsMainProject.getDesignCost()).orElse(0l); //大项目成本总价(设计成本费用)
        bigTotalCost += smallTotalCost;
        dmsMainProject.setDesignCost(bigTotalCost);

        // 4.计算利润.利润 = 设计销售费用-设计成本费用
        Long profit = Optional.ofNullable(dmsMainProject.getProfit()).orElse(0l);
        profit = profit + bigTotalSalesPrice - bigTotalCost;
        dmsMainProject.setProfit(profit);

        // 5.存进大项目数据库
        dmsMainProjectMapper.updateMoneyById(dmsMainProject);
        // 6.状态设为审核通过,存进子项目数据库
        dmsChildProject.setConfirmStatus((byte) 2);
        dmsChildProject.setConfirmTime(date);
        dmsChildProjectMapper.updateStatusById(dmsChildProject);
        // 7.把统计到的数据存进统计表
        DmsSettlement dmsSettlement = new DmsSettlement();
        dmsSettlement.setParentProject(parentId);
        dmsSettlement.setChildProject(id);
        dmsSettlement.setSaleAmount(smallTotalSalesPrice);
        dmsSettlement.setCostAmount(smallTotalCost);
        dmsSettlement.setCommissionAmount(dmsChildProject.getDesignerCommission());
        dmsSettlement.setUserId(dmsChildProject.getUserId());
        dmsSettlement.setEventDate(date);

        dmsSettlementMapper.insertSelective(dmsSettlement);
    }

    @Override
    public void noAuditSubProject(Long id) {
        // 1.根据子项目id获取到该子项目
        DmsChildProject dmsChildProject = dmsChildProjectMapper.selectByPrimaryKey(id);
        if(dmsChildProject == null) {
            throw new RuntimeException("未找到该子项目!");
        }
        dmsChildProject.setConfirmStatus((byte) 0);
        dmsChildProjectMapper.updateStatusById(dmsChildProject);
    }

    @Override
    public void deleteSubByPrimaryKey(Long id) {
        // 如果子项目已经被确认了就不能被删除
        // 1.根据id去子项目表查询,如果状态是2(已确认),就不能删除
        DmsChildProject dmsChildProject = dmsChildProjectMapper.selectByPrimaryKey(id);
        if(dmsChildProject == null) {
            throw new RuntimeException("该子项目不存在!");
        }
        if(dmsChildProject.getConfirmStatus() != null && dmsChildProject.getConfirmStatus() == 2) {
            throw new RuntimeException("该子项目已审核通过,不能删除!");
        }
        dmsChildProjectMapper.deleteByPrimaryKey(id);
    }

    @Override
    public Long sumAreaByUserTime(Long uid, Date startTime, Date endTime,String projectCode) {
        return dmsChildProjectMapper.sumAreaByUserTime(uid,projectCode,startTime,endTime);
    }

}
