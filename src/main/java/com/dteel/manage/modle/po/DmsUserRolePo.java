package com.dteel.manage.modle.po;

import com.dteel.manage.modle.entity.DmsRole;
import com.dteel.manage.modle.entity.DmsUser;
import lombok.Data;

import java.util.List;

@Data
public class DmsUserRolePo extends DmsUser {
    private List<DmsRole> roles;

}
