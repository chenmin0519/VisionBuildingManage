package com.visionbuilding.manage.controller.data;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.visionbuilding.manage.controller.BaseController;
import com.visionbuilding.manage.controller.project.DmsProjectController;
import com.visionbuilding.manage.dao.mapper.DmsChildProjectMapper;
import com.visionbuilding.manage.modle.ResultBean;
import com.visionbuilding.manage.modle.ResultPOBean;
import com.visionbuilding.manage.modle.ResultPOListBean;
import com.visionbuilding.manage.modle.entity.DmsDepartment;
import com.visionbuilding.manage.modle.entity.DmsProjectType;
import com.visionbuilding.manage.service.DmsMainProjectService;
import com.visionbuilding.manage.service.DmsProjectTypeService;
import com.visionbuilding.manage.utill.FormDataUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping("/data/projectType")
public class DmsProjectTypeController extends BaseController {
    @Autowired
    private DmsProjectTypeService dmsProjectTypeService;
    @Autowired
    private DmsMainProjectService dmsMainProjectService;

    @RequestMapping("/projectType.html")
    public String user(HttpServletRequest request, Long parentId){
        return "data/projectType/projectType_list";
    }
    @RequestMapping("/edit.html")
    public String edit(HttpServletRequest request,Long id){
        DmsProjectType dmsProjectType = dmsProjectTypeService.selectByPrimaryKey(id);
        request.setAttribute("po",dmsProjectType);
        return "data/projectType/projectType_edit";
    }

    @RequestMapping("/add.html")
    public String add(HttpServletRequest request){
        return "data/projectType/projectType_edit";
    }

    @ResponseBody
    @RequestMapping("/getDates")
    public String getDates(DmsProjectType dmsProjectType, HttpServletRequest request)throws Exception{
        ResultPOListBean<DmsProjectType> resultPOListBean = new ResultPOListBean<>();
        //获取分页参数
        FormDataUtils.setQueryParamter(dmsProjectType, request);
        resultPOListBean = dmsProjectTypeService.queryPage(dmsProjectType);
        //格式话参数
        Map<String, Object> map = FormDataUtils.getFormDataMap(resultPOListBean.getValue(), resultPOListBean.getTotalCount());
        return JSON.toJSONString(map);
    }
    @ResponseBody
    @RequestMapping("/del")
    public String getDates(Long id)throws Exception{
        ResultBean resultBean = new ResultBean();
        resultBean.failure("暂时不能删除请联系管理员");
        try {
            dmsProjectTypeService.deleteByPrimaryKey(id);
            resultBean.success();
        }catch (Exception e){
            e.printStackTrace();
            resultBean.failure(e.getMessage());
        }
        return JSON.toJSONString(resultBean);
    }
    @ResponseBody
    @RequestMapping("/save")
    public String save(DmsProjectType dmsProjectType)throws Exception{
        ResultBean resultBean = new ResultBean();
//        resultBean.failure("暂时不能操作请联系管理员");
        try {
            if(dmsProjectType.getId() == null){
                dmsProjectTypeService.insertSelective(dmsProjectType);
            }else{
                dmsProjectTypeService.updateByPrimaryKeySelective(dmsProjectType);
            }
            resultBean.success();
        }catch (Exception e){
            e.printStackTrace();
            resultBean.failure(e.getMessage());
        }
        return JSON.toJSONString(resultBean);
    }

    @ResponseBody
    @RequestMapping("/getAll")
    public String getAll()throws Exception{
        List<DmsProjectType> resultBean = new ArrayList<>();
        try {
            resultBean = dmsProjectTypeService.getAll();
        }catch (Exception e){
            e.printStackTrace();
        }
        return JSON.toJSONString(resultBean);
    }

    @ResponseBody
    @RequestMapping("/getUserCommission")
    public String getUserCommission(@RequestParam("uid") Long uid,@RequestParam("projectCode") String projectCode,@RequestParam("constructionArea")Long constructionArea)throws Exception{
        ResultPOBean<Long> resultPOBean = new ResultPOBean();
        DmsProjectType dmsProjectType = dmsProjectTypeService.selectByCode(projectCode);
        if(dmsProjectType.getCommissionType() == 1){
            resultPOBean.success(Long.parseLong(dmsProjectType.getCommission()));
        }else if(dmsProjectType.getCommissionType() == 2){
            Calendar cal = Calendar.getInstance();
            //当前日期月份
            cal.add(Calendar.MONTH,0);
            //创建当前时间
            Date date = new Date();
            //设置时间格式为yyyy-MM-dd HH:mm:ss
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            //设置当前时间,
            cal.setTime(date);
            //打印当前时间
            System.out.println("当前时间：" + sdf.format(date));
            //获取到本月起始日
            int actualMinimum = cal.getActualMinimum(Calendar.DAY_OF_MONTH);
            //获取到本月结束日
            int actualMaximum = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
            //设置本月起始日的年月日时分秒格式
            cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONDAY),actualMinimum,00,00,00);
            //本月起始日的年月日时分秒格式
            Date startTime = cal.getTime();
            //设置本月结束日的年月日时分秒格式
            cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONDAY),actualMaximum,23,59,59);
            //本月结束日的年月日时分秒格式
            Date endTime = cal.getTime();
            Long area = dmsMainProjectService.sumAreaByUserTime(uid,startTime,endTime,projectCode);
            Long price = getPrice(dmsProjectType.getCommission(),area,constructionArea);
            resultPOBean.success(price);
        }else{
            resultPOBean.success(-1l);
        }

        return JSON.toJSONString(resultPOBean);
    }

    /**
     * 获取配置梯级分佣平均值
     * @param commission
     * @param area
     * @return
     */
    private Long getPrice(String commission, Long area,Long constructionArea) {
        if(area == null){
            area = 0l;
        }
        Long result = -1l;
        if(!StringUtils.isBlank(commission)){
            try{
                Long area2 = 0l;//面积2段
                Long commission1 = -1l;
                Long commission2 = -1l;
                List<Map<String,Integer>> jsonList = JSONObject.parseObject(commission,List.class);
                for(Map<String,Integer> obj : jsonList){
                    Integer max = obj.get("max");
                    Integer min = obj.get("min");
                    Integer commissionStr = obj.get("commission");
//                  以前面积在区间里
                    if(area < (max*100) && area >= (min*100)){
                        commission1 = commissionStr.longValue();
                    }
                    if(area + constructionArea < (max*100) && area + constructionArea >= (min*100)){
                        commission2 = commissionStr.longValue();
                        area2 = (area + constructionArea) - (min*100);
                    }
                }
                commission1 = commission1 * 100;
                commission2 = commission2 * 100;
                if(commission1 != commission2){
                    result = (commission2 * area2) + (commission1 * (constructionArea - area2));
                    result = result / constructionArea;
                }else {
                    result = commission1;
                }
            }catch (Exception e){
            }
        }
        return result;
    }

}
