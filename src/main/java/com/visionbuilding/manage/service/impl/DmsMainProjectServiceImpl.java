package com.visionbuilding.manage.service.impl;

import com.visionbuilding.manage.dao.mapper.DmsChildProjectMapper;
import com.visionbuilding.manage.dao.mapper.DmsMainProjectMapper;
import com.visionbuilding.manage.modle.ResultPOListBean;
import com.visionbuilding.manage.modle.entity.DmsChildProject;
import com.visionbuilding.manage.modle.entity.DmsMainProject;
import com.visionbuilding.manage.modle.query.QueryBean;
import com.visionbuilding.manage.service.DmsMainProjectService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class DmsMainProjectServiceImpl implements DmsMainProjectService {

    @Autowired
    private DmsMainProjectMapper dmsMainProjectMapper;

    @Autowired
    private DmsChildProjectMapper dmsChildProjectMapper;

    @Override
    public DmsMainProject selectByPrimaryKey(Long id) {
        return dmsMainProjectMapper.selectByPrimaryKey(id);
    }

    @Override
    public void deleteByPrimaryKey(Long id) {
        dmsMainProjectMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void insertSelective(DmsMainProject dmsMainProject) {
        //1.获取客户来源的名称
        dmsMainProject.setCustomerSource("郑灿大傻逼");
        // 2.获取客户来源名称的简写
        String jx = "ZC-";
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
    }

    @Override
    public void updateByPrimaryKeySelective(DmsMainProject dmsMainProject) {
        dmsMainProjectMapper.updateByPrimaryKeySelective(dmsMainProject);
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
//        dmsMainProject.setCustomerSource("郑灿大傻逼");
////        LocalDateTime.now().
//        Date date = new Date();
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//        String dateStr = sdf.format(date);
//        dmsMainProject.setProjectCreationTime(date);
//        dmsMainProject.setCustomerCode("ZC-202004-002");
        dmsChildProjectMapper.insertSelective(dmsChildProject);
    }

    @Override
    public void updateByPrimaryKeySelectiveChild(DmsChildProject dmsChildProject) {
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

}
