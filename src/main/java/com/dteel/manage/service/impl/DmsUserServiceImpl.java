package com.dteel.manage.service.impl;

import com.dteel.manage.dao.mapper.DmsUserMapper;
import com.dteel.manage.dao.mapper.DmsUserRoleMapper;
import com.dteel.manage.exception.LogicException;
import com.dteel.manage.modle.ResultBean;
import com.dteel.manage.modle.ResultPOBean;
import com.dteel.manage.modle.ResultPOListBean;
import com.dteel.manage.modle.entity.DmsUser;
import com.dteel.manage.modle.entity.DmsUserRole;
import com.dteel.manage.modle.query.QueryBean;
import com.dteel.manage.myenum.EnumStatus;
import com.dteel.manage.service.DmsUserService;
import com.dteel.manage.utill.EncryptionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DmsUserServiceImpl implements DmsUserService {
    @Autowired
    private DmsUserMapper dmsUserMapper;

    @Autowired
    private DmsUserRoleMapper dmsUserRoleMapper;

    @Override
    public ResultPOListBean<DmsUser> queryUserInfoByPage(DmsUser user) throws Exception {
        ResultPOListBean<DmsUser> result = new ResultPOListBean<>();
        //分页参数
        QueryBean queryBean = new QueryBean();
        queryBean.setPageNo(user.getPageNo());
        queryBean.setPageRows(user.getPageRows());
        queryBean.setF(user.getPagingMap());

        int count = 0;
        count = dmsUserMapper.queryUserInfoByPageCount(queryBean);
        queryBean.resetTotalCount(count);
        List<DmsUser> users = new ArrayList<>();
        if(count > 0){
            users = dmsUserMapper.queryUserInfoByPage(queryBean);
        }
        result.success(users,count);
        //分页信息
        BeanUtils.copyProperties(queryBean, result);
        return result;
    }

    @Override
    public ResultBean insertUser(DmsUser user) throws LogicException {
        ResultBean resultBean = new ResultBean();
//        userValidator.validataUser(user);
        try{
            user.setPassword(EncryptionUtils.getCiphertext(user.getPassword()));
            user.setStatus(EnumStatus.YES.getKey());
            int count = dmsUserMapper.insertUser(user);
            if(count>0){
                resultBean.success();
            }else{
                resultBean.failure("操作失败");
            }
        }catch (Exception e){
            e.printStackTrace();
            throw  new LogicException("数据库操作异常");
        }
        return resultBean;
    }

    @Override
    public ResultBean delUser(Long id) throws LogicException {
        ResultBean resultBean = new ResultBean();
        try{
            dmsUserMapper.delUser(id);
            resultBean.success();
        }catch (Exception e){
            e.printStackTrace();
            resultBean.failure("数据库操作异常");
        }
        return resultBean;
    }

    @Override
    public ResultPOBean<DmsUser> queryUserInfoByWid(Long id) throws LogicException {
        ResultPOBean<DmsUser> users = new ResultPOBean<>();
        DmsUser user = null;
        try {
            user = dmsUserMapper.queryUserInfoByWid(id);
            users.setValue(user);
            users.success();
        } catch (Exception e) {
            e.printStackTrace();
            users.failure("数据库操作异常");
        }
        return users;
    }

    @Override
    public ResultBean updateUserByWid(DmsUser user) throws LogicException {
        ResultBean resultBean = new ResultBean();
        try{
            if(user.getPassword() != null && "******".equals(user.getPassword())){
                user.setPassword(null);
            }else{
                user.setPassword(EncryptionUtils.getCiphertext(user.getPassword()));
            }
            dmsUserMapper.updateUserById(user);
            resultBean.success();
        }catch (Exception e){
            e.printStackTrace();
            resultBean.failure("数据库操作异常");
            throw new LogicException("数据库操作异常");
        }
        return resultBean;
    }

    @Override
    public ResultBean resetPassword(Long id) throws LogicException {
        ResultBean resultBean = new ResultBean();
        DmsUser user = new DmsUser();
        user.setId(id);
        user.setPassword(EncryptionUtils.getCiphertext("123456"));
        try {
            dmsUserMapper.updateUserById(user);
            resultBean.success();
        } catch (Exception e) {
            e.printStackTrace();
            throw new LogicException("数据库操作异常");
        }
        return resultBean;
    }

    @Override
    public ResultBean saveUserRoles(DmsUser user, String roles) throws LogicException {
        ResultBean resultBean = new ResultBean();
        if (user.getId() == null) {
            insertUser(user);
            resultBean.success();
        } else {
            updateUserByWid(user);
            resultBean.success();
        }
        dmsUserRoleMapper.delByUserId(user.getId());
        List<DmsUserRole> list = new ArrayList<>();
        if(roles != null && !"".equals(roles.trim())) {
            String[] roleIds = roles.split(",");
            if (roleIds != null && roleIds.length > 0) {
                for (String roleId : roleIds) {
                    DmsUserRole userRole = new DmsUserRole();
                    userRole.setRoleId(new Long(roleId));
                    userRole.setUserId(user.getId());
                    list.add(userRole);
                }
                dmsUserRoleMapper.saveBatch(list);
            }
        }
        return resultBean;
    }

    @Override
    public void resetPwd(String userName, String newPwd) {
        dmsUserMapper.resetPwd(userName,newPwd);
    }

    @Override
    public void resetZt(DmsUser user) {
        dmsUserMapper.resetZt(user.getStatus(),user.getId());
    }
}
