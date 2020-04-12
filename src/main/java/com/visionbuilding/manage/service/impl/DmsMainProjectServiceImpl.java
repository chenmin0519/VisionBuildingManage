package com.visionbuilding.manage.service.impl;

import com.visionbuilding.manage.dao.mapper.DmsChildProjectMapper;
import com.visionbuilding.manage.dao.mapper.DmsMainProjectMapper;
import com.visionbuilding.manage.modle.ResultPOListBean;
import com.visionbuilding.manage.modle.entity.DmsChildProject;
import com.visionbuilding.manage.modle.entity.DmsMainProject;
import com.visionbuilding.manage.modle.query.QueryBean;
import com.visionbuilding.manage.service.DmsMainProjectService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class DmsMainProjectServiceImpl implements DmsMainProjectService {

    @Autowired
    private DmsMainProjectMapper dmsMainProjectMapper;

    @Autowired
    private DmsChildProjectMapper dmsChildProjectMapper;

    @Override
    public DmsMainProject selectByPrimaryKey(Long id) {
        return dmsMainProjectMapper.selectByPrimaryKey(id);
    }

    @Override
    public void deleteByPrimaryKey(Long id) {
        dmsMainProjectMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void insertSelective(DmsMainProject dmsMainProject) {
        dmsMainProject.setCustomerSource("郑灿大傻逼");
//        LocalDateTime.now().
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String dateStr = sdf.format(date);
        dmsMainProject.setProjectCreationTime(date);
        dmsMainProject.setCustomerCode("ZC-202004-002");
        dmsMainProjectMapper.insertSelective(dmsMainProject);
    }

    @Override
    public void updateByPrimaryKeySelective(DmsMainProject dmsMainProject) {
        dmsMainProjectMapper.updateByPrimaryKeySelective(dmsMainProject);
    }

    @Override
    public ResultPOListBean<DmsMainProject> queryPage(DmsMainProject dmsMainProject) {
        ResultPOListBean<DmsMainProject> result = new ResultPOListBean<>();
        //分页参数
        QueryBean queryBean = new QueryBean();
        queryBean.setPageNo(dmsMainProject.getPageNo());
        queryBean.setPageRows(dmsMainProject.getPageRows());
        queryBean.setF(dmsMainProject.getPagingMap());

        int count = 0;
        count = dmsMainProjectMapper.queryPageCount(queryBean);
        queryBean.resetTotalCount(count);
        List<DmsMainProject> dmsMainProjects = new ArrayList<>();
        if(count > 0){
            dmsMainProjects = dmsMainProjectMapper.queryPage(queryBean);
        }
        result.success(dmsMainProjects,count);
        //分页信息
        BeanUtils.copyProperties(queryBean, result);
        return result;
    }

    @Override
    public void insertSelectiveChild(DmsChildProject dmsChildProject) {
//        dmsMainProject.setCustomerSource("郑灿大傻逼");
////        LocalDateTime.now().
//        Date date = new Date();
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//        String dateStr = sdf.format(date);
//        dmsMainProject.setProjectCreationTime(date);
//        dmsMainProject.setCustomerCode("ZC-202004-002");
        dmsChildProjectMapper.insertSelective(dmsChildProject);
    }

    @Override
    public void updateByPrimaryKeySelectiveChild(DmsChildProject dmsChildProject) {
        dmsChildProjectMapper.updateByPrimaryKeySelective(dmsChildProject);
    }
}
