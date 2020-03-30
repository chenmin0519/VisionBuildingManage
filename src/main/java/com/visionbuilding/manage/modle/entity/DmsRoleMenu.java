package com.visionbuilding.manage.modle.entity; /***********************************************************************
 * Module:  TYjsCsglXtglRoleMenu.java
 * Author:  wisedu
 * Purpose: Defines the Class TYjsCsglXtglRoleMenu
 ***********************************************************************/

import lombok.Data;

import java.util.*;

/** 角色和权限关系表 */
@Data
public class DmsRoleMenu {
   public Long id;
   /** 角色WID */
   public Long roleId;
   /** 权限WID */
   public Long menuId;

}