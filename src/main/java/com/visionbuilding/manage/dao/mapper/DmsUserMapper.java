package com.visionbuilding.manage.dao.mapper;


import com.visionbuilding.manage.modle.entity.DmsUser;
import com.visionbuilding.manage.modle.query.QueryBean;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DmsUserMapper {
    /**
     * 登陆
     * @param userName
     * @param password
     * @return
     */
    DmsUser login(@Param("userName") String userName, @Param("password") String password) throws Exception;

    int updateUserById(DmsUser user);

    int resetPwd(@Param("userName") String userName,@Param("newPassword") String newPassword );

    int resetZt(@Param("status") Integer status ,@Param("id") Long id);

    int delUser(@Param("id") Long id);

    List<DmsUser> queryUserInfoByPage(QueryBean queryBean);

    int queryUserInfoByPageCount(QueryBean queryBean);

    DmsUser queryUserInfoByWid(@Param("id") Long id);

    int insertUser(DmsUser user);

    int queryUserInfoByuserName(@Param("userName") String userName);

    List<DmsUser> queryByDepartment(Long department);
}