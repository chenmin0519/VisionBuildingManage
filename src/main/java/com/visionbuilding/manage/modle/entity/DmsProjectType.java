package com.visionbuilding.manage.modle.entity;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class DmsProjectType extends BaseEntity{
    private Long id;

    private String typeName;

    private String typeCode;

}