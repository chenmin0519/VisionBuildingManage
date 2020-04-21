package com.visionbuilding.manage.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.visionbuilding.manage.dao.mapper.DmsProjectLogMapper;
import com.visionbuilding.manage.modle.ResultPOListBean;
import com.visionbuilding.manage.modle.entity.*;
import com.visionbuilding.manage.modle.po.LoggerVo;
import com.visionbuilding.manage.modle.query.LoggerQuery;
import com.visionbuilding.manage.modle.query.QueryBean;
import com.visionbuilding.manage.myenum.EnumLoggerType;
import com.visionbuilding.manage.service.LoggerService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class LoggerServiceImpl implements LoggerService {

    @Autowired
    private DmsProjectLogMapper dmsProjectLogMapper;

    @Override
    public ResultPOListBean<LoggerVo> queryPage(LoggerQuery par) throws Exception {
        ResultPOListBean<LoggerVo> result = new ResultPOListBean<>();
        //分页参数
        QueryBean queryBean = new QueryBean();
        queryBean.setPageNo(par.getPageNo());
        queryBean.setPageRows(par.getPageRows());
        queryBean.setF(par.getPagingMap());
        int count = 0;
        count = dmsProjectLogMapper.queryByPageCount(queryBean);
        queryBean.resetTotalCount(count);
        List<DmsProjectLog> users = new ArrayList<>();
        List<LoggerVo> loggerVos = new ArrayList<>();
        if(count > 0){
            users = dmsProjectLogMapper.queryByPage(queryBean);
            for(DmsProjectLog dmsProjectLog : users){
                loggerVos.add(initData(dmsProjectLog));
            }
        }
        result.setValue(loggerVos);
        //分页信息
        BeanUtils.copyProperties(queryBean, result);
        return result;
    }
    /**
     * 格式话数据
     * @param dmsProjectLog
     * @return
     */
    private LoggerVo initData(DmsProjectLog dmsProjectLog) {
        LoggerVo loggerVo = new LoggerVo();
        loggerVo.setId(dmsProjectLog.getId());
        loggerVo.setUser(dmsProjectLog.getUser());
        loggerVo.setType(dmsProjectLog.getType());
        if(EnumLoggerType.BIGPROJECT_ADD.getKey() == dmsProjectLog.getType() ||
                EnumLoggerType.BIGPROJECT_UPDATE.getKey() == dmsProjectLog.getType()){
            //大项目
            loggerVo.setNewValue(JSONObject.parseObject(dmsProjectLog.getNewValue(), DmsMainProject.class));
            if(dmsProjectLog.getOldValue() != null) {
                loggerVo.setOldValue(JSONObject.parseObject(dmsProjectLog.getOldValue(), DmsMainProject.class));
            }
        }else if(EnumLoggerType.CHILDPROJECT_ADD.getKey() == dmsProjectLog.getType()||
                    EnumLoggerType.CHILDPROJECT_UPDATE.getKey() == dmsProjectLog.getType()){
            loggerVo.setNewValue(JSONObject.parseObject(dmsProjectLog.getNewValue(), DmsChildProject.class));
            if(dmsProjectLog.getOldValue() != null) {
                loggerVo.setOldValue(JSONObject.parseObject(dmsProjectLog.getOldValue(), DmsChildProject.class));
            }
        }else if(EnumLoggerType.REPORT_ADD.getKey() == dmsProjectLog.getType()){
            loggerVo.setNewValue(JSONObject.parseObject(dmsProjectLog.getNewValue(), DmsChildProject.class));
            if(dmsProjectLog.getOldValue() != null) {
                loggerVo.setOldValue(JSONObject.parseObject(dmsProjectLog.getOldValue(), DmsChildProject.class));
            }
        }else if(EnumLoggerType.REPORT_UPDATE.getKey() == dmsProjectLog.getType()){
            loggerVo.setNewValue(JSONObject.parseObject(dmsProjectLog.getNewValue(), DmsSettlement.class));
            if(dmsProjectLog.getOldValue() != null) {
                loggerVo.setOldValue(JSONObject.parseObject(dmsProjectLog.getOldValue(), DmsSettlement.class));
            }
        }
        return loggerVo;
    }
}
