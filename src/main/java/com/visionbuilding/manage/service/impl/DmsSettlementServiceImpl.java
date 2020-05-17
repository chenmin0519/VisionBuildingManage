package com.visionbuilding.manage.service.impl;

import com.visionbuilding.manage.dao.mapper.DmsMainProjectMapper;
import com.visionbuilding.manage.dao.mapper.DmsSettlementMapper;
import com.visionbuilding.manage.modle.ResultPOListBean;
import com.visionbuilding.manage.modle.entity.DmsMainProject;
import com.visionbuilding.manage.modle.entity.DmsSettlement;
import com.visionbuilding.manage.modle.po.*;
import com.visionbuilding.manage.modle.query.DepartmentEchartQuery;
import com.visionbuilding.manage.modle.query.QueryBean;
import com.visionbuilding.manage.service.DmsSettlementService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class DmsSettlementServiceImpl implements DmsSettlementService {

    @Autowired
    private DmsSettlementMapper dmsSettlementMapper;

    @Autowired
    private DmsMainProjectMapper dmsMainProjectMapper;

    @Override
    public EchartPo getUserEchartInfo(Date startTime, Date endTime,Long department) {
        EchartPo echartPo = new EchartPo();
        List<StaticsUserPo> dmsSettlements = dmsSettlementMapper.getUserEchartInfo(startTime,endTime,department);
        //表数据变量
        List<String> dataStr = new ArrayList<>();
        List<Long> saleData = new ArrayList<>();
        List<Long> costData = new ArrayList<>();
        List<Long> receivableData = new ArrayList<>();
        List<Long> returnedData = new ArrayList<>();
        List<Long> commistionData = new ArrayList<>();
        List<EchartImageDataPo> echartImageDataPos = new ArrayList<>();
        //坐标参数需要变量
        Long leftYMin = 0L;
        Long rigthYMin = 0L;
        Long leftMax = 0L;
        Long rigthMax = 0L;
        for(StaticsUserPo staticsMouthsPo : dmsSettlements){
            staticsMouthsPo.setTotalCost(staticsMouthsPo.getTotalCost()/100);
            staticsMouthsPo.setBonus(staticsMouthsPo.getBonus()/100);
            staticsMouthsPo.setFine(staticsMouthsPo.getFine()/100);
            staticsMouthsPo.setWages(staticsMouthsPo.getWages()/100);
            staticsMouthsPo.setPayWages(staticsMouthsPo.getPayWages()/100);
            staticsMouthsPo.setDesignerCommission(staticsMouthsPo.getDesignerCommission()/100);
            staticsMouthsPo.setProfit(staticsMouthsPo.getProfit()/100);
            dataStr.add(staticsMouthsPo.getDesigner());
            saleData.add(staticsMouthsPo.getTotalCost());
            echartPo.setAllSale(Optional.ofNullable(echartPo.getAllSale()).orElse(0l) + staticsMouthsPo.getTotalCost());//总销售额
            costData.add(staticsMouthsPo.getPayWages());
            echartPo.setAllCost(Optional.ofNullable(echartPo.getAllCost()).orElse(0l) + staticsMouthsPo.getPayWages());//总chengben
            commistionData.add(staticsMouthsPo.getProfit());
            leftYMin = getLeftMin(leftYMin,staticsMouthsPo.getTotalCost(),staticsMouthsPo.getPayWages());
            if(rigthYMin == 0 || (staticsMouthsPo.getTotalCost()-staticsMouthsPo.getPayWages()) < rigthYMin){
                rigthYMin = staticsMouthsPo.getTotalCost()-staticsMouthsPo.getPayWages();
            }
            leftMax = getLeftMax(leftMax,staticsMouthsPo.getTotalCost(),staticsMouthsPo.getPayWages());
            if(rigthMax == 0 || (staticsMouthsPo.getTotalCost()-staticsMouthsPo.getPayWages()) > rigthMax){
                rigthMax = staticsMouthsPo.getTotalCost()-staticsMouthsPo.getPayWages();
            }
        }
        //初始化表数据
        EchartImageDataPo echartImageDataPo = new EchartImageDataPo();
        echartImageDataPo.setName("部门成本");
        echartImageDataPo.setType("bar");
        echartImageDataPo.setData(saleData);
        echartImageDataPos.add(echartImageDataPo);
        echartImageDataPo = new EchartImageDataPo();
        echartImageDataPo.setName("设计工资");
        echartImageDataPo.setType("bar");
        echartImageDataPo.setData(costData);
        echartImageDataPos.add(echartImageDataPo);
        echartImageDataPo = new EchartImageDataPo();
        echartImageDataPo.setName("利润");
        echartImageDataPo.setType("bar");
        echartImageDataPo.setYAxisIndex(1);
        echartImageDataPo.setData(commistionData);
        echartImageDataPos.add(echartImageDataPo);
        echartPo.setSeries(echartImageDataPos);
        //下标数据
        echartPo.setDataStr(dataStr);
        //XY坐标数据
        echartPo.setLeftYMin(getDonwInteger(leftYMin));
        echartPo.setLeftMax(getUpInteger(leftMax));
        echartPo.setRigthMax(getUpInteger(rigthMax));
        echartPo.setRigthYMin(getDonwInteger(rigthYMin));
        echartPo.setLeftInterval((echartPo.getLeftMax()-echartPo.getLeftYMin())/5);
        echartPo.setRightInterval((echartPo.getRigthMax()-echartPo.getRigthYMin())/5);
        return echartPo;
    }

    @Override
    public ResultPOListBean<StaticsUserPo> getUserEchartInfoPage(DepartmentEchartQuery dmsProjectType) {
        ResultPOListBean<StaticsUserPo> result = new ResultPOListBean<>();
        //分页参数
        QueryBean queryBean = new QueryBean();
        queryBean.setPageNo(dmsProjectType.getPageNo());
        queryBean.setPageRows(dmsProjectType.getPageRows());
        queryBean.setF(dmsProjectType.getPagingMap());

        int count = 0;
        count = dmsSettlementMapper.getUserEchartInfoPageCount(queryBean);
        queryBean.resetTotalCount(count);
        List<StaticsUserPo> departments = new ArrayList<>();
        if(count > 0){
            departments = dmsSettlementMapper.getUserEchartInfoPage(queryBean);
        }
        result.success(departments,count);
        //分页信息
        BeanUtils.copyProperties(queryBean, result);
        return result;
    }

    @Override
    public ResultPOListBean<StaticsBusinessPo> getdepartmentEchartInfoPage(DepartmentEchartQuery dmsProjectType) {
        ResultPOListBean<StaticsBusinessPo> result = new ResultPOListBean<>();
        //分页参数
        QueryBean queryBean = new QueryBean();
        queryBean.setPageNo(dmsProjectType.getPageNo());
        queryBean.setPageRows(dmsProjectType.getPageRows());
        queryBean.setF(dmsProjectType.getPagingMap());

        int count = 0;
        count = dmsSettlementMapper.getdepartmentEchartInfoPageCount(queryBean);
        queryBean.resetTotalCount(count);
        List<StaticsBusinessPo> departments = new ArrayList<>();
        if(count > 0){
            departments = dmsSettlementMapper.getdepartmentEchartInfoPage(queryBean);
        }
        result.success(departments,count);
        //分页信息
        BeanUtils.copyProperties(queryBean, result);
        return result;
    }

    @Override
    public EchartPo getdepartmentEchartInfo(Date startTime, Date endTime) {
        EchartPo echartPo = new EchartPo();
        List<StaticsBusinessPo> dmsSettlements = dmsSettlementMapper.getdepartmentEchartInfo(startTime,endTime);
        //表数据变量
        List<String> dataStr = new ArrayList<>();
        List<Long> saleData = new ArrayList<>();
        List<Long> costData = new ArrayList<>();
        List<Long> receivableData = new ArrayList<>();
        List<Long> returnedData = new ArrayList<>();
        List<Long> commistionData = new ArrayList<>();
        List<EchartImageDataPo> echartImageDataPos = new ArrayList<>();
        //坐标参数需要变量
        Long leftYMin = 0L;
        Long rigthYMin = 0L;
        Long leftMax = 0L;
        Long rigthMax = 0L;
        for(StaticsBusinessPo staticsMouthsPo : dmsSettlements){
            staticsMouthsPo.setAllCost(staticsMouthsPo.getAllCost()/100);
            staticsMouthsPo.setTotalCost(staticsMouthsPo.getTotalCost()/100);
            staticsMouthsPo.setProfit(staticsMouthsPo.getAllCost() - staticsMouthsPo.getTotalCost());
            dataStr.add(staticsMouthsPo.getDepartmentName());
            saleData.add(staticsMouthsPo.getAllCost());
            echartPo.setAllSale(Optional.ofNullable(echartPo.getAllSale()).orElse(0l) + staticsMouthsPo.getAllCost());//总销售额
            costData.add(staticsMouthsPo.getTotalCost());
            echartPo.setAllCost(Optional.ofNullable(echartPo.getAllCost()).orElse(0l) + staticsMouthsPo.getTotalCost());//总销售额
            commistionData.add(staticsMouthsPo.getAllCost() - staticsMouthsPo.getTotalCost());
            leftYMin = getLeftMin(leftYMin,staticsMouthsPo.getAllCost(),staticsMouthsPo.getTotalCost());
            if(rigthYMin == 0 || (staticsMouthsPo.getAllCost()-staticsMouthsPo.getTotalCost()) < rigthYMin){
                rigthYMin = staticsMouthsPo.getAllCost()-staticsMouthsPo.getTotalCost();
            }
            leftMax = getLeftMax(leftMax,staticsMouthsPo.getAllCost(),staticsMouthsPo.getTotalCost());
            if(rigthMax == 0 || (staticsMouthsPo.getAllCost()-staticsMouthsPo.getTotalCost()) > rigthMax){
                rigthMax = staticsMouthsPo.getAllCost()-staticsMouthsPo.getTotalCost();
            }
        }
        //初始化表数据
        EchartImageDataPo echartImageDataPo = new EchartImageDataPo();
        echartImageDataPo.setName("设计院拨款");
        echartImageDataPo.setType("bar");
        echartImageDataPo.setData(saleData);
        echartImageDataPos.add(echartImageDataPo);
        echartImageDataPo = new EchartImageDataPo();
        echartImageDataPo.setName("设计成本");
        echartImageDataPo.setType("bar");
        echartImageDataPo.setData(costData);
        echartImageDataPos.add(echartImageDataPo);
        echartImageDataPo = new EchartImageDataPo();
        echartImageDataPo.setName("利润");
        echartImageDataPo.setType("bar");
        echartImageDataPo.setYAxisIndex(1);
        echartImageDataPo.setData(commistionData);
        echartImageDataPos.add(echartImageDataPo);
        echartPo.setSeries(echartImageDataPos);
        //下标数据
        echartPo.setDataStr(dataStr);
        //XY坐标数据
        echartPo.setLeftYMin(getDonwInteger(leftYMin));
        echartPo.setLeftMax(getUpInteger(leftMax));
        echartPo.setRigthMax(getUpInteger(rigthMax));
        echartPo.setRigthYMin(getDonwInteger(rigthYMin));
        echartPo.setLeftInterval((echartPo.getLeftMax()-echartPo.getLeftYMin())/5);
        echartPo.setRightInterval((echartPo.getRigthMax()-echartPo.getRigthYMin())/5);
        return echartPo;
    }


    @Override
    public ResultPOListBean<StaticsMouthsPo> getBusinessEchartInfoPage(DmsMainProject dmsProjectType) {
        ResultPOListBean<StaticsMouthsPo> result = new ResultPOListBean<>();
        //分页参数
        QueryBean queryBean = new QueryBean();
        queryBean.setPageNo(dmsProjectType.getPageNo());
        queryBean.setPageRows(dmsProjectType.getPageRows());
        queryBean.setF(dmsProjectType.getPagingMap());

        int count = 0;
        count = dmsMainProjectMapper.getBusinessEchartInfoPageCount(queryBean);
        queryBean.resetTotalCount(count);
        List<StaticsMouthsPo> departments = new ArrayList<>();
        if(count > 0){
            departments = dmsMainProjectMapper.getBusinessEchartInfoPage(queryBean);
        }
        result.success(departments,count);
        //分页信息
        BeanUtils.copyProperties(queryBean, result);
        return result;
    }

    @Override
    public EchartPo getBusinessEchartInfo(DmsMainProject dmsProjectType) {
        EchartPo echartPo = new EchartPo();
        List<StaticsMouthsPo> dmsSettlements = dmsMainProjectMapper.getBusinessEchartInfo(dmsProjectType.getProjectCreationTime());
        //表数据变量
        List<String> dataStr = new ArrayList<>();
        List<Long> saleData = new ArrayList<>();
        List<Long> costData = new ArrayList<>();
        List<Long> receivableData = new ArrayList<>();
        List<Long> returnedData = new ArrayList<>();
        List<Long> commistionData = new ArrayList<>();
        List<EchartImageDataPo> echartImageDataPos = new ArrayList<>();
        //坐标参数需要变量
        Long leftYMin = 0L;
        Long rigthYMin = 0L;
        Long leftMax = 0L;
        Long rigthMax = 0L;
        for(StaticsMouthsPo staticsMouthsPo : dmsSettlements){
            staticsMouthsPo.setSaleAmount(staticsMouthsPo.getSaleAmount()/100);
            staticsMouthsPo.setCommissionAmount(staticsMouthsPo.getCommissionAmount()/100);
            staticsMouthsPo.setCostAmount(staticsMouthsPo.getCostAmount()/100);
            dataStr.add(staticsMouthsPo.getEventDate());
            saleData.add(staticsMouthsPo.getSaleAmount());
            echartPo.setAllSale(Optional.ofNullable(echartPo.getAllSale()).orElse(0l) + staticsMouthsPo.getSaleAmount());//总销售额
            costData.add(staticsMouthsPo.getCostAmount());
            echartPo.setAllCost(Optional.ofNullable(echartPo.getAllCost()).orElse(0l) + staticsMouthsPo.getCostAmount());//总销售额
            receivableData.add(staticsMouthsPo.getAmountReceivable()/100);
            returnedData.add(staticsMouthsPo.getAmountReturned()/100);
            commistionData.add(staticsMouthsPo.getSaleAmount()-staticsMouthsPo.getCostAmount());
            leftYMin = getLeftMin(leftYMin,staticsMouthsPo.getSaleAmount(),staticsMouthsPo.getCostAmount());
            if(rigthYMin == 0 || (staticsMouthsPo.getSaleAmount()-staticsMouthsPo.getCostAmount()) < rigthYMin){
                rigthYMin = staticsMouthsPo.getSaleAmount()-staticsMouthsPo.getCostAmount();
            }
            leftMax = getLeftMax(leftMax,staticsMouthsPo.getSaleAmount(),staticsMouthsPo.getCostAmount());
            if(rigthMax == 0 || (staticsMouthsPo.getSaleAmount()-staticsMouthsPo.getCostAmount()) > rigthMax){
                rigthMax = staticsMouthsPo.getSaleAmount()-staticsMouthsPo.getCostAmount();
            }
        }
        //初始化表数据
        EchartImageDataPo echartImageDataPo = new EchartImageDataPo();
        echartImageDataPo.setName("销售额");
        echartImageDataPo.setType("bar");
        echartImageDataPo.setData(saleData);
        echartImageDataPos.add(echartImageDataPo);
        echartImageDataPo = new EchartImageDataPo();
        echartImageDataPo.setName("设计成本");
        echartImageDataPo.setType("bar");
        echartImageDataPo.setData(costData);
        echartImageDataPos.add(echartImageDataPo);
        echartImageDataPo = new EchartImageDataPo();
        echartImageDataPo.setName("应收款");
        echartImageDataPo.setType("bar");
        echartImageDataPo.setData(receivableData);
        echartImageDataPos.add(echartImageDataPo);
        echartImageDataPo = new EchartImageDataPo();
        echartImageDataPo.setName("已回款");
        echartImageDataPo.setType("bar");
        echartImageDataPo.setData(returnedData);
        echartImageDataPos.add(echartImageDataPo);
        echartImageDataPo = new EchartImageDataPo();
        echartImageDataPo.setName("利润");
        echartImageDataPo.setType("bar");
        echartImageDataPo.setYAxisIndex(1);
        echartImageDataPo.setData(commistionData);
        echartImageDataPos.add(echartImageDataPo);
        echartPo.setSeries(echartImageDataPos);
        //下标数据
        echartPo.setDataStr(dataStr);
        //XY坐标数据
        echartPo.setLeftYMin(getDonwInteger(leftYMin));
        echartPo.setLeftMax(getUpInteger(leftMax));
        echartPo.setRigthMax(getUpInteger(rigthMax));
        echartPo.setRigthYMin(getDonwInteger(rigthYMin));
        echartPo.setLeftInterval((echartPo.getLeftMax()-echartPo.getLeftYMin())/5);
        echartPo.setRightInterval((echartPo.getRigthMax()-echartPo.getRigthYMin())/5);
        return echartPo;
    }

    @Override
    public EchartPo getEchartInfoBussiness() {
        EchartPo echartPo = new EchartPo();
        LocalDate endTime = LocalDate.now();
        LocalDate startTime = LocalDate.now().minusMonths(12);
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM");
        List<StaticsMouthsPo> dmsSettlements = dmsMainProjectMapper.selectOnYearData(startTime.format(dtf)+"-31",endTime.format(dtf)+"-31");
        //表数据变量
        List<String> dataStr = new ArrayList<>();
        List<Long> saleData = new ArrayList<>();
        List<Long> costData = new ArrayList<>();
        List<Long> receivableData = new ArrayList<>();
        List<Long> returnedData = new ArrayList<>();
        List<Long> commistionData = new ArrayList<>();
        List<EchartImageDataPo> echartImageDataPos = new ArrayList<>();
        //坐标参数需要变量
        Long leftYMin = 0L;
        Long rigthYMin = 0L;
        Long leftMax = 0L;
        Long rigthMax = 0L;
        for(StaticsMouthsPo staticsMouthsPo : dmsSettlements){
            staticsMouthsPo.setSaleAmount(staticsMouthsPo.getSaleAmount()/100);
            staticsMouthsPo.setCommissionAmount(staticsMouthsPo.getCommissionAmount()/100);
            staticsMouthsPo.setCostAmount(staticsMouthsPo.getCostAmount()/100);
            dataStr.add(staticsMouthsPo.getEventDate());
            saleData.add(staticsMouthsPo.getSaleAmount());
            echartPo.setAllSale(Optional.ofNullable(echartPo.getAllSale()).orElse(0l) + staticsMouthsPo.getSaleAmount());//总销售额
            costData.add(staticsMouthsPo.getCostAmount());
            echartPo.setAllCost(Optional.ofNullable(echartPo.getAllCost()).orElse(0l) + staticsMouthsPo.getCostAmount());//总成本
            receivableData.add(staticsMouthsPo.getAmountReceivable()/100);
            returnedData.add(staticsMouthsPo.getAmountReturned()/100);
            commistionData.add(staticsMouthsPo.getSaleAmount()-staticsMouthsPo.getCostAmount());
            leftYMin = getLeftMin(leftYMin,staticsMouthsPo.getSaleAmount(),staticsMouthsPo.getCostAmount());
            if(rigthYMin == 0 || (staticsMouthsPo.getSaleAmount()-staticsMouthsPo.getCostAmount()) < rigthYMin){
                rigthYMin = staticsMouthsPo.getSaleAmount()-staticsMouthsPo.getCostAmount();
            }
            leftMax = getLeftMax(leftMax,staticsMouthsPo.getSaleAmount(),staticsMouthsPo.getCostAmount());
            if(rigthMax == 0 || (staticsMouthsPo.getSaleAmount()-staticsMouthsPo.getCostAmount()) > rigthMax){
                rigthMax = staticsMouthsPo.getSaleAmount()-staticsMouthsPo.getCostAmount();
            }
        }
        //初始化表数据
        EchartImageDataPo echartImageDataPo = new EchartImageDataPo();
        echartImageDataPo.setName("销售额");
        echartImageDataPo.setType("bar");
        echartImageDataPo.setData(saleData);
        echartImageDataPos.add(echartImageDataPo);
        echartImageDataPo = new EchartImageDataPo();
        echartImageDataPo.setName("设计成本");
        echartImageDataPo.setType("bar");
        echartImageDataPo.setData(costData);
        echartImageDataPos.add(echartImageDataPo);
        echartImageDataPo = new EchartImageDataPo();
        echartImageDataPo.setName("应收款");
        echartImageDataPo.setType("bar");
        echartImageDataPo.setData(receivableData);
        echartImageDataPos.add(echartImageDataPo);
        echartImageDataPo = new EchartImageDataPo();
        echartImageDataPo.setName("已回款");
        echartImageDataPo.setType("bar");
        echartImageDataPo.setData(returnedData);
        echartImageDataPos.add(echartImageDataPo);
        echartImageDataPo = new EchartImageDataPo();
        echartImageDataPo.setName("利润");
        echartImageDataPo.setType("line");
        echartImageDataPo.setYAxisIndex(1);
        echartImageDataPo.setData(commistionData);
        echartImageDataPos.add(echartImageDataPo);
        echartPo.setSeries(echartImageDataPos);
        //下标数据
        echartPo.setDataStr(dataStr);
        //XY坐标数据
        echartPo.setLeftYMin(getDonwInteger(leftYMin));
        echartPo.setLeftMax(getUpInteger(leftMax));
        echartPo.setRigthMax(getUpInteger(rigthMax));
        echartPo.setRigthYMin(getDonwInteger(rigthYMin));
        echartPo.setLeftInterval((echartPo.getLeftMax()-echartPo.getLeftYMin())/5);
        echartPo.setRightInterval((echartPo.getRigthMax()-echartPo.getRigthYMin())/5);
        return echartPo;
    }

    @Override
    public EchartPo getEchartInfo() {
        EchartPo echartPo = new EchartPo();
        LocalDate endTime = LocalDate.now();
        LocalDate startTime = LocalDate.now().minusMonths(12);
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM");
        List<StaticsMouthsPo> dmsSettlements = dmsSettlementMapper.selectOnYearData(startTime.format(dtf)+"-31",endTime.format(dtf)+"-31");
        //表数据变量
        List<String> dataStr = new ArrayList<>();
        List<Long> saleData = new ArrayList<>();
        List<Long> costData = new ArrayList<>();
        List<Long> commistionData = new ArrayList<>();
        List<EchartImageDataPo> echartImageDataPos = new ArrayList<>();
        //坐标参数需要变量
        Long leftYMin = 0L;
        Long rigthYMin = 0L;
        Long leftMax = 0L;
        Long rigthMax = 0L;
        for(StaticsMouthsPo staticsMouthsPo : dmsSettlements){
            staticsMouthsPo.setSaleAmount(staticsMouthsPo.getSaleAmount()/100);
            staticsMouthsPo.setCommissionAmount(staticsMouthsPo.getCommissionAmount()/100);
            staticsMouthsPo.setCostAmount(staticsMouthsPo.getCostAmount()/100);
            dataStr.add(staticsMouthsPo.getEventDate());
            saleData.add(staticsMouthsPo.getSaleAmount());
            costData.add(staticsMouthsPo.getCostAmount());
            commistionData.add(staticsMouthsPo.getSaleAmount()-staticsMouthsPo.getCostAmount());
            leftYMin = getLeftMin(leftYMin,staticsMouthsPo.getSaleAmount(),staticsMouthsPo.getCostAmount());
            if(rigthYMin == 0 || (staticsMouthsPo.getSaleAmount()-staticsMouthsPo.getCostAmount()) < rigthYMin){
                rigthYMin = staticsMouthsPo.getSaleAmount()-staticsMouthsPo.getCostAmount();
            }
            leftMax = getLeftMax(leftMax,staticsMouthsPo.getSaleAmount(),staticsMouthsPo.getCostAmount());
            if(rigthMax == 0 || (staticsMouthsPo.getSaleAmount()-staticsMouthsPo.getCostAmount()) > rigthMax){
                rigthMax = staticsMouthsPo.getSaleAmount()-staticsMouthsPo.getCostAmount();
            }
        }
        //初始化表数据
        EchartImageDataPo echartImageDataPo = new EchartImageDataPo();
        echartImageDataPo.setName("销售额");
        echartImageDataPo.setType("bar");
        echartImageDataPo.setData(saleData);
        echartImageDataPos.add(echartImageDataPo);
        echartImageDataPo = new EchartImageDataPo();
        echartImageDataPo.setName("设计成本");
        echartImageDataPo.setType("bar");
        echartImageDataPo.setData(costData);
        echartImageDataPos.add(echartImageDataPo);
        echartImageDataPo = new EchartImageDataPo();
        echartImageDataPo.setName("利润");
        echartImageDataPo.setType("line");
        echartImageDataPo.setYAxisIndex(1);
        echartImageDataPo.setData(commistionData);
        echartImageDataPos.add(echartImageDataPo);
        echartPo.setSeries(echartImageDataPos);
        //下标数据
        echartPo.setDataStr(dataStr);
        //XY坐标数据
        echartPo.setLeftYMin(getDonwInteger(leftYMin));
        echartPo.setLeftMax(getUpInteger(leftMax));
        echartPo.setRigthMax(getUpInteger(rigthMax));
        echartPo.setRigthYMin(getDonwInteger(rigthYMin));
        echartPo.setLeftInterval((echartPo.getLeftMax()-echartPo.getLeftYMin())/5);
        echartPo.setRightInterval((echartPo.getRigthMax()-echartPo.getRigthYMin())/5);
        return echartPo;
    }

    /**
     * 做坐标最大值
     * @param leftMax
     * @param saleAmount
     * @param costAmount
     * @return
     */
    private Long getLeftMax(Long leftMax, Long saleAmount, Long costAmount) {
        if(leftMax != 0){
            if(leftMax > saleAmount){
                if(leftMax > costAmount){
                    return leftMax;
                }else{
                    return costAmount;
                }
            }else {
                if(saleAmount > costAmount){
                    return saleAmount;
                }else{
                    return costAmount;
                }
            }
        }else{
            if(saleAmount > costAmount){
                return saleAmount;
            }else{
                return costAmount;
            }
        }
    }

    /**
     * 获取左坐标最小值
     * @param leftYMin
     * @param saleAmount
     * @param costAmount
     * @return
     */
    private Long getLeftMin(Long leftYMin, Long saleAmount, Long costAmount) {
        if(leftYMin != 0){
            if(leftYMin < saleAmount){
                if(leftYMin < costAmount){
                    return leftYMin;
                }else{
                    return costAmount;
                }
            }else {
                if(saleAmount < costAmount){
                    return saleAmount;
                }else{
                    return costAmount;
                }
            }
        }else{
            if(saleAmount < costAmount){
                return saleAmount;
            }else{
                return costAmount;
            }
        }
    }


    /**
     * 向下取整
     * @param num
     * @return
     */
    private Long getDonwInteger(Long num){
        long i = 10l;
        long temp = 0l;
        while(num/i != 0){
            temp = num / i;
            i = i * 10;
        }
        if(temp == 0){
            return 10l;
        }else{
            return temp * i/10;
        }
    }

    /**
     * 向上去整
     * @param num
     * @return
     */
    private Long getUpInteger(Long num){
        long i = 10l;
        long temp = 0l;
        while(num/i != 0){
            temp = num / i;
            i = i * 10;
        }
        if(temp == 0){
            return 10l;
        }else{
            return (temp+1) * i/10;
        }
    }
}
