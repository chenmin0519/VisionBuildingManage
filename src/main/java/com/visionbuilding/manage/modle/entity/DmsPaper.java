package com.visionbuilding.manage.modle.entity;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class DmsPaper  extends BaseEntity {
    private String code;
    private String paper;
    private Long departmentId;
}
