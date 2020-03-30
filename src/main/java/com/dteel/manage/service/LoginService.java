package com.dteel.manage.service;


import com.dteel.manage.modle.entity.DmsUser;

public interface LoginService {
    DmsUser login(String userName, String password) throws Exception;
}
