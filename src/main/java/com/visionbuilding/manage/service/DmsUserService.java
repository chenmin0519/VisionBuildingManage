package com.visionbuilding.manage.service;

import com.visionbuilding.manage.exception.LogicException;
import com.visionbuilding.manage.modle.ResultBean;
import com.visionbuilding.manage.modle.ResultPOBean;
import com.visionbuilding.manage.modle.ResultPOListBean;
import com.visionbuilding.manage.modle.entity.DmsUser;

public interface DmsUserService {
    /**
     * 分页查询
     * @param user
     * @return
     * @throws Exception
     */
    ResultPOListBean<DmsUser> queryUserInfoByPage(DmsUser user) throws Exception;

    ResultBean insertUser(DmsUser user)throws LogicException;

    ResultBean delUser(Long id)throws LogicException;

    ResultPOBean<DmsUser> queryUserInfoByWid(Long id)throws LogicException;

    ResultBean updateUserByWid(DmsUser user)throws LogicException;

    /**
     * 初试化密码：123456
     * @param id
     * @return
     * @throws LogicException
     */
    ResultBean resetPassword(Long id)throws LogicException;

    ResultBean saveUserRoles(DmsUser user, String roles)throws LogicException;

//    ResultPOListBean<Zd> queryUserZd();

    void resetPwd(String userName, String newPwd);

    void resetZt(DmsUser user);
}
