package com.visionbuilding.manage.modle.entity;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class DmsQuestion extends BaseEntity {
    private String answer;
    private String question;
    private Integer type;
    private Long departmentId;
    private Integer score;
}
