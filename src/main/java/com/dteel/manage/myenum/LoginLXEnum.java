package com.dteel.manage.myenum;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;


@AllArgsConstructor
@Getter
public enum LoginLXEnum {

    DL(1,"登陆"),ZX(0,"注销");
    private Integer key;
    private String desc;
}
