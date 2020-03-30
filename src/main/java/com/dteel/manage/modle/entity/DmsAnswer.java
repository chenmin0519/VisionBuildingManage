package com.dteel.manage.modle.entity;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class DmsAnswer extends BaseEntity {
    private Long questionId;
    private String code;
    private String text;
}
