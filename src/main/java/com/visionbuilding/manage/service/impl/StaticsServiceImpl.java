package com.visionbuilding.manage.service.impl;

import com.visionbuilding.manage.dao.mapper.DmsDepartmentMapper;
import com.visionbuilding.manage.dao.mapper.DmsMainProjectMapper;
import com.visionbuilding.manage.dao.mapper.DmsProjectTypeMapper;
import com.visionbuilding.manage.dao.mapper.StaticsMapper;
import com.visionbuilding.manage.modle.ResultPOListBean;
import com.visionbuilding.manage.modle.entity.DmsDepartment;
import com.visionbuilding.manage.modle.entity.DmsMainProject;
import com.visionbuilding.manage.modle.entity.DmsProjectType;
import com.visionbuilding.manage.modle.po.DepartmentStaticsPo;
import com.visionbuilding.manage.modle.po.ExportDepartmentExclePo;
import com.visionbuilding.manage.modle.po.ExportExclePo;
import com.visionbuilding.manage.modle.query.QueryBean;
import com.visionbuilding.manage.modle.query.StaticsQuery;
import com.visionbuilding.manage.service.StaticsService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class StaticsServiceImpl implements StaticsService {
    @Autowired
    private StaticsMapper staticsMapper;

    @Autowired
    private DmsDepartmentMapper dmsDepartmentMapper;

    @Autowired
    private DmsMainProjectMapper dmsMainProjectMapper;

    @Autowired
    private DmsProjectTypeMapper dmsProjectTypeMapper;

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
    public Map<String,Object> getData(StaticsQuery department,Integer role) {
        Map<String,Object> result = new HashMap<>();
        List<Map<String,String>> resultList = new ArrayList<>();
        //查询出所有部门
        List<DmsDepartment> departments = dmsDepartmentMapper.getAll();
        Map<Long,String> departmentMap = new HashMap<>();
        for(DmsDepartment po : departments){
            departmentMap.put(po.getId(),po.getDepartment());
        }
        //excle列名
        List<String> names = new ArrayList<>();
        names.add("序号");
        names.add("业务初始建档时间");
        names.add("客户来源");
        names.add("客户编码");
        names.add("项目地点");
        if(role == 1){
            names.add("销售面积");
        }
        //查出所有类型
        Map<String,DmsProjectType> typeMap = new HashMap<>();
        List<DmsProjectType> types = dmsProjectTypeMapper.getAll();
        if(types != null){
            for(DmsProjectType type : types){
                typeMap.put(type.getTypeCode(),type);
                if("效果图".equals(type.getTypeName())){
                    names.add(type.getTypeName() + "张数");
                }else {
                    names.add(type.getTypeName() + "面积");
                }
            }
            for(DmsProjectType type : types){
                typeMap.put(type.getTypeCode(),type);
                names.add(type.getTypeName() + "收单时间");
                names.add(type.getTypeName() + "交付时间");
                names.add(type.getTypeName() + "成本");
                names.add(type.getTypeName() + "设计师提成");
                names.add(type.getTypeName() + "部门");
                names.add(type.getTypeName() + "设计师");
            }
        }
        if(role == 1){
            names.add("设计销售费用");
            names.add("设计成本费用");
            names.add("利润");
            names.add("已回款金额");
            names.add("应收金额");
            names.add("已支出金额");
            names.add("应付金额");
            names.add("合同号");
            names.add("备注");
        }
        //查出统计数据
        List<DepartmentStaticsPo>  list = staticsMapper.queryListByParam(department);
        if(list != null){
            //组装导出excel数据
            Map<Long,Map<String,String>> datas = new HashMap<>();
            for(DepartmentStaticsPo po : list){
                Map<String,String> exclePo = null;
                if(datas.get(po.getParentId()) != null){
                    exclePo = datas.get(po.getParentId());
                    initData(exclePo,po,departmentMap,typeMap,role);
                }else{
                    exclePo = new HashMap<>();
                    exclePo.put("业务初始建档时间",po.getProjectCode());
                    exclePo.put("客户来源",po.getCustomerSource());
                    exclePo.put("客户编码",po.getCustomerCode());
                    exclePo.put("项目地点",po.getPlace());
                    if(role == 1){
                        if(po.getAmountReturned() != null) {
                            exclePo.put("销售面积", String.format("%.2f", po.getAmountReturned().doubleValue() / 100));
                        }
                        if(po.getAmountReceivable() != null) {
                            exclePo.put("已回款金额",String.format("%.2f",po.getAmountReceivable().doubleValue()/100));
                        }
                        if(po.getAmountSpent() != null) {
                            exclePo.put("应收金额",String.format("%.2f",po.getAmountSpent().doubleValue()/100));
                        }
                        if(po.getAmountsPayable() != null) {
                            exclePo.put("已支出金额",String.format("%.2f",po.getAmountsPayable().doubleValue()/100));
                        }
                        if(po.getAmountsPayable() != null) {
                            exclePo.put("应付金额",String.format("%.2f",po.getAmountsPayable().doubleValue()/100));
                        }
                        exclePo.put("合同号",po.getContractNo());
                        exclePo.put("备注",po.getRemarks());
                    }
                    initData(exclePo,po,departmentMap,typeMap,role);
                }
                datas.put(po.getParentId(),exclePo);
            }
            int i = 1;
            for(Long key : datas.keySet()){
                datas.get(key).put("序号",i+"");
                if(datas.get(key).get("设计销售费用") != null) {
                    Long salaCost = Long.parseLong(datas.get(key).get("设计销售费用"));
                    datas.get(key).put("设计销售费用", String.format("%.2f", salaCost.doubleValue() / 100));
                }
                if(datas.get(key).get("设计成本费用") != null) {
                    Long chengben = Long.parseLong(datas.get(key).get("设计成本费用"));
                    datas.get(key).put("设计成本费用", String.format("%.2f", chengben.doubleValue() / 100));
                }
                if(datas.get(key).get("利润") != null) {
                    Long lirun = Long.parseLong(datas.get(key).get("利润"));
                    datas.get(key).put("利润", String.format("%.2f", lirun.doubleValue() / 100));
                }
                i++;
                resultList.add(datas.get(key));
            }
        }
        result.put("resultList",resultList);
        result.put("names",names);
        return result;
    }

    /**
     * 初始数据
     * @param exclePo
     * @param po
     */
    private void initData(Map<String,String> exclePo, DepartmentStaticsPo po, Map<Long,String> departmentMap,
                          Map<String,DmsProjectType> typeMap,Integer type) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String name = typeMap.get(po.getTypeCode()).getTypeName();
        if(type == 1){
            Long xiaoshou = Long.parseLong(Optional.ofNullable(exclePo.get("设计销售费用")).orElse("0"))+Optional.ofNullable(po.getTotalSalesPrice()).orElse(0l);
            exclePo.put("设计销售费用",xiaoshou.toString());
            Long chengben = Long.parseLong(Optional.ofNullable(exclePo.get("设计成本费用")).orElse("0"))+Optional.ofNullable(po.getChildTotalCost()).orElse(0l);
            exclePo.put("设计成本费用",chengben.toString());
            Long lirun = xiaoshou-chengben;
            exclePo.put("利润",lirun.toString());
        }
        if(po.getConstructionArea() != null){
            if("效果图".equals(name)){
                exclePo.put(name+"张数",String.format("%.2f",po.getConstructionArea().doubleValue()/100));
            }else {
                exclePo.put(name+"面积",String.format("%.2f",po.getConstructionArea().doubleValue()/100));
            }
        }
        if(po.getAcquisitionDate() != null){
            exclePo.put(name+"收单时间",sdf.format(po.getAcquisitionDate()));
        }
        if(po.getInitialDeliveryDate() != null){
            exclePo.put(name+"交付时间",sdf.format(po.getInitialDeliveryDate()));
        }
        if(po.getChildTotalCost() != null){
            exclePo.put(name+"成本",String.format("%.2f",po.getChildTotalCost().doubleValue()/100));
        }
        if(po.getDesignerCommission() != null){
            exclePo.put(name+"设计师提成",String.format("%.2f",po.getDesignerCommission().doubleValue()/100));
        }
        if(po.getDepartment() != null){
            exclePo.put(name+"部门",departmentMap.get(po.getDepartment()));
        }
        if(po.getDesigner() != null){
            exclePo.put(name+"设计师",po.getDesigner());
        }
    }

//    @Override
//    public List<ExportExclePo> getAllData(StaticsQuery department) {
//        List<DmsDepartment> departments = dmsDepartmentMapper.getAll();
//        Map<Long,String> departmentMap = new HashMap<>();
//        for(DmsDepartment po : departments){
//            departmentMap.put(po.getId(),po.getDepartment());
//        }
//        List<ExportExclePo> result = new ArrayList<>();
//        List<DepartmentStaticsPo>  list = staticsMapper.queryListByParam(department);
//        if(list != null){
//            Map<Long,ExportExclePo> f = new TreeMap<>();
//            for(DepartmentStaticsPo po : list){
//                ExportExclePo exclePo = null;
//                if(f.get(po.getParentId()) != null){
//                    exclePo = f.get(po.getParentId());
//                    initAllData(exclePo,po,departmentMap);
//                }else{
//                    exclePo = new ExportExclePo();
//                    exclePo.setProjectCode(po.getProjectCode());
//                    exclePo.setCustomerSource(po.getCustomerSource());
//                    exclePo.setCustomerCode(po.getCustomerCode());
//                    exclePo.setPlace(po.getPlace());
//                    exclePo.setSalesArea(po.getSalesArea());
//                    exclePo.setAmountReturned(po.getAmountReturned());
//                    exclePo.setAmountReceivable(po.getAmountReceivable());
//                    exclePo.setAmountSpent(po.getAmountSpent());
//                    exclePo.setAmountsPayable(po.getAmountsPayable());
//                    exclePo.setContractNo(po.getContractNo());
//                    exclePo.setRemarks(po.getRemarks());
//                    initAllData(exclePo,po,departmentMap);
//                }
//                f.put(po.getParentId(),exclePo);
//            }
//            int i = 1;
//            for(Long key : f.keySet()){
//                f.get(key).setXh(i);
//                i++;
//                result.add(f.get(key));
//            }
//        }
//        return result;
//    }
//
//    private void initAllData(ExportExclePo exclePo, DepartmentStaticsPo po, Map<Long, String> departmentMap) {
//        exclePo.setDesignSalesCost(Optional.ofNullable(exclePo.getDesignSalesCost()).orElse(0l)+
//                Optional.ofNullable(po.getTotalSalesPrice()).orElse(0l));//销售费用
//        exclePo.setDesignCost(Optional.ofNullable(exclePo.getDesignCost()).orElse(0l)+
//                Optional.ofNullable(po.getChildTotalCost()).orElse(0l));//成本
//        exclePo.setProfit(exclePo.getDesignSalesCost() - exclePo.getDesignCost());//利润
//        if("001".equals(po.getTypeCode())){
//            //平面
//            exclePo.setTotalSalesPrice1(po.getTotalSalesPrice());
//            exclePo.setArea1(po.getConstructionArea());
//            exclePo.setAcquisitionDate1(po.getAcquisitionDate());
//            exclePo.setInitialDeliveryDate1(po.getInitialDeliveryDate());
//            exclePo.setTotalCost1(po.getChildTotalCost());
//            exclePo.setDesignerCommission1(po.getDesignerCommission());
//            exclePo.setDepartment1(departmentMap.get(po.getDepartment()));
//            exclePo.setDesigner1(po.getDesigner());
//        }else if("002".equals(po.getTypeCode())){
//            //基础6
//            exclePo.setTotalSalesPrice6(po.getTotalSalesPrice());
//            exclePo.setArea6(po.getConstructionArea());
//            exclePo.setAcquisitionDate6(po.getAcquisitionDate());
//            exclePo.setInitialDeliveryDate6(po.getInitialDeliveryDate());
//            exclePo.setTotalCost6(po.getChildTotalCost());
//            exclePo.setDesignerCommission6(po.getDesignerCommission());
//            exclePo.setDepartment6(departmentMap.get(po.getDepartment()));
//            exclePo.setDesigner6(po.getDesigner());
//        }else if("003".equals(po.getTypeCode())){
//            //立面5
//            exclePo.setTotalSalesPrice5(po.getTotalSalesPrice());
//            exclePo.setArea5(po.getConstructionArea());
//            exclePo.setAcquisitionDate5(po.getAcquisitionDate());
//            exclePo.setInitialDeliveryDate5(po.getInitialDeliveryDate());
//            exclePo.setTotalCost5(po.getChildTotalCost());
//            exclePo.setDesignerCommission5(po.getDesignerCommission());
//            exclePo.setDepartment5(departmentMap.get(po.getDepartment()));
//            exclePo.setDesigner5(po.getDesigner());
//        }else if("004".equals(po.getTypeCode())){
//            //结构4
//            exclePo.setTotalSalesPrice4(po.getTotalSalesPrice());
//            exclePo.setArea4(po.getConstructionArea());
//            exclePo.setAcquisitionDate4(po.getAcquisitionDate());
//            exclePo.setInitialDeliveryDate4(po.getInitialDeliveryDate());
//            exclePo.setTotalCost4(po.getChildTotalCost());
//            exclePo.setDesignerCommission4(po.getDesignerCommission());
//            exclePo.setDepartment4(departmentMap.get(po.getDepartment()));
//            exclePo.setDesigner4(po.getDesigner());
//        }else if("005".equals(po.getTypeCode())){
//            //效果图3
//            exclePo.setTotalSalesPrice3(po.getTotalSalesPrice());
//            exclePo.setArea3(po.getConstructionArea());
//            exclePo.setAcquisitionDate3(po.getAcquisitionDate());
//            exclePo.setInitialDeliveryDate3(po.getInitialDeliveryDate());
//            exclePo.setTotalCost3(po.getChildTotalCost());
//            exclePo.setDesignerCommission3(po.getDesignerCommission());
//            exclePo.setDepartment3(departmentMap.get(po.getDepartment()));
//            exclePo.setDesigner3(po.getDesigner());
//        }else if("006".equals(po.getTypeCode())){
//            //水电2
//            exclePo.setTotalSalesPrice2(po.getTotalSalesPrice());
//            exclePo.setArea2(po.getConstructionArea());
//            exclePo.setAcquisitionDate2(po.getAcquisitionDate());
//            exclePo.setInitialDeliveryDate2(po.getInitialDeliveryDate());
//            exclePo.setTotalCost2(po.getChildTotalCost());
//            exclePo.setDesignerCommission2(po.getDesignerCommission());
//            exclePo.setDepartment2(departmentMap.get(po.getDepartment()));
//            exclePo.setDesigner2(po.getDesigner());
//        } else if("007".equals(po.getTypeCode())){
//            //预算
//            exclePo.setTotalSalesPrice(po.getTotalSalesPrice());
//            exclePo.setArea(po.getConstructionArea());
//            exclePo.setAcquisitionDate(po.getAcquisitionDate());
//            exclePo.setInitialDeliveryDate(po.getInitialDeliveryDate());
//            exclePo.setTotalCost(po.getChildTotalCost());
//            exclePo.setDesignerCommission(po.getDesignerCommission());
//            exclePo.setDepartment(departmentMap.get(po.getDepartment()));
//            exclePo.setDesigner(po.getDesigner());
//        }else if("008".equals(po.getTypeCode())){
//            //其他
//        }
//    }
}
