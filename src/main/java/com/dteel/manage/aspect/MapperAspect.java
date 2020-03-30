package com.dteel.manage.aspect;

import com.dteel.manage.modle.entity.BaseEntity;
import com.dteel.manage.modle.entity.DmsUser;
import com.dteel.manage.myenum.SessionAttributes;
import com.dteel.manage.utill.BaseEntityParamsUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

@Component
@Aspect
public class MapperAspect {

    @Before("execution(* com.dteel.manage.dao.mapper.*.*(..))")
    public void before(JoinPoint joinPoint){
        HttpServletRequest request =((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
        Object[] args = joinPoint.getArgs();
        String methodName = joinPoint.getSignature().getName();
        DmsUser user = (DmsUser) request.getSession().getAttribute(SessionAttributes.USER_SESSION_NAME);
        if(user != null) {
            if (methodName.startsWith("insert")) {
                if (args != null && args.length > 0) {
                    if (args[0] instanceof BaseEntity) {
                        BaseEntity entity = (BaseEntity) args[0];
                        BaseEntityParamsUtils.initAddParams(entity, user);
                    }
                }
            } else if (methodName.startsWith("update")) {
                if (args != null && args.length > 0) {
                    if (args[0] instanceof BaseEntity) {
                        BaseEntity entity = (BaseEntity) args[0];
                        BaseEntityParamsUtils.initEditParams(entity, user);
                    }
                }
            }
        }
    }

}
