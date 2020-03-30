package com.dteel.manage.service;

import com.dteel.manage.exception.LogicException;
import com.dteel.manage.modle.ResultBean;
import com.dteel.manage.modle.ResultPOBean;
import com.dteel.manage.modle.ResultPOListBean;
import com.dteel.manage.modle.entity.DmsRole;

import java.util.List;

public interface DmsRoleService {

    ResultPOListBean<DmsRole> queryRoleInfoByPage(DmsRole role)throws Exception;

    ResultPOBean<DmsRole> saveRole(DmsRole role)throws LogicException;

    ResultBean delRole(Long id)throws LogicException;

    ResultBean saveRoleAndMenus(Long roleId, List<Long> menus)throws LogicException;

    ResultPOBean<DmsRole> queryRoleByWid(Long id)throws LogicException;

    ResultPOListBean<DmsRole> queryRoleByuser(Long id)throws LogicException;

    List<DmsRole> queryAllRole()throws LogicException;
}
