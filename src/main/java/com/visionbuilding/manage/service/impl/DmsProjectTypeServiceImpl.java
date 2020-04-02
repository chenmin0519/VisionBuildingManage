package com.visionbuilding.manage.service.impl;

import com.visionbuilding.manage.dao.mapper.DmsProjectTypeMapper;
import com.visionbuilding.manage.modle.ResultPOListBean;
import com.visionbuilding.manage.modle.entity.DmsProjectType;
import com.visionbuilding.manage.modle.query.QueryBean;
import com.visionbuilding.manage.service.DmsProjectTypeService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DmsProjectTypeServiceImpl implements DmsProjectTypeService {

    @Autowired
    private DmsProjectTypeMapper dmsProjectTypeMapper;

    @Override
    public DmsProjectType selectByPrimaryKey(Long id) {
        return dmsProjectTypeMapper.selectByPrimaryKey(id);
    }

    @Override
    public void deleteByPrimaryKey(Long id) {
        dmsProjectTypeMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void insertSelective(DmsProjectType param) {
        dmsProjectTypeMapper.insertSelective(param);
    }

    @Override
    public void updateByPrimaryKeySelective(DmsProjectType params) {
        dmsProjectTypeMapper.updateByPrimaryKeySelective(params);
    }

    @Override
    public ResultPOListBean<DmsProjectType> queryPage(DmsProjectType department) {
        ResultPOListBean<DmsProjectType> result = new ResultPOListBean<>();
        //分页参数
        QueryBean queryBean = new QueryBean();
        queryBean.setPageNo(department.getPageNo());
        queryBean.setPageRows(department.getPageRows());
        queryBean.setF(department.getPagingMap());

        int count = 0;
        count = dmsProjectTypeMapper.queryPageCount(queryBean);
        queryBean.resetTotalCount(count);
        List<DmsProjectType> departments = new ArrayList<>();
        if(count > 0){
            departments = dmsProjectTypeMapper.queryPage(queryBean);
        }
        result.success(departments,count);
        //分页信息
        BeanUtils.copyProperties(queryBean, result);
        return result;
    }
}
