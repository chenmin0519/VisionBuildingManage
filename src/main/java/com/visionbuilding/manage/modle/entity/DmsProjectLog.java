package com.visionbuilding.manage.modle.entity;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class DmsProjectLog {
    private Long id;
    private Integer type;
    private String oldValue;
    private String newValue;
    private Long user;
}