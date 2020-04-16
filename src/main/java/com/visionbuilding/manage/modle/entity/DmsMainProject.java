package com.visionbuilding.manage.modle.entity;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@RequiredArgsConstructor
public class DmsMainProject  extends BaseEntity{

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date projectCreationTime;

    private String customerSource;

    private String customerCode;
    private String place;
    private String customerPhone;
    private Long salesArea;
    private Long unitPrice;
    private Long costPrice;
    private Long designSalesCost;
    private Long designCost;
    private Long profit;
    private Long amountReturned;
    private Long amountReceivable;
    private Long amountSpent;
    private Long amountsPayable;
    private String contractNo;
    private String remarks;
    private String production;

}