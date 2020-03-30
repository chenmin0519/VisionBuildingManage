package com.dteel.manage.service.impl;

import com.dteel.manage.dao.mapper.DmsRoleMapper;
import com.dteel.manage.dao.mapper.DmsRoleMenuMapper;
import com.dteel.manage.exception.LogicException;
import com.dteel.manage.modle.ResultBean;
import com.dteel.manage.modle.ResultPOBean;
import com.dteel.manage.modle.ResultPOListBean;
import com.dteel.manage.modle.entity.DmsRole;
import com.dteel.manage.modle.entity.DmsRoleMenu;
import com.dteel.manage.modle.query.QueryBean;
import com.dteel.manage.myenum.LoginLXEnum;
import com.dteel.manage.service.DmsRoleService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class DmsRoleServiceImpl implements DmsRoleService {

    @Autowired
    private DmsRoleMenuMapper dmsRoleMenuMapper;

    @Autowired
    private DmsRoleMapper dmsRoleMapper;

    @Override
    public ResultPOBean<DmsRole> queryRoleByWid(Long id) throws LogicException {
        ResultPOBean<DmsRole> resultPOBean = new ResultPOBean<>();
        try{
            DmsRole role = new DmsRole();
            role = dmsRoleMapper.selectByPrimaryKey(id);
            resultPOBean.setValue(role);
        }catch (Exception e){
            e.printStackTrace();
            throw new LogicException("数据库操作异常");
        }
        return resultPOBean;
    }

    @Override
    public ResultPOListBean<DmsRole> queryRoleInfoByPage(DmsRole role) throws Exception {
        ResultPOListBean<DmsRole> result = new ResultPOListBean<>();
        //分页参数
        QueryBean queryBean = new QueryBean();
        queryBean.setPageNo(role.getPageNo());
        queryBean.setPageRows(role.getPageRows());
        queryBean.setF(role.getPagingMap());

        int count = 0;
        count = dmsRoleMapper.queryRoleInfoByPageCount(queryBean);
        queryBean.resetTotalCount(count);
        List<DmsRole> users = new ArrayList<>();
        if(count > 0){
            users = dmsRoleMapper.queryRoleInfoByPage(queryBean);
        }
        result.success(users,count);
        //分页信息
        BeanUtils.copyProperties(queryBean, result);
        return result;
    }

    @Override
    public ResultPOBean<DmsRole> saveRole(DmsRole role) throws LogicException {
        ResultPOBean<DmsRole> resultPOBean = new ResultPOBean<>();
        try {
            if (role.getId() != null && !"".equals(role.getId())) {
                dmsRoleMapper.updateByPrimaryKeySelective(role);
                resultPOBean.success(role);
            } else {
                dmsRoleMapper.insertSelective(role);
                resultPOBean.success(role);
            }
        }catch (Exception e ){
            e.printStackTrace();
            throw new LogicException("数据库操作异常");
        }
        return resultPOBean;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResultBean delRole(Long id) throws LogicException {
        ResultBean resultBean = new ResultBean();
        try{
            dmsRoleMapper.deleteByPrimaryKey(id);
            dmsRoleMenuMapper.deleteByRoleIds(id);
            resultBean.success();
        }catch (Exception e){
            e.printStackTrace();
            throw new LogicException("数据库操作异常");
        }
        return resultBean;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResultBean saveRoleAndMenus(Long roleId, List<Long> menus) throws LogicException {
        ResultBean resultBean = new ResultBean();
        try {
            dmsRoleMenuMapper.deleteByRoleIds(roleId);
            List<DmsRoleMenu> roleMenus = new ArrayList<>();
            menus.forEach(menuId -> {
                DmsRoleMenu roleMenu = new DmsRoleMenu();
                roleMenu.setMenuId(menuId);
                roleMenu.setRoleId(roleId);
                roleMenus.add(roleMenu);
            });
            dmsRoleMenuMapper.saveBatch(roleMenus);
            resultBean.success();
        }catch (Exception e ){
            e.printStackTrace();
            throw new LogicException("数据库操作异常");
        }
        return resultBean;
    }

    @Override
    public ResultPOListBean<DmsRole> queryRoleByuser(Long id) throws LogicException {
        ResultPOListBean<DmsRole> resultPOListBean = new ResultPOListBean<>();
        List<DmsRole> all = new ArrayList<>();
        List<DmsRole> checkedList = dmsRoleMapper.queryRoleByuser(id);
        List<DmsRole> allList = dmsRoleMapper.queryAllRole();
        all = getChecked(allList,checkedList);
        resultPOListBean.setValue(all);
        return resultPOListBean;
    }

    @Override
    public List<DmsRole> queryAllRole() throws LogicException {
        return dmsRoleMapper.queryAllRole();
    }




    /**
     *
     * @param allList
     */
    private List<DmsRole>  getChecked(List<DmsRole> allList,List<DmsRole> checkedList) {
        List<DmsRole> all = new ArrayList<>();
        allList.forEach(role -> {
            boolean flag = true;
            for(DmsRole checkedrole : checkedList){
                if(flag) {
                    if (role.getId().equals(checkedrole.getId())) {
                        role.setStatus(LoginLXEnum.DL.getKey());
                        flag = false;
                    } else {
                        role.setStatus(LoginLXEnum.ZX.getKey());
                    }
                }
            }
            if(role.getStatus() == null){
                role.setStatus(LoginLXEnum.ZX.getKey());
            }
            all.add(role);
        });
        return all;
    }
}
