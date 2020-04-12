package com.visionbuilding.manage.modle.po;

import lombok.Data;

import java.util.List;

@Data
public class EchartImageDataPo {
    private String name;
    private String type;
    private Integer yAxisIndex;
    private List<Long> data;
}
