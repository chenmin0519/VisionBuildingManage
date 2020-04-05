package com.visionbuilding.manage.service.impl;

import com.visionbuilding.manage.dao.mapper.DmsDepartmentMapper;
import com.visionbuilding.manage.modle.ResultPOListBean;
import com.visionbuilding.manage.modle.entity.DmsDepartment;
import com.visionbuilding.manage.modle.query.QueryBean;
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
    public DmsDepartment selectByPrimaryKey(Long id) {
        return dmsDepartmentMapper.selectByPrimaryKey(id);
    }

    @Override
    public void deleteByPrimaryKey(Long id) {
        dmsDepartmentMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void insertSelective(DmsDepartment param) {
        dmsDepartmentMapper.insertSelective(param);
    }

    @Override
    public void updateByPrimaryKeySelective(DmsDepartment params) {
        dmsDepartmentMapper.updateByPrimaryKeySelective(params);
    }

    @Override
    public ResultPOListBean<DmsDepartment> queryPage(DmsDepartment department) {
        ResultPOListBean<DmsDepartment> result = new ResultPOListBean<>();
        //分页参数
        QueryBean queryBean = new QueryBean();
        queryBean.setPageNo(department.getPageNo());
        queryBean.setPageRows(department.getPageRows());
        queryBean.setF(department.getPagingMap());

        int count = 0;
        count = dmsDepartmentMapper.queryPageCount(queryBean);
        queryBean.resetTotalCount(count);
        List<DmsDepartment> departments = new ArrayList<>();
        if(count > 0){
            departments = dmsDepartmentMapper.queryPage(queryBean);
        }
        result.success(departments,count);
        //分页信息
        BeanUtils.copyProperties(queryBean, result);
        return result;
    }

    @Override
    public List<DmsDepartment> getAll() {
        return dmsDepartmentMapper.getAll();
    }
}
