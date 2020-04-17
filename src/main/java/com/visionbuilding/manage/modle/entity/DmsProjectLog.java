package com.visionbuilding.manage.modle.entity;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;


@Data
@RequiredArgsConstructor
public class DmsProjectLog {
    private Long id;
    private Integer type;
    private String oldValue;
    private String newValue;
    private Long user;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date editeTime;
}