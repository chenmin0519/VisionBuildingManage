package com.visionbuilding.manage.modle.po;

import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class EchartPo {
    private List<String> dataStr;
    private Long leftYMin;
    private Long rigthYMin;
    private Long leftMax;
    private Long rigthMax;
    private Long leftInterval;
    private Long rightInterval;
    private List<EchartImageDataPo> series;
    private Long allSale;
    private Long allCost;
    private Long allPrifx;
}
