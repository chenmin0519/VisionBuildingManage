package com.visionbuilding.manage.controller.data;

import com.alibaba.fastjson.JSONObject;
import com.visionbuilding.manage.modle.ResultPOBean;
import com.visionbuilding.manage.modle.po.EchartPo;
import com.visionbuilding.manage.service.DmsSettlementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/data/settlement")
public class DmsSettlementController {

    @Autowired
    private DmsSettlementService dmsSettlementService;

    @RequestMapping("/getEchartInfo")
    @ResponseBody
    public String getEchartInfo(){
        ResultPOBean<EchartPo> result = new ResultPOBean<>();
        EchartPo echartPo = dmsSettlementService.getEchartInfo();
        result.success(echartPo);
        return JSONObject.toJSONString(result);
    }
}
