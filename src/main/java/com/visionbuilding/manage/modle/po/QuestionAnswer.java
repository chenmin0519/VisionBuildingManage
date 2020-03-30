package com.visionbuilding.manage.modle.po;

import com.visionbuilding.manage.modle.entity.DmsAnswer;

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
