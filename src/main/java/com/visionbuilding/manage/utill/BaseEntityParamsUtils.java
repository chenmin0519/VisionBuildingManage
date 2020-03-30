package com.visionbuilding.manage.utill;

import com.visionbuilding.manage.modle.entity.BaseEntity;
import com.visionbuilding.manage.modle.entity.DmsUser;

import java.util.Date;
import java.util.Objects;

public class BaseEntityParamsUtils {

    /**
     * 初始化新增公共信息
     * @param entity
     * @param user
     */
    public static void initAddParams(BaseEntity entity, DmsUser user){
        if(Objects.isNull(entity.getAddTime())){
            entity.setAddTime(new Date());
        }
        if(Objects.isNull(entity.getAddUser())){
            entity.setAddUser(user.getUserName());
        }

    }

    public static void initEditParams(BaseEntity entity, DmsUser user){
        if(Objects.isNull(entity.getUpdateTime())){
            entity.setUpdateTime(new Date());
        }
        if(Objects.isNull(entity.getUpdateUser())){
            entity.setUpdateUser(user.getUserName());
        }
    }
}
