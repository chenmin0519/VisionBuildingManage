package com.visionbuilding.manage.modle.po;

import com.visionbuilding.manage.modle.entity.DmsRole;
import com.visionbuilding.manage.modle.entity.DmsUser;
import lombok.Data;

import java.util.List;

@Data
public class DmsUserRolePo extends DmsUser {
    private List<DmsRole> roles;

}
