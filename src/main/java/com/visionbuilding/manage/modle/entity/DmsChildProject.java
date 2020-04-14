package com.visionbuilding.manage.modle.entity;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@RequiredArgsConstructor
public class DmsChildProject extends BaseEntity {
//    private Long id;

    private Long parentId;

    private String projectTypeCode;

    private String projectTypeName;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date acquisitionDate;

    private Long constructionArea;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date initialDeliveryDate;

    private Long costPrice;

    private Long totalCost;

    private Long totalSalesPrice; //销售总价

    private String draftingUnit;

    private Long designerCommission;

    private Long designerCommissionPrice;

    private Byte confirmStatus;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date confirmTime;

    private String remarks;

//    private Byte status;


    private Long department;

    private Long renderingNum;

    // 以下字段仅在modle类用

    private Long salesArea; //销售面积
    private Long unitPrice; //销售单价

}