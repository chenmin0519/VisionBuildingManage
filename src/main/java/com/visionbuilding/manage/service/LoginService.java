package com.visionbuilding.manage.service;


import com.visionbuilding.manage.modle.entity.DmsUser;

public interface LoginService {
    DmsUser login(String userName, String password) throws Exception;
}
