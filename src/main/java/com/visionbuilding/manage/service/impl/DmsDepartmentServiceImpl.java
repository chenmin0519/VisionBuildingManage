package com.visionbuilding.manage.service.impl;

import com.visionbuilding.manage.dao.mapper.DmsDepartmentMapper;
import com.visionbuilding.manage.exception.LogicException;
import com.visionbuilding.manage.modle.ResultBean;
import com.visionbuilding.manage.modle.ResultPOListBean;
import com.visionbuilding.manage.modle.entity.DmsDepartment;
import com.visionbuilding.manage.modle.query.QueryBean;
import com.visionbuilding.manage.myenum.EnumStatus;
import com.visionbuilding.manage.service.DmsDepartmentService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DmsDepartmentServiceImpl implements DmsDepartmentService {

    @Autowired
    private DmsDepartmentMapper dmsDepartmentMapper;

    @Override
    public ResultPOListBean<DmsDepartment> queryPage(DmsDepartment department) {
        ResultPOListBean<DmsDepartment> result = new ResultPOListBean<>();
        //分页参数
        QueryBean queryBean = new QueryBean();
        queryBean.setPageNo(department.getPageNo());
        queryBean.setPageRows(department.getPageRows());
        queryBean.setF(department.getPagingMap());

        int count = 0;
        try {
            count = dmsDepartmentMapper.queryPageCount(queryBean);
            queryBean.resetTotalCount(count);
            List<DmsDepartment> menus = new ArrayList<>();
            if(count > 0){
                menus = dmsDepartmentMapper.queryPage(queryBean);
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
    public DmsDepartment queryById(Long id) {
        return dmsDepartmentMapper.queryById(id);
    }

    @Override
    public ResultBean insert(DmsDepartment department) {
        ResultBean resultBean = new ResultBean();
        try{
            department.setStatus(EnumStatus.YES.getKey());
            int count = dmsDepartmentMapper.insertSelective(department);
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
    public ResultBean update(DmsDepartment department) {
        ResultBean resultBean = new ResultBean();
//        menuValidator.validataUpdatePar(menu);
        try{
            dmsDepartmentMapper.updateByPrimaryKeySelective(department);
        }catch (Exception e){
            e.printStackTrace();
            throw  new LogicException("数据库操作异常");
        }
        return resultBean;
    }

    @Override
    public ResultBean delete(Long id) {
        ResultBean resultBean = new ResultBean();
//        menuValidator.validataUpdatePar(menu);
        try{
            dmsDepartmentMapper.delete(id);
        }catch (Exception e){
            e.printStackTrace();
            throw  new LogicException("数据库操作异常");
        }
        return resultBean;
    }

    @Override
    public ResultPOListBean<DmsDepartment> queryAll() {
        ResultPOListBean<DmsDepartment> resultPOListBean = new ResultPOListBean<>();
        List<DmsDepartment> list = dmsDepartmentMapper.queryAll();
        resultPOListBean.success(list,list.size());
        return resultPOListBean;
    }


    @Override
    public List<DmsDepartment> queryDepartmentList() {
        return dmsDepartmentMapper.queryAll();
    }
}
