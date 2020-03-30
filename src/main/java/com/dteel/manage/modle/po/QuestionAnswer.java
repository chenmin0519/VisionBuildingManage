package com.dteel.manage.modle.po;

import com.dteel.manage.modle.entity.DmsAnswer;
import com.dteel.manage.modle.entity.DmsQuestion;

import java.util.List;

public class QuestionAnswer extends DmsQuestionVo {
    private List<DmsAnswer> answers;

    public List<DmsAnswer> getAnswers() {
        return answers;
    }

    public void setAnswers(List<DmsAnswer> answers) {
        this.answers = answers;
    }
}
