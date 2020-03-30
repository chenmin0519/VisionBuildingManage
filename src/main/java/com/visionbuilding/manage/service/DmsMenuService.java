package com.visionbuilding.manage.service;

import com.visionbuilding.manage.exception.LogicException;
import com.visionbuilding.manage.modle.ResultBean;
import com.visionbuilding.manage.modle.ResultPOBean;
import com.visionbuilding.manage.modle.ResultPOListBean;
import com.visionbuilding.manage.modle.entity.DmsMenu;

import java.util.List;

public interface DmsMenuService {
    ResultPOListBean<DmsMenu> queryMenuInfoByPage(DmsMenu menu)throws LogicException;

    ResultPOListBean<DmsMenu> querMenuByParent(Long parentId)throws LogicException;

    ResultBean insertMenu(DmsMenu menu)throws LogicException;

    ResultPOBean<DmsMenu> queryMenuById(Long id)throws LogicException;

    ResultBean delMenu(Long id) throws LogicException;

    ResultBean updateMenu(DmsMenu menu)throws LogicException;

    ResultBean resetMenuZt(Long id, Integer status)throws LogicException;

    /**
     * 查询用户有权限的菜单
     * @param id
     * @return
     * @throws LogicException
     */
    List<DmsMenu> queryUserMenu(Long id)throws LogicException;

    List<DmsMenu> queryMenuByRoleWid(Long id)throws LogicException;

    //获取编辑页面菜单
    List<DmsMenu> queryEditZtreeMenusByRole(Long id)throws LogicException;
    /**
     * 查询所有菜单
     * @return
     * @throws LogicException
     */
    List<DmsMenu> queryAllMenu()throws LogicException;
}
