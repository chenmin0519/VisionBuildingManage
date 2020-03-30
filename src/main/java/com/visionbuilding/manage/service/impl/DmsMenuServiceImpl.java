package com.visionbuilding.manage.service.impl;

import com.visionbuilding.manage.dao.mapper.DmsMenuMapper;
import com.visionbuilding.manage.exception.LogicException;
import com.visionbuilding.manage.modle.ResultBean;
import com.visionbuilding.manage.modle.ResultPOBean;
import com.visionbuilding.manage.modle.ResultPOListBean;
import com.visionbuilding.manage.modle.entity.DmsMenu;
import com.visionbuilding.manage.modle.query.QueryBean;
import com.visionbuilding.manage.myenum.EnumStatus;
import com.visionbuilding.manage.service.DmsMenuService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DmsMenuServiceImpl implements DmsMenuService {
    @Autowired
    private DmsMenuMapper dmsMenuMapper;


    @Override
    public ResultPOListBean<DmsMenu> queryMenuInfoByPage(DmsMenu menu) throws LogicException {
        ResultPOListBean<DmsMenu> result = new ResultPOListBean<>();
        //分页参数
        QueryBean queryBean = new QueryBean();
        queryBean.setPageNo(menu.getPageNo());
        queryBean.setPageRows(menu.getPageRows());
        queryBean.setF(menu.getPagingMap());

        int count = 0;
        try {
            count = dmsMenuMapper.queryMenuInfoByPageCount(queryBean);
            queryBean.resetTotalCount(count);
            List<DmsMenu> menus = new ArrayList<>();
            if(count > 0){
                menus = dmsMenuMapper.queryMenuInfoByPage(queryBean);
            }
            result.success(menus,count);
            //分页信息
            BeanUtils.copyProperties(queryBean, result);
        } catch (Exception e) {
            e.printStackTrace();
            result.setValue(null);
        }
        return result;
    }

    @Override
    public ResultPOListBean<DmsMenu> querMenuByParent(Long parentId) throws LogicException {
        ResultPOListBean<DmsMenu> resultPOListBean = new ResultPOListBean<>();
        try {
            List<DmsMenu> menus = dmsMenuMapper.querMenuByParent(parentId);
            resultPOListBean.success();
            resultPOListBean.setValue(menus);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultPOListBean;
    }

    @Override
    public ResultBean insertMenu(DmsMenu menu) throws LogicException {
        ResultBean resultBean = new ResultBean();
//        menuValidator.validataAddpar(menu);
        try{
            menu.setStatus(EnumStatus.YES.getKey());
            int count = dmsMenuMapper.insertSelective(menu);
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
    public ResultPOBean<DmsMenu> queryMenuById(Long id) throws LogicException {
        ResultPOBean<DmsMenu> resultPOBean = new ResultPOBean<>();
        try{
            DmsMenu menu = dmsMenuMapper.selectByPrimaryKey(id);
            resultPOBean.success(menu);
        }catch (Exception e){
            e.printStackTrace();
            throw new LogicException("数据库操作异常");
        }
        return resultPOBean;
    }

    @Override
    public ResultBean delMenu(Long id) throws LogicException {
        ResultBean resultBean = new ResultBean();
        try{
            int count = dmsMenuMapper.deleteByPrimaryKey(id);
            dmsMenuMapper.deleteByPrentId(id);
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
    public ResultBean updateMenu(DmsMenu menu) throws LogicException {
        ResultBean resultBean = new ResultBean();
//        menuValidator.validataUpdatePar(menu);
        try{
            dmsMenuMapper.updateByPrimaryKeySelective(menu);
        }catch (Exception e){
            e.printStackTrace();
            throw  new LogicException("数据库操作异常");
        }
        return resultBean;
    }

    @Override
    public ResultBean resetMenuZt(Long id, Integer status) throws LogicException {
        ResultBean resultBean = new ResultBean();
//        menuValidator.validataNotNull(id);
//        menuValidator.validataNotNull(status);
        try {
            //激活判断父级是否可用
            if("1".equals(status)){
                DmsMenu menu = dmsMenuMapper.selectByPrimaryKey(id);
                DmsMenu parentMenu =dmsMenuMapper.selectByPrimaryKey(menu.getMenuPid());
                if(parentMenu != null && "0".equals(parentMenu.getStatus())){
                    throw  new LogicException("不可激活,父级菜单处于禁用状态!");
                }else{
                    updateChrildAnd(id,status);
                }
            }else{
                //冻结 一起冻结子集菜单
                updateChrildAnd(id,status);
            }
        }catch (Exception e){
            e.printStackTrace();
            throw  new LogicException(e.getMessage());
        }
        return resultBean;
    }

    /**
     * 父级激活冻结子集同步
     * @param id
     * @param status
     */
    private void updateChrildAnd(Long id , Integer status){
        DmsMenu menuPar = new DmsMenu();
        menuPar.setId(id);
        menuPar.setStatus(status);
        dmsMenuMapper.updateByPrimaryKeySelective(menuPar);
        DmsMenu parentMenu = new DmsMenu();
        parentMenu.setMenuPid(id);
        parentMenu.setStatus(status);
        dmsMenuMapper.updateByParentId(parentMenu);
    }

    @Override
    public List<DmsMenu> queryUserMenu(Long userId) throws LogicException {
        List<DmsMenu> menus = new ArrayList<>();
        menus = dmsMenuMapper.queryUserMenu(userId);
        return menus;
    }


    @Override
    public List<DmsMenu> queryEditZtreeMenusByRole(Long id) throws LogicException {
        List<DmsMenu> menus = new ArrayList<>();
        menus = dmsMenuMapper.queryEditZtreeMenusByRole(id);
        return menus;
    }
    @Override
    public List<DmsMenu> queryMenuByRoleWid(Long id) throws LogicException {
        List<DmsMenu> menus = new ArrayList<>();
        menus = dmsMenuMapper.queryMenusByRole(id);
        return menus;
    }

    @Override
    public List<DmsMenu> queryAllMenu() throws LogicException {
        return dmsMenuMapper.queryAllMenu();
    }

}
