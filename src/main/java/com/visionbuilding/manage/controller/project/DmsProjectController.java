package com.visionbuilding.manage.controller.project;

import com.alibaba.fastjson.JSON;
import com.visionbuilding.manage.controller.BaseController;
import com.visionbuilding.manage.dao.mapper.DmsAmountLogMapper;
import com.visionbuilding.manage.modle.ResultBean;
import com.visionbuilding.manage.modle.ResultPOBean;
import com.visionbuilding.manage.modle.ResultPOListBean;
import com.visionbuilding.manage.modle.entity.DmsAmountLog;
import com.visionbuilding.manage.modle.entity.DmsChildProject;
import com.visionbuilding.manage.modle.entity.DmsDepartment;
import com.visionbuilding.manage.modle.entity.DmsMainProject;
import com.visionbuilding.manage.modle.po.DmsMainProjectPo;
import com.visionbuilding.manage.modle.query.DepartmentEchartQuery;
import com.visionbuilding.manage.service.DmsMainProjectService;
import com.visionbuilding.manage.utill.FormDataUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.Map;

@Controller
@RequestMapping("/project/main-project")
public class DmsProjectController extends BaseController {

    @Autowired
    private DmsMainProjectService dmsMainProjectService;

    @Autowired
    private DmsAmountLogMapper dmsAmountLogMapper;

    @RequestMapping("/project.html")
    public String user(HttpServletRequest request, Long parentId){
        return "project/mainProject/project_list";
    }

    @RequestMapping("/edit.html")
    public String edit(HttpServletRequest request,Long id) throws ParseException {
        DmsMainProject dmsMainProject = dmsMainProjectService.selectByPrimaryKey(id);
        DmsMainProjectPo dmsMainProjectPo = new DmsMainProjectPo();
        BeanUtils.copyProperties(dmsMainProject,dmsMainProjectPo);
        DmsAmountLog dmsAmountLog = new DmsAmountLog();
        dmsAmountLog.setProjectId(dmsMainProject.getId());
        LocalDate today = LocalDate.now();
        LocalDate kssj = today.minusMonths(1).with(TemporalAdjusters.firstDayOfMonth());
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        SimpleDateFormat sdfs = new SimpleDateFormat("yyyy-MM-dd");
        dmsAmountLog.setEventDate(sdfs.parse(kssj.format(dtf)));
        dmsAmountLog = dmsAmountLogMapper.selectOne(dmsAmountLog);
        if(dmsAmountLog != null){
            dmsMainProjectPo.setAmountReceivableOld(dmsAmountLog.getAmountReceivable());
            dmsMainProjectPo.setAmountReturnedOld(dmsAmountLog.getAmountReturned());
            dmsMainProjectPo.setAmountsPayableOld(dmsAmountLog.getAmountsPayable());
            dmsMainProjectPo.setAmountSpentOld(dmsAmountLog.getAmountSpent());
        }
        request.setAttribute("po",dmsMainProjectPo);
        return "project/mainProject/project_edit";
    }

    @RequestMapping("/add.html")
    public String add(HttpServletRequest request){
        return "project/mainProject/project_edit";
    }

    @ResponseBody
    @RequestMapping("/getDates")
    public String getDates(DmsMainProject dmsMainProject, HttpServletRequest request)throws Exception{
        ResultPOListBean<DmsMainProject> resultPOListBean = new ResultPOListBean<>();
        //获取分页参数
        FormDataUtils.setQueryParamter(dmsMainProject, request);
        resultPOListBean = dmsMainProjectService.queryPage(dmsMainProject);
        //格式话参数
        Map<String, Object> map = FormDataUtils.getFormDataMap(resultPOListBean.getValue(), resultPOListBean.getTotalCount());
        return JSON.toJSONString(map);
    }
    @ResponseBody
    @RequestMapping("/del")
    public String del(Long id)throws Exception{
        ResultBean resultBean = new ResultBean();
        try {
            dmsMainProjectService.deleteByPrimaryKey(id);
            resultBean.success();
        }catch (Exception e){
            e.printStackTrace();
            resultBean.failure(e.getMessage());
        }
        return JSON.toJSONString(resultBean);
    }

    @ResponseBody
    @RequestMapping("/delSub")
    public String delSub(Long id)throws Exception{
        ResultBean resultBean = new ResultBean();
        try {
            dmsMainProjectService.deleteSubByPrimaryKey(id);
            resultBean.success();
        }catch (Exception e){
            e.printStackTrace();
            resultBean.failure(e.getMessage());
        }
        return JSON.toJSONString(resultBean);
    }

    @ResponseBody
    @RequestMapping("/save")
    public String save(DmsMainProject dmsMainProject)throws Exception{
        ResultBean resultBean = new ResultBean();
        try {
            if(dmsMainProject.getProduction() != null && "".equals(dmsMainProject.getProduction())) {
                // 是否生产在数据库是tinyint类型的,如果未选择传过来的值是"",会报错,手动置为null
                dmsMainProject.setProduction(null);
            }
            if(dmsMainProject.getId() == null){
                dmsMainProjectService.insertSelective(dmsMainProject);
            }else{
                dmsMainProjectService.updateByPrimaryKeySelective(dmsMainProject);
            }
            resultBean.success();
        }catch (Exception e){
            e.printStackTrace();
            resultBean.failure(e.getMessage());
        }
        return JSON.toJSONString(resultBean);
    }


    /**
     * 运营中心跳转到子项目新增页面
     * 传过来的id是主项目的id
     * @return
     */
    @RequestMapping("/addSub.html")
    public String addSubProject(HttpServletRequest request, Long id){
        DmsMainProject dmsMainProject = dmsMainProjectService.selectByPrimaryKey(id);
        DmsChildProject dmsChildProject = new DmsChildProject();
        dmsChildProject.setSalesArea(dmsMainProject.getSalesArea());
        dmsChildProject.setUnitPrice(dmsMainProject.getUnitPrice());
        dmsChildProject.setParentId(id);
        request.setAttribute("po",dmsChildProject);
        return "project/subProject/sub_project_edit";
    }

    /**
     * 运营中心跳转到子项目编辑页面
     * 传过来的id是子项目的id
     * @return
     */
    @RequestMapping("/editSubProject.html")
    public String editSubPro(HttpServletRequest request, Long id){
        DmsChildProject dmsChildProject = dmsMainProjectService.selectSubByPrimaryKey(id);
        Long parentId = dmsChildProject.getParentId();
        DmsMainProject dmsMainProject = dmsMainProjectService.selectByPrimaryKey(parentId);
        dmsChildProject.setSalesArea(dmsMainProject.getSalesArea());
//        dmsChildProject.setUnitPrice(dmsMainProject.getUnitPrice());
//        dmsChildProject.setParentId(id);
        request.setAttribute("po",dmsChildProject);
        return "project/subProject/sub_project_edit";
    }

    /**
     * 运营中心(设计院)新增或编辑子项目
     * @return
     */
    @ResponseBody
    @RequestMapping("/save-sub-project")
    public String saveSubProject(DmsChildProject dmsChildProject)throws Exception{
        ResultBean resultBean = new ResultBean();
        try {
            if(dmsChildProject.getId() == null){
                dmsMainProjectService.insertSelectiveChild(dmsChildProject);
            }else{
                dmsMainProjectService.updateByPrimaryKeySelectiveChild(dmsChildProject);
            }
            resultBean.success();
        }catch (Exception e){
            e.printStackTrace();
            resultBean.failure(e.getMessage());
        }
        return JSON.toJSONString(resultBean);
    }

    @RequestMapping("/info.html")
    public String detail(HttpServletRequest request,Long id){
        DmsMainProject dmsMainProject = dmsMainProjectService.selectByPrimaryKey(id);
        request.setAttribute("po",dmsMainProject);
        return "project/mainProject/project_info";
    }

    /**
     * 获取子项目列表
     * @param dmsChildProject
     * @param request
     * @return
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping("/getSubDatas")
    public String getSubDates(DmsChildProject dmsChildProject, HttpServletRequest request)throws Exception{
        ResultPOListBean<DmsChildProject> resultPOListBean = new ResultPOListBean<>();
        //获取分页参数
        FormDataUtils.setQueryParamter(dmsChildProject, request);
        resultPOListBean = dmsMainProjectService.querySubPage(dmsChildProject);
        //格式话参数
        Map<String, Object> map = FormDataUtils.getFormDataMap(resultPOListBean.getValue(), resultPOListBean.getTotalCount());
        return JSON.toJSONString(map);
    }
    @RequestMapping("/audit_project_list.html")
    public String homeAudit(HttpServletRequest request,Long id){
        return "sys/audit_project_list";
    }
    /**
     * 获取今天要审核子项目个数
     * @param dmsChildProject
     * @param request
     * @return
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping("/counttodaySubDates")
    public String counttodaySubDates(HttpServletRequest request)throws Exception{
        ResultPOBean<Integer> resultPOBean = new ResultPOBean<>();
        resultPOBean.success(dmsMainProjectService.counttodaySubDates());
        return JSON.toJSONString(resultPOBean);
    }

    /**
     * 获取今天要审核子项目列表
     * @param dmsChildProject
     * @param request
     * @return
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping("/gettodaySubDates")
    public String gettodaySubDates(DepartmentEchartQuery dmsChildProject, HttpServletRequest request)throws Exception{
        ResultPOListBean<DmsChildProject> resultPOListBean = new ResultPOListBean<>();
        //获取分页参数
        FormDataUtils.setQueryParamter(dmsChildProject, request);
        resultPOListBean = dmsMainProjectService.gettodaySubDates(dmsChildProject);
        //格式话参数
        Map<String, Object> map = FormDataUtils.getFormDataMap(resultPOListBean.getValue(), resultPOListBean.getTotalCount());
        return JSON.toJSONString(map);
    }

    @RequestMapping("/subProject.html")
    public String goSubProject(){
        return "project/subProject/sub_project_list";
    }

    /**
     * 设计院编辑子项目
     * @return
     */
    @RequestMapping("/editSub.html")
    public String editSubProject(HttpServletRequest request, Long id) {
        // 1.传进来子项目id,根据id查询到子项目的数据
        DmsChildProject dmsChildProject =  dmsMainProjectService.selectSubByPrimaryKey(id);

        // 2.拿到子项目里的parentId,根据这个parentId去大项目表查到大项目的数据
        Long parentId = dmsChildProject.getParentId();
        DmsMainProject dmsMainProject = dmsMainProjectService.selectByPrimaryKey(parentId);

        // 3.把大小项目的数据都set进request中,在页面中调用
        request.setAttribute("po",dmsChildProject);
        request.setAttribute("parentPo",dmsMainProject);

        return "project/subProject/sub_project_edit_write";
    }

    /**
     * 查看子项目详情
     * @param request
     * @param id
     * @return
     */
    @RequestMapping("/subInfo.html")
    public String subDetail(HttpServletRequest request,Long id){
        // 1.传进来子项目id,根据id查询到子项目的数据
        DmsChildProject dmsChildProject =  dmsMainProjectService.selectSubByPrimaryKey(id);

        // 2.拿到子项目里的parentId,根据这个parentId去大项目表查到大项目的数据
        Long parentId = dmsChildProject.getParentId();
        DmsMainProject dmsMainProject = dmsMainProjectService.selectByPrimaryKey(parentId);

        // 3.把大小项目的数据都set进request中,在页面中调用
        request.setAttribute("po",dmsChildProject);
        request.setAttribute("parentPo",dmsMainProject);
        return "project/subProject/sub_project_info";
    }


    @RequestMapping("/auditSubProject.html")
    public String goAuditSubProjectList(){
        return "project/subProject/audit_sub_project_list";
    }

    @RequestMapping("/auditSub.html")
    public String goAuditSubProject(HttpServletRequest request,Long id){
        // 1.传进来子项目id,根据id查询到子项目的数据
        DmsChildProject dmsChildProject =  dmsMainProjectService.selectSubByPrimaryKey(id);

        // 2.拿到子项目里的parentId,根据这个parentId去大项目表查到大项目的数据
        Long parentId = dmsChildProject.getParentId();
        DmsMainProject dmsMainProject = dmsMainProjectService.selectByPrimaryKey(parentId);

        // 3.把大小项目的数据都set进request中,在页面中调用
        request.setAttribute("po",dmsChildProject);
        request.setAttribute("parentPo",dmsMainProject);
        return "project/subProject/audit_sub_project";
    }

    @ResponseBody
    @RequestMapping("/audit-sub-project")
    public String auditSubProject(
            @RequestParam(required = true, name = "id") Long id,
            @RequestParam(required = true, name = "audit") String audit) throws Exception{
        ResultBean resultBean = new ResultBean();
        try {
            if(id == null){
                resultBean.failure("未获取到子项目id!");
            }else{
                if(audit == null || audit == "") {
                    resultBean.failure("未填写审核状态!");
                } else {
                    if("0".equals(audit)) { //审核通过
                        dmsMainProjectService.auditSubProject(id);
                    } else if("1".equals(audit)) { //审核不通过
                        dmsMainProjectService.noAuditSubProject(id);
                    }


                }
//                DmsChildProject dmsChildProject = new DmsChildProject();
//                dmsChildProject.setId(id);
                // 审核通过

            }
            resultBean.success();
        }catch (Exception e){
            e.printStackTrace();
            resultBean.failure(e.getMessage());
        }
        return JSON.toJSONString(resultBean);
    }
}
