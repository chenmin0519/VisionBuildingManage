package com.dteel.manage.modle.po;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class DmsQuestionImport {
    private String answer;
    private String question;
    private Integer type;
    private String departmentCode;
    private Integer score;
    private String checkA;
    private String checkB;
    private String checkC;
    private String checkD;
    private Long departmentId;
}
