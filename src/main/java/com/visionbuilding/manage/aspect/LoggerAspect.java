package com.visionbuilding.manage.aspect;

import com.alibaba.fastjson.JSONObject;
import com.visionbuilding.manage.dao.mapper.DmsChildProjectMapper;
import com.visionbuilding.manage.dao.mapper.DmsMainProjectMapper;
import com.visionbuilding.manage.dao.mapper.DmsProjectLogMapper;
import com.visionbuilding.manage.modle.entity.*;
import com.visionbuilding.manage.myenum.EnumLoggerType;
import com.visionbuilding.manage.myenum.SessionAttributes;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@Component
@Aspect
public class LoggerAspect {

    @Autowired
    private DmsProjectLogMapper dmsProjectLogMapper;

    @Autowired
    private DmsMainProjectMapper dmsMainProjectMapper;

    @Autowired
    private DmsChildProjectMapper dmsChildProjectMapper;

    @Before("execution(* com.visionbuilding.manage.dao.mapper.DmsMainProjectMapper.insertSelective(..))")
    public void before(JoinPoint joinPoint){
        try{
            HttpServletRequest request =((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
            DmsUser user = (DmsUser) request.getSession().getAttribute(SessionAttributes.USER_SESSION_NAME);
            DmsProjectLog dmsProjectLog = new DmsProjectLog();
            dmsProjectLog.setNewValue(JSONObject.toJSONString(joinPoint.getArgs()[0]));
            dmsProjectLog.setUser(user.getId());
            dmsProjectLog.setEditeTime(new Date());
            dmsProjectLog.setType(EnumLoggerType.BIGPROJECT_ADD.getKey());
            dmsProjectLogMapper.insertSelective(dmsProjectLog);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Before("execution(* com.visionbuilding.manage.dao.mapper.DmsMainProjectMapper.updateByPrimaryKeySelective(..))")
    public void update(JoinPoint joinPoint){
        try{
            HttpServletRequest request =((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
            DmsUser user = (DmsUser) request.getSession().getAttribute(SessionAttributes.USER_SESSION_NAME);
            DmsProjectLog dmsProjectLog = new DmsProjectLog();
            dmsProjectLog.setNewValue(JSONObject.toJSONString(joinPoint.getArgs()[0]));
            dmsProjectLog.setUser(user.getId());
            dmsProjectLog.setType(EnumLoggerType.BIGPROJECT_UPDATE.getKey());
            dmsProjectLog.setEditeTime(new Date());
            DmsMainProject dmsMainProject = (DmsMainProject) joinPoint.getArgs()[0];
            dmsMainProject = dmsMainProjectMapper.selectByPrimaryKey(dmsMainProject.getId());
            dmsProjectLog.setOldValue(JSONObject.toJSONString(dmsMainProject));
            dmsProjectLogMapper.insertSelective(dmsProjectLog);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Before("execution(* com.visionbuilding.manage.dao.mapper.DmsChildProjectMapper.insertSelective(..))")
    public void childAdd(JoinPoint joinPoint){
        try{
            HttpServletRequest request =((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
            DmsUser user = (DmsUser) request.getSession().getAttribute(SessionAttributes.USER_SESSION_NAME);
            DmsProjectLog dmsProjectLog = new DmsProjectLog();
            dmsProjectLog.setNewValue(JSONObject.toJSONString(joinPoint.getArgs()[0]));
            dmsProjectLog.setUser(user.getId());
            dmsProjectLog.setEditeTime(new Date());
            dmsProjectLog.setType(EnumLoggerType.CHILDPROJECT_ADD.getKey());
            dmsProjectLogMapper.insertSelective(dmsProjectLog);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Before("execution(* com.visionbuilding.manage.dao.mapper.DmsChildProjectMapper.updateByPrimaryKeySelective(..))")
    public void childUpdate(JoinPoint joinPoint){
        try{
            HttpServletRequest request =((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
            DmsUser user = (DmsUser) request.getSession().getAttribute(SessionAttributes.USER_SESSION_NAME);
            DmsProjectLog dmsProjectLog = new DmsProjectLog();
            dmsProjectLog.setNewValue(JSONObject.toJSONString(joinPoint.getArgs()[0]));
            dmsProjectLog.setUser(user.getId());
            dmsProjectLog.setEditeTime(new Date());
            DmsChildProject childProject = (DmsChildProject) joinPoint.getArgs()[0];
            if(childProject.getDesignerCommission() != null){
                dmsProjectLog.setType(EnumLoggerType.REPORT_ADD.getKey());
            }else{
                dmsProjectLog.setType(EnumLoggerType.CHILDPROJECT_UPDATE.getKey());
            }
            childProject = dmsChildProjectMapper.selectByPrimaryKey(childProject.getId());
            dmsProjectLog.setOldValue(JSONObject.toJSONString(childProject));
            dmsProjectLogMapper.insertSelective(dmsProjectLog);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Before("execution(* com.visionbuilding.manage.dao.mapper.DmsSettlementMapper.insertSelective(..))")
    public void reportUpdate(JoinPoint joinPoint){
        try{
            HttpServletRequest request =((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
            DmsUser user = (DmsUser) request.getSession().getAttribute(SessionAttributes.USER_SESSION_NAME);
            DmsProjectLog dmsProjectLog = new DmsProjectLog();
            dmsProjectLog.setNewValue(JSONObject.toJSONString(joinPoint.getArgs()[0]));
            dmsProjectLog.setUser(user.getId());
            dmsProjectLog.setEditeTime(new Date());
            dmsProjectLog.setType(EnumLoggerType.REPORT_UPDATE.getKey());
            dmsProjectLogMapper.insertSelective(dmsProjectLog);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
