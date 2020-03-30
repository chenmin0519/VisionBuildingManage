package com.dteel.manage.myenum;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum EnumQuestoinType {
    RADIO(0,"单选"),CHECKBOX(1,"多选"),TORF(2,"判断");
    private Integer key;
    private String text;
}
