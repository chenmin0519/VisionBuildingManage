package com.dteel.manage.controller;

import com.dteel.manage.modle.ResultBean;
import com.dteel.manage.utill.ValidateUtils;

public class BaseController {
	public ResultBean validataNoll(Object object){
	    ResultBean resultBean=new ResultBean();
	    if(ValidateUtils.isNotEmptyObjectOrString(object)){
	        resultBean.success();
	    }else{
	        resultBean.failure("有必填字段未填写");
	    }
	    return resultBean;
	}
}
