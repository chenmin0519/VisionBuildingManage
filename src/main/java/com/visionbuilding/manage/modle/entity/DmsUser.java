package com.visionbuilding.manage.modle.entity;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.Date;

@Data
@RequiredArgsConstructor
public class DmsUser extends BaseEntity{

    private String userName;
    private String password;
    private String realName;
    private Date loginTime;
    private Integer loginCount;
    private String roleType;
    private Long department;
}
