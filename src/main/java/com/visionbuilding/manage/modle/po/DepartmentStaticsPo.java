package com.visionbuilding.manage.modle.po;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class DepartmentStaticsPo {
    private String projectCode;//建档时间
    private Long salesArea;//销售面积
    private Long costPrice;//成本单价
    private String customerSource;//客户来源
    private String customerCode;//客户编码
    private String place;//项目地点
    private Long designSalesCost;//设计销售费用
    private Long totalSalesPrice;//设计销售zongjia
    private Long parentId;
    private Long typeCode;//类型编码
    private String typeName;
    private Long constructionArea;//面积
    private Long childCostPrice;//成本单价
    private Long childTotalCost;//成本总价
    private Long designerCommissionPrice;//提成单价
    private Long designerCommission;//提成总价
//    private Long totalSalesPrice;
    private Long department;//部门
    private String designer;//设计师
    private Long amountReturned;
    private Long amountReceivable;
    private Long amountSpent;
    private Long amountsPayable;
    private String contractNo;
    private String remarks;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date acquisitionDate;//shoudan
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date initialDeliveryDate;//jiaofu
}
