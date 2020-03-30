package com.dteel.manage.modle.entity;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class DmsRole extends BaseEntity{
    private String roleName;
    private String roleComment;
}
