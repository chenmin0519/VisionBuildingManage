package com.visionbuilding.manage.controller.data;

import com.alibaba.fastjson.JSON;
import com.visionbuilding.manage.controller.BaseController;
import com.visionbuilding.manage.modle.ResultBean;
import com.visionbuilding.manage.modle.ResultPOListBean;
import com.visionbuilding.manage.modle.entity.DmsBusiness;
import com.visionbuilding.manage.service.DmsBusinessService;
import com.visionbuilding.manage.utill.FormDataUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
@RequestMapping("/data/business")
public class DmsBusinessController extends BaseController {
    @Autowired
    private DmsBusinessService dmsBusinessService;

    @RequestMapping("/business.html")
    public String user(HttpServletRequest request, Long parentId){
        return "data/business/business_list";
    }
    @RequestMapping("/edit.html")
    public String edit(HttpServletRequest request,Long id){
        DmsBusiness dmsBusiness = dmsBusinessService.selectByPrimaryKey(id);
        request.setAttribute("po",dmsBusiness);
        return "data/business/business_edit";
    }

    @RequestMapping("/add.html")
    public String add(HttpServletRequest request){
        return "data/business/business_edit";
    }

    @ResponseBody
    @RequestMapping("/getDates")
    public String getDates(DmsBusiness dmsBusiness, HttpServletRequest request)throws Exception{
        ResultPOListBean<DmsBusiness> resultPOListBean = new ResultPOListBean<>();
        //获取分页参数
        FormDataUtils.setQueryParamter(dmsBusiness, request);
        resultPOListBean = dmsBusinessService.queryPage(dmsBusiness);
        //格式话参数
        Map<String, Object> map = FormDataUtils.getFormDataMap(resultPOListBean.getValue(), resultPOListBean.getTotalCount());
        return JSON.toJSONString(map);
    }
    @ResponseBody
    @RequestMapping("/del")
    public String getDates(Long id)throws Exception{
        ResultBean resultBean = new ResultBean();
        try {
            dmsBusinessService.deleteByPrimaryKey(id);
            resultBean.success();
        }catch (Exception e){
            e.printStackTrace();
            resultBean.failure("系统异常");
        }
        return JSON.toJSONString(resultBean);
    }
    @ResponseBody
    @RequestMapping("/save")
    public String save(DmsBusiness dmsBusiness)throws Exception{
        ResultBean resultBean = new ResultBean();
        try {
            if(dmsBusiness.getId() == null){
                dmsBusinessService.insertSelective(dmsBusiness);
            }else{
                dmsBusinessService.updateByPrimaryKeySelective(dmsBusiness);
            }
            resultBean.success();
        }catch (Exception e){
            e.printStackTrace();
            resultBean.failure("系统异常");
        }
        return JSON.toJSONString(resultBean);
    }

}
