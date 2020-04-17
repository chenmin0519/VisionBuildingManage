package com.visionbuilding.manage.service;

import com.visionbuilding.manage.modle.ResultPOListBean;
import com.visionbuilding.manage.modle.po.LoggerVo;
import com.visionbuilding.manage.modle.query.LoggerQuery;

public interface LoggerService {

    ResultPOListBean<LoggerVo> queryPage(LoggerQuery par) throws Exception;
}
