package com.dteel.manage.modle.entity;

import lombok.Data;

import java.util.List;

@Data
public class DmsUserRole{
    private Long id;
    private Long userId;
    private Long roleId;
    private List<DmsRole> roles;
}
