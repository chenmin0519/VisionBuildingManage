package com.visionbuilding.manage.modle.po;

import com.visionbuilding.manage.annotation.ExcelName;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class ExportExclePo {
    @ExcelName("业务初始建档时间")
    private String projectCode;
    @ExcelName("客户来源")
    private String customerSource;
    @ExcelName("客户编码")
    private  String customerCode;
    @ExcelName("项目地点")
    private  String place;
    @ExcelName("销售面积")
    private Long salesArea;

    @ExcelName("平面面积")
    private Long area1;
    @ExcelName("水电面积")
    private Long area2;
    @ExcelName("效果图张数")
    private Long area3;
    @ExcelName("结构面积")
    private Long area4;
    @ExcelName("立面面积")
    private Long area5;
    @ExcelName("基础面积")
    private Long area6;
    @ExcelName("预算面积")
    private Long area;

    @ExcelName("平面收单时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date acquisitionDate1;
    @ExcelName("平面交付时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date initialDeliveryDate1;
    @ExcelName("平面总价")
    private Long totalSalesPrice1;
    @ExcelName("平面成本")
    private Long totalCost1;
    @ExcelName("平面设计师提成")
    private Long designerCommission1;
    @ExcelName("平面设计部门")
    private String department1;
    @ExcelName("平面设计师")
    private String designer1;

    @ExcelName("水电收单时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date acquisitionDate2;
    @ExcelName("水电交付时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date initialDeliveryDate2;
    @ExcelName("水电总价")
    private Long totalSalesPrice2;
    @ExcelName("水电成本")
    private Long totalCost2;
    @ExcelName("水电设计师提成")
    private Long designerCommission2;
    @ExcelName("水电设计部门")
    private String department2;
    @ExcelName("水电设计师")
    private String designer2;

    @ExcelName("效果图收单时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date acquisitionDate3;
    @ExcelName("效果图交付时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date initialDeliveryDate3;
    @ExcelName("效果图总价")
    private Long totalSalesPrice3;
    @ExcelName("效果图成本")
    private Long totalCost3;
    @ExcelName("效果图设计师提成")
    private Long designerCommission3;
    @ExcelName("效果图设计部门")
    private String department3;
    @ExcelName("效果图设计师")
    private String designer3;

    @ExcelName("结构收单时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date acquisitionDate4;
    @ExcelName("结构交付时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date initialDeliveryDate4;
    @ExcelName("结构总价")
    private Long totalSalesPrice4;
    @ExcelName("结构成本")
    private Long totalCost4;
    @ExcelName("结构设计师提成")
    private Long designerCommission4;
    @ExcelName("结构设计部门")
    private String department4;
    @ExcelName("结构设计师")
    private String designer4;

    @ExcelName("立面收单时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date acquisitionDate5;
    @ExcelName("立面交付时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date initialDeliveryDate5;
    @ExcelName("立面总价")
    private Long totalSalesPrice5;
    @ExcelName("立面成本")
    private Long totalCost5;
    @ExcelName("立面设计师提成")
    private Long designerCommission5;
    @ExcelName("立面设计部门")
    private String department5;
    @ExcelName("立面设计师")
    private String designer5;

    @ExcelName("基础收单时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date acquisitionDate6;
    @ExcelName("基础交付时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date initialDeliveryDate6;
    @ExcelName("基础总价")
    private Long totalSalesPrice6;
    @ExcelName("基础成本")
    private Long totalCost6;
    @ExcelName("基础设计师提成")
    private Long designerCommission6;
    @ExcelName("基础设计部门")
    private String department6;
    @ExcelName("基础设计师")
    private String designer6;

    @ExcelName("预算收单时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date acquisitionDate;
    @ExcelName("预算交付时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date initialDeliveryDate;
    @ExcelName("预算总价")
    private Long totalSalesPrice;
    @ExcelName("预算成本")
    private Long totalCost;
    @ExcelName("预算设计师提成")
    private Long designerCommission;
    @ExcelName("预算设计部门")
    private String department;
    @ExcelName("预算人")
    private String designer;

    @ExcelName("设计销售费用")
    private Long designSalesCost;
    @ExcelName("设计成本费用")
    private Long designCost;
    @ExcelName("利润")
    private Long profit;
    @ExcelName("已回款金额")
    private Long amountReturned;
    @ExcelName("应收金额")
    private Long amountReceivable;
    @ExcelName("已支出金额")
    private Long amountSpent;
    @ExcelName("应付金额")
    private Long amountsPayable;
    @ExcelName("合同号")
    private String contractNo;
    @ExcelName("备注")
    private String remarks;
}
