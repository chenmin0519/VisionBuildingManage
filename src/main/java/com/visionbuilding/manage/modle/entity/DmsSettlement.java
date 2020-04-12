package com.visionbuilding.manage.modle.entity;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class DmsSettlement extends BaseEntity{
    private Long parentProject;
    private Long childProject;
    private Long saleAmount;
    private Long costAmount;
    private Long commissionAmount;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date eventDate;
}