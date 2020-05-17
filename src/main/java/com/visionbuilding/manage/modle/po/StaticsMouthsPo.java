package com.visionbuilding.manage.modle.po;

import lombok.Data;

@Data
public class StaticsMouthsPo {
    private Long saleAmount;
    private Long costAmount;
    private Long commissionAmount;
    private Long amountReceivable;//应收金额
    private Long amountReturned;//以回金额
    private String eventDate;
}
