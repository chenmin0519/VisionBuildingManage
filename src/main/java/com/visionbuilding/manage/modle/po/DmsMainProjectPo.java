package com.visionbuilding.manage.modle.po;

import com.visionbuilding.manage.modle.entity.DmsMainProject;
import lombok.Data;

@Data
public class DmsMainProjectPo extends DmsMainProject {
    private Long amountReturnedOld = 0l;
    private Long amountReceivableOld = 0l;
    private Long amountSpentOld = 0l;
    private Long amountsPayableOld = 0l;
}
