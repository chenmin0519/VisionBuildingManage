package com.visionbuilding.manage.service.impl;

import com.visionbuilding.manage.dao.mapper.DmsSettlementMapper;
import com.visionbuilding.manage.modle.entity.DmsSettlement;
import com.visionbuilding.manage.modle.po.EchartImageDataPo;
import com.visionbuilding.manage.modle.po.EchartPo;
import com.visionbuilding.manage.modle.po.StaticsMouthsPo;
import com.visionbuilding.manage.service.DmsSettlementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class DmsSettlementServiceImpl implements DmsSettlementService {

    @Autowired
    private DmsSettlementMapper dmsSettlementMapper;

    @Override
    public EchartPo getEchartInfo() {
        EchartPo echartPo = new EchartPo();
        LocalDate endTime = LocalDate.now();
        LocalDate startTime = LocalDate.now().minusMonths(12);
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM");
        List<StaticsMouthsPo> dmsSettlements = dmsSettlementMapper.selectOnYearData(startTime.format(dtf),endTime.format(dtf));
        //表数据变量
        List<String> dataStr = new ArrayList<>();
        List<Long> saleData = new ArrayList<>();
        List<Long> costData = new ArrayList<>();
        List<Long> commistionData = new ArrayList<>();
        List<EchartImageDataPo> echartImageDataPos = new ArrayList<>();
        //坐标参数需要变量
        Long leftYMin = 0L;
        Long rigthYMin = 0L;
        Long leftMax = 0L;
        Long rigthMax = 0L;
        for(StaticsMouthsPo staticsMouthsPo : dmsSettlements){
            staticsMouthsPo.setSaleAmount(staticsMouthsPo.getSaleAmount()/100);
            staticsMouthsPo.setCommissionAmount(staticsMouthsPo.getCommissionAmount()/100);
            staticsMouthsPo.setCostAmount(staticsMouthsPo.getCostAmount()/100);
            dataStr.add(staticsMouthsPo.getEventDate());
            saleData.add(staticsMouthsPo.getSaleAmount());
            costData.add(staticsMouthsPo.getCostAmount());
            commistionData.add(staticsMouthsPo.getSaleAmount()-staticsMouthsPo.getCostAmount());
            leftYMin = getLeftMin(leftYMin,staticsMouthsPo.getSaleAmount(),staticsMouthsPo.getCostAmount());
            if(rigthYMin == 0 || (staticsMouthsPo.getSaleAmount()-staticsMouthsPo.getCostAmount()) < rigthYMin){
                rigthYMin = staticsMouthsPo.getSaleAmount()-staticsMouthsPo.getCostAmount();
            }
            leftMax = getLeftMax(leftMax,staticsMouthsPo.getSaleAmount(),staticsMouthsPo.getCostAmount());
            if(rigthMax == 0 || (staticsMouthsPo.getSaleAmount()-staticsMouthsPo.getCostAmount()) > rigthMax){
                rigthMax = staticsMouthsPo.getSaleAmount()-staticsMouthsPo.getCostAmount();
            }
        }
        //初始化表数据
        EchartImageDataPo echartImageDataPo = new EchartImageDataPo();
        echartImageDataPo.setName("设计成本");
        echartImageDataPo.setType("bar");
        echartImageDataPo.setData(saleData);
        echartImageDataPos.add(echartImageDataPo);
        echartImageDataPo = new EchartImageDataPo();
        echartImageDataPo.setName("销售额");
        echartImageDataPo.setType("bar");
        echartImageDataPo.setData(costData);
        echartImageDataPos.add(echartImageDataPo);
        echartImageDataPo = new EchartImageDataPo();
        echartImageDataPo.setName("利润");
        echartImageDataPo.setType("line");
        echartImageDataPo.setYAxisIndex(1);
        echartImageDataPo.setData(commistionData);
        echartImageDataPos.add(echartImageDataPo);
        echartPo.setSeries(echartImageDataPos);
        //下标数据
        echartPo.setDataStr(dataStr);
        //XY坐标数据
        echartPo.setLeftYMin(getDonwInteger(leftYMin));
        echartPo.setLeftMax(getUpInteger(leftMax));
        echartPo.setRigthMax(getUpInteger(rigthMax));
        echartPo.setRigthYMin(getDonwInteger(rigthYMin));
        echartPo.setLeftInterval((leftMax-leftYMin)/5);
        echartPo.setRightInterval((rigthMax-rigthYMin)/5);
        return echartPo;
    }

    /**
     * 做坐标最大值
     * @param leftMax
     * @param saleAmount
     * @param costAmount
     * @return
     */
    private Long getLeftMax(Long leftMax, Long saleAmount, Long costAmount) {
        if(leftMax != 0){
            if(leftMax > saleAmount){
                if(leftMax > costAmount){
                    return leftMax;
                }else{
                    return costAmount;
                }
            }else {
                if(saleAmount > costAmount){
                    return saleAmount;
                }else{
                    return costAmount;
                }
            }
        }else{
            if(saleAmount > costAmount){
                return saleAmount;
            }else{
                return costAmount;
            }
        }
    }

    /**
     * 获取左坐标最小值
     * @param leftYMin
     * @param saleAmount
     * @param costAmount
     * @return
     */
    private Long getLeftMin(Long leftYMin, Long saleAmount, Long costAmount) {
        if(leftYMin != 0){
            if(leftYMin < saleAmount){
                if(leftYMin < costAmount){
                    return leftYMin;
                }else{
                    return costAmount;
                }
            }else {
                if(saleAmount < costAmount){
                    return saleAmount;
                }else{
                    return costAmount;
                }
            }
        }else{
            if(saleAmount < costAmount){
                return saleAmount;
            }else{
                return costAmount;
            }
        }
    }


    /**
     * 向下取整
     * @param num
     * @return
     */
    private Long getDonwInteger(Long num){
        long i = 10l;
        long temp = 0l;
        while(num/i != 0){
            temp = num / i;
            i = i * 10;
        }
        if(temp == 0){
            return 10l;
        }else{
            return temp * i/10;
        }
    }

    /**
     * 向上去整
     * @param num
     * @return
     */
    private Long getUpInteger(Long num){
        long i = 10l;
        long temp = 0l;
        while(num/i != 0){
            temp = num / i;
            i = i * 10;
        }
        if(temp == 0){
            return 10l;
        }else{
            return (temp+1) * i/10;
        }
    }
}
