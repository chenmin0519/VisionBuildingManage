package com.visionbuilding.manage.modle.entity;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class DmsBusiness extends BaseEntity{
    private Long id;

    private String business;

    private String pinyin;

    private String mobile;

}