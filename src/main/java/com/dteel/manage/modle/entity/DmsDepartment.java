package com.dteel.manage.modle.entity;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class DmsDepartment extends BaseEntity {
    private String code;
    private String departmentName;
    private Integer type;
    private String redio;
}
