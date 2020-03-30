package com.visionbuilding.manage.modle.entity;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class DmsMenu extends BaseEntity {
    /** 菜单名称 */
    private String menuName;
    /** 父级菜单 */
    private Long menuPid;
    /** 等级 */
    private String lv;
    /** URL */
    private String menuUrl;
    /** 序号 */
    private String num;
    /** 小图标 */
    private String icon;

}
