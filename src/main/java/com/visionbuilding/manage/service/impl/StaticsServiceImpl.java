package com.visionbuilding.manage.service.impl;

import com.visionbuilding.manage.dao.mapper.DmsDepartmentMapper;
import com.visionbuilding.manage.dao.mapper.StaticsMapper;
import com.visionbuilding.manage.modle.ResultPOListBean;
import com.visionbuilding.manage.modle.entity.DmsDepartment;
import com.visionbuilding.manage.modle.po.DepartmentStaticsPo;
import com.visionbuilding.manage.modle.po.ExportDepartmentExclePo;
import com.visionbuilding.manage.modle.po.ExportExclePo;
import com.visionbuilding.manage.modle.query.QueryBean;
import com.visionbuilding.manage.modle.query.StaticsQuery;
import com.visionbuilding.manage.service.StaticsService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class StaticsServiceImpl implements StaticsService {
    @Autowired
    private StaticsMapper staticsMapper;

    @Autowired
    private DmsDepartmentMapper dmsDepartmentMapper;

    @Override
    public ResultPOListBean<DepartmentStaticsPo> queryPage(StaticsQuery par) throws Exception {
        ResultPOListBean<DepartmentStaticsPo> result = new ResultPOListBean<>();
        //分页参数
        QueryBean queryBean = new QueryBean();
        queryBean.setPageNo(par.getPageNo());
        queryBean.setPageRows(par.getPageRows());
        queryBean.setF(par.getPagingMap());
        int count = 0;
        count = staticsMapper.querycount(queryBean);
        queryBean.resetTotalCount(count);
        List<DepartmentStaticsPo> users = new ArrayList<>();
        if(count > 0){
            users = staticsMapper.queryList(queryBean);
        }
        result.setValue(users);
        //分页信息
        BeanUtils.copyProperties(queryBean, result);
        return result;
    }

    @Override
    public List<ExportDepartmentExclePo> getData(StaticsQuery department) {
        List<DmsDepartment> departments = dmsDepartmentMapper.getAll();
        Map<Long,String> departmentMap = new HashMap<>();
        for(DmsDepartment po : departments){
            departmentMap.put(po.getId(),po.getDepartment());
        }
        List<ExportDepartmentExclePo> result = new ArrayList<>();
        List<DepartmentStaticsPo>  list = staticsMapper.queryListByParam(department);
        if(list != null){
            Map<Long,ExportDepartmentExclePo> f = new TreeMap<>();
            for(DepartmentStaticsPo po : list){
                ExportDepartmentExclePo exclePo = null;
                if(f.get(po.getParentId()) != null){
                    exclePo = f.get(po.getParentId());
                    initData(exclePo,po,departmentMap);
                }else{
                    exclePo = new ExportDepartmentExclePo();
                    exclePo.setProjectCode(po.getProjectCode());
                    exclePo.setCustomerSource(po.getCustomerSource());
                    exclePo.setCustomerCode(po.getCustomerCode());
                    exclePo.setPlace(po.getPlace());
                    initData(exclePo,po,departmentMap);
                }
                f.put(po.getParentId(),exclePo);
            }
            f.forEach((key,value)->{
                result.add(value);
            });
        }
        return result;
    }

    /**
     * 初始数据
     * @param exclePo
     * @param po
     */
    private void initData(ExportDepartmentExclePo exclePo, DepartmentStaticsPo po, Map<Long,String> departmentMap) {
        if("001".equals(po.getTypeCode())){
            //平面
            exclePo.setArea1(po.getConstructionArea());
            exclePo.setAcquisitionDate1(po.getAcquisitionDate());
            exclePo.setInitialDeliveryDate1(po.getInitialDeliveryDate());
            exclePo.setTotalCost1(po.getChildTotalCost());
            exclePo.setDesignerCommission1(po.getDesignerCommission());
            exclePo.setDepartment1(departmentMap.get(po.getDepartment()));
            exclePo.setDesigner1(po.getDesigner());
        }else if("002".equals(po.getTypeCode())){
            //基础6
            exclePo.setArea6(po.getConstructionArea());
            exclePo.setAcquisitionDate6(po.getAcquisitionDate());
            exclePo.setInitialDeliveryDate6(po.getInitialDeliveryDate());
            exclePo.setTotalCost6(po.getChildTotalCost());
            exclePo.setDesignerCommission6(po.getDesignerCommission());
            exclePo.setDepartment6(departmentMap.get(po.getDepartment()));
            exclePo.setDesigner6(po.getDesigner());
        }else if("003".equals(po.getTypeCode())){
            //立面5
            exclePo.setArea5(po.getConstructionArea());
            exclePo.setAcquisitionDate5(po.getAcquisitionDate());
            exclePo.setInitialDeliveryDate5(po.getInitialDeliveryDate());
            exclePo.setTotalCost5(po.getChildTotalCost());
            exclePo.setDesignerCommission5(po.getDesignerCommission());
            exclePo.setDepartment5(departmentMap.get(po.getDepartment()));
            exclePo.setDesigner5(po.getDesigner());
        }
        else if("004".equals(po.getTypeCode())){
            //结构4
            exclePo.setArea4(po.getConstructionArea());
            exclePo.setAcquisitionDate4(po.getAcquisitionDate());
            exclePo.setInitialDeliveryDate4(po.getInitialDeliveryDate());
            exclePo.setTotalCost4(po.getChildTotalCost());
            exclePo.setDesignerCommission4(po.getDesignerCommission());
            exclePo.setDepartment4(departmentMap.get(po.getDepartment()));
            exclePo.setDesigner4(po.getDesigner());
        }
        else if("005".equals(po.getTypeCode())){
            //效果图3
            exclePo.setArea3(po.getConstructionArea());
            exclePo.setAcquisitionDate3(po.getAcquisitionDate());
            exclePo.setInitialDeliveryDate3(po.getInitialDeliveryDate());
            exclePo.setTotalCost3(po.getChildTotalCost());
            exclePo.setDesignerCommission3(po.getDesignerCommission());
            exclePo.setDepartment3(departmentMap.get(po.getDepartment()));
            exclePo.setDesigner3(po.getDesigner());
        }
        else if("006".equals(po.getTypeCode())){
            //水电2
            exclePo.setArea2(po.getConstructionArea());
            exclePo.setAcquisitionDate2(po.getAcquisitionDate());
            exclePo.setInitialDeliveryDate2(po.getInitialDeliveryDate());
            exclePo.setTotalCost2(po.getChildTotalCost());
            exclePo.setDesignerCommission2(po.getDesignerCommission());
            exclePo.setDepartment2(departmentMap.get(po.getDepartment()));
            exclePo.setDesigner2(po.getDesigner());
        }
        else if("007".equals(po.getTypeCode())){
            //预算
            exclePo.setArea(po.getConstructionArea());
            exclePo.setAcquisitionDate(po.getAcquisitionDate());
            exclePo.setInitialDeliveryDate(po.getInitialDeliveryDate());
            exclePo.setTotalCost(po.getChildTotalCost());
            exclePo.setDesignerCommission(po.getDesignerCommission());
            exclePo.setDepartment(departmentMap.get(po.getDepartment()));
            exclePo.setDesigner(po.getDesigner());
        }
        else if("008".equals(po.getTypeCode())){
            //其他
        }
    }

    @Override
    public List<ExportExclePo> getAllData(StaticsQuery department) {
        List<DmsDepartment> departments = dmsDepartmentMapper.getAll();
        Map<Long,String> departmentMap = new HashMap<>();
        for(DmsDepartment po : departments){
            departmentMap.put(po.getId(),po.getDepartment());
        }
        List<ExportExclePo> result = new ArrayList<>();
        List<DepartmentStaticsPo>  list = staticsMapper.queryListByParam(department);
        if(list != null){
            Map<Long,ExportExclePo> f = new TreeMap<>();
            for(DepartmentStaticsPo po : list){
                ExportExclePo exclePo = null;
                if(f.get(po.getParentId()) != null){
                    exclePo = f.get(po.getParentId());
                    initAllData(exclePo,po,departmentMap);
                }else{
                    exclePo = new ExportExclePo();
                    exclePo.setProjectCode(po.getProjectCode());
                    exclePo.setCustomerSource(po.getCustomerSource());
                    exclePo.setCustomerCode(po.getCustomerCode());
                    exclePo.setPlace(po.getPlace());
                    exclePo.setSalesArea(po.getSalesArea());
                    exclePo.setAmountReturned(po.getAmountReturned());
                    exclePo.setAmountReceivable(po.getAmountReceivable());
                    exclePo.setAmountSpent(po.getAmountSpent());
                    exclePo.setAmountsPayable(po.getAmountsPayable());
                    exclePo.setContractNo(po.getContractNo());
                    exclePo.setRemarks(po.getRemarks());
                    initAllData(exclePo,po,departmentMap);
                }
                f.put(po.getParentId(),exclePo);
            }
            f.forEach((key,value)->{
                result.add(value);
            });
        }
        return result;
    }

    private void initAllData(ExportExclePo exclePo, DepartmentStaticsPo po, Map<Long, String> departmentMap) {
        exclePo.setDesignSalesCost(Optional.ofNullable(exclePo.getDesignSalesCost()).orElse(0l)+po.getTotalSalesPrice());//销售费用
        exclePo.setDesignCost(Optional.ofNullable(exclePo.getDesignCost()).orElse(0l)+po.getChildTotalCost());//成本
        exclePo.setProfit(exclePo.getDesignSalesCost() - exclePo.getDesignCost());//利润
        if("001".equals(po.getTypeCode())){
            //平面
            exclePo.setArea1(po.getConstructionArea());
            exclePo.setAcquisitionDate1(po.getAcquisitionDate());
            exclePo.setInitialDeliveryDate1(po.getInitialDeliveryDate());
            exclePo.setTotalCost1(po.getChildTotalCost());
            exclePo.setDesignerCommission1(po.getDesignerCommission());
            exclePo.setDepartment1(departmentMap.get(po.getDepartment()));
            exclePo.setDesigner1(po.getDesigner());
        }else if("002".equals(po.getTypeCode())){
            //基础6
            exclePo.setArea6(po.getConstructionArea());
            exclePo.setAcquisitionDate6(po.getAcquisitionDate());
            exclePo.setInitialDeliveryDate6(po.getInitialDeliveryDate());
            exclePo.setTotalCost6(po.getChildTotalCost());
            exclePo.setDesignerCommission6(po.getDesignerCommission());
            exclePo.setDepartment6(departmentMap.get(po.getDepartment()));
            exclePo.setDesigner6(po.getDesigner());
        }else if("003".equals(po.getTypeCode())){
            //立面5
            exclePo.setArea5(po.getConstructionArea());
            exclePo.setAcquisitionDate5(po.getAcquisitionDate());
            exclePo.setInitialDeliveryDate5(po.getInitialDeliveryDate());
            exclePo.setTotalCost5(po.getChildTotalCost());
            exclePo.setDesignerCommission5(po.getDesignerCommission());
            exclePo.setDepartment5(departmentMap.get(po.getDepartment()));
            exclePo.setDesigner5(po.getDesigner());
        }else if("004".equals(po.getTypeCode())){
            //结构4
            exclePo.setArea4(po.getConstructionArea());
            exclePo.setAcquisitionDate4(po.getAcquisitionDate());
            exclePo.setInitialDeliveryDate4(po.getInitialDeliveryDate());
            exclePo.setTotalCost4(po.getChildTotalCost());
            exclePo.setDesignerCommission4(po.getDesignerCommission());
            exclePo.setDepartment4(departmentMap.get(po.getDepartment()));
            exclePo.setDesigner4(po.getDesigner());
        }else if("005".equals(po.getTypeCode())){
            //效果图3
            exclePo.setArea3(po.getConstructionArea());
            exclePo.setAcquisitionDate3(po.getAcquisitionDate());
            exclePo.setInitialDeliveryDate3(po.getInitialDeliveryDate());
            exclePo.setTotalCost3(po.getChildTotalCost());
            exclePo.setDesignerCommission3(po.getDesignerCommission());
            exclePo.setDepartment3(departmentMap.get(po.getDepartment()));
            exclePo.setDesigner3(po.getDesigner());
        }else if("006".equals(po.getTypeCode())){
            //水电2
            exclePo.setArea2(po.getConstructionArea());
            exclePo.setAcquisitionDate2(po.getAcquisitionDate());
            exclePo.setInitialDeliveryDate2(po.getInitialDeliveryDate());
            exclePo.setTotalCost2(po.getChildTotalCost());
            exclePo.setDesignerCommission2(po.getDesignerCommission());
            exclePo.setDepartment2(departmentMap.get(po.getDepartment()));
            exclePo.setDesigner2(po.getDesigner());
        } else if("007".equals(po.getTypeCode())){
            //预算
            exclePo.setArea(po.getConstructionArea());
            exclePo.setAcquisitionDate(po.getAcquisitionDate());
            exclePo.setInitialDeliveryDate(po.getInitialDeliveryDate());
            exclePo.setTotalCost(po.getChildTotalCost());
            exclePo.setDesignerCommission(po.getDesignerCommission());
            exclePo.setDepartment(departmentMap.get(po.getDepartment()));
            exclePo.setDesigner(po.getDesigner());
        }else if("008".equals(po.getTypeCode())){
            //其他
        }
    }
}
