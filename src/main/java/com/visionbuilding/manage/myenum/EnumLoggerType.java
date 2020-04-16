package com.visionbuilding.manage.myenum;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum EnumLoggerType {
   BIGPROJECT_ADD(1,"大项目添加"),BIGPROJECT_UPDATE(2,"大项目修改")
   ,CHILDPROJECT_ADD(3,"小项目添加"),CHILDPROJECT_UPDATE(4,"小项目修改")
   ,REPORT_ADD(5,"数据上报"),REPORT_UPDATE(6,"审核功能");
   private Integer key;
   private String text;
}
