package com.visionbuilding.manage.modle.entity;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class DmsAmountLog {
    private Long id;
    private Long projectId;
    private Long amountReturned;
    private Long amountReceivable;
    private Long amountSpent;
    private Long amountsPayable;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date eventDate;

}