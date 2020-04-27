package com.visionbuilding.manage.modle.entity;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@RequiredArgsConstructor
public class DmsChildProject extends BaseEntity {
//    private Long id;
    /**
     * 父项目ID
     */
    private Long parentId;
    /**
     * 项目类型编码
     */
    private String projectTypeCode;
    /**
     * 项目类型
     */
    private String projectTypeName;
    /**
     * 收单日期
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date acquisitionDate;
    /**
     * 建筑面积/效果图张数
     */
    private Long constructionArea;
    /**
     * 初次交付日期
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date initialDeliveryDate;
    /**
     * 成本单价
     */
    private Long costPrice;
    /**
     * 成本总价
     */
    private Long totalCost;
    /**
     * 销售总价
     */
    private Long totalSalesPrice; //销售总价
    /**
     * 制图单位
     */
    private String draftingUnit;
    /**
     * 设计师提成
     */
    private Long designerCommission;
    /**
     * 设计师提成单价
     */
    private Long designerCommissionPrice;
    /**
     * '确认状态 0 待提交(运营中心发布出来等待被设计院填写),1 待审核(设计院填写完子项目并保存,等待被运营中心审核),2 审核通过'
     */
    private Byte confirmStatus;
    /**
     * 确认时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date confirmTime;
    /**
     * 备注
     */
    private String remarks;
    /**
     * 部门id
     */
    private Long department;

    /**
     * 效果图张数
     */
//    private Long renderingNum;

    // 以下字段仅在modle类用

    private Long salesArea; //销售面积
    private Long unitPrice; //销售单价
    // 项目编号(前端用作 业务初始建档时间显示)
    private String projectCreationTime;
    /**
     * 设计师名称
     */
    private String designer;
    /**
     * 设计师id
     */
    private Long userId;
}