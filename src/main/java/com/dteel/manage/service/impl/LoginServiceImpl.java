package com.dteel.manage.service.impl;


import com.dteel.manage.dao.mapper.DmsUserMapper;
import com.dteel.manage.modle.entity.DmsUser;
import com.dteel.manage.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements LoginService{

    @Autowired
    private DmsUserMapper dmsUserMapper;

    @Override
    public DmsUser login(String userName, String password) throws Exception{
        DmsUser user = dmsUserMapper.login(userName,password);
        return user;
    }
}
