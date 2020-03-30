package com.dteel.manage.modle.po;

import com.dteel.manage.modle.entity.DmsQuestion;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class QuestionSaveVo extends DmsQuestion {
    private String checkA;
    private String checkB;
    private String checkC;
    private String checkD;

    private String checksA;
    private String checksB;
    private String checksC;
    private String checksD;
}
