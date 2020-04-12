package com.visionbuilding.manage.modle.entity;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.Date;

@Data
@RequiredArgsConstructor
public class DmsChildProject extends BaseEntity {
//    private Long id;

    private Long parentId;

    private String projectTypeCode;

    private String projectTypeName;

    private Date acquisitionDate;

    private Long constructionArea;

    private Date initialDeliveryDate;

    private Long costPrice;

    private Long totalCost;

    private Long totalSalesPrice; //销售总价

    private String draftingUnit;

    private Long designerCommission;

    private Long designerCommissionPrice;

    private Byte confirmStatus;

    private Date confirmTime;

    private String remarks;

//    private Byte status;

    private String addUser;

    private Date addTime;

    private String updateUser;

    private Date updateTime;

    private Long department;

    private Long renderingNum;

}