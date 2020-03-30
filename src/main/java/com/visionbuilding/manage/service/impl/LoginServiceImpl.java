package com.visionbuilding.manage.service.impl;


import com.visionbuilding.manage.dao.mapper.DmsUserMapper;
import com.visionbuilding.manage.modle.entity.DmsUser;
import com.visionbuilding.manage.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private DmsUserMapper dmsUserMapper;

    @Override
    public DmsUser login(String userName, String password) throws Exception{
        DmsUser user = dmsUserMapper.login(userName,password);
        return user;
    }
}
