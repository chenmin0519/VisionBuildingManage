package com.dteel.manage.modle.entity;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class DmsPaperQuestion {
    private Long id;
    private Long paper;
    private Long question;

}
