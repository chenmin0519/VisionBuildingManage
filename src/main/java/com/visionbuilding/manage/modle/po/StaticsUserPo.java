package com.visionbuilding.manage.modle.po;

import lombok.Data;

@Data
public class StaticsUserPo {
    private Long userId;
    private String designer;
    private Long totalCost;
    private Long bonus;//奖金
    private Long fine;//罚款
    private Long wages;//工资
    private Long designerCommission;//提成
    private Long payWages;
    private Long profit;//利润
}
