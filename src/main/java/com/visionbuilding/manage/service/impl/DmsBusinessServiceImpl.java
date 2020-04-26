package com.visionbuilding.manage.service.impl;

import com.visionbuilding.manage.dao.mapper.DmsBusinessMapper;
import com.visionbuilding.manage.dao.mapper.DmsMainProjectMapper;
import com.visionbuilding.manage.exception.LogicException;
import com.visionbuilding.manage.modle.ResultPOListBean;
import com.visionbuilding.manage.modle.entity.DmsBusiness;
import com.visionbuilding.manage.modle.entity.DmsMainProject;
import com.visionbuilding.manage.modle.entity.DmsProjectType;
import com.visionbuilding.manage.modle.query.QueryBean;
import com.visionbuilding.manage.service.DmsBusinessService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DmsBusinessServiceImpl implements DmsBusinessService {

    @Autowired
    private DmsBusinessMapper dmsBusinessMapper;

    @Autowired
    private DmsMainProjectMapper dmsMainProjectMapper;

    @Override
    public DmsBusiness selectByPrimaryKey(Long id) {
        return dmsBusinessMapper.selectByPrimaryKey(id);
    }

    @Override
    public void deleteByPrimaryKey(Long id) {
        DmsBusiness business = dmsBusinessMapper.selectByPrimaryKey(id);
        int count = dmsMainProjectMapper.countByBussiness(business.getPinyin());
        if(count > 0){
            throw new LogicException("已关联项目不能删除");
        }
        dmsBusinessMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void insertSelective(DmsBusiness param) {
        int count = dmsBusinessMapper.countByPingying(param.getPinyin());
        if(count > 0){
            throw new LogicException("拼音不能重复存在请从新填写");
        }
        dmsBusinessMapper.insertSelective(param);
    }

    @Override
    public void updateByPrimaryKeySelective(DmsBusiness params) {
        dmsBusinessMapper.updateByPrimaryKeySelective(params);
    }

    @Override
    public ResultPOListBean<DmsBusiness> queryPage(DmsBusiness department) {
        ResultPOListBean<DmsBusiness> result = new ResultPOListBean<>();
        //分页参数
        QueryBean queryBean = new QueryBean();
        queryBean.setPageNo(department.getPageNo());
        queryBean.setPageRows(department.getPageRows());
        queryBean.setF(department.getPagingMap());

        int count = 0;
        count = dmsBusinessMapper.queryPageCount(queryBean);
        queryBean.resetTotalCount(count);
        List<DmsBusiness> departments = new ArrayList<>();
        if(count > 0){
            departments = dmsBusinessMapper.queryPage(queryBean);
        }
        result.success(departments,count);
        //分页信息
        BeanUtils.copyProperties(queryBean, result);
        return result;
    }

    @Override
    public List<DmsBusiness> getAll() {
        return dmsBusinessMapper.getAll();
    }
}
