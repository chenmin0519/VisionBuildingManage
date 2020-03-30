package com.visionbuilding.manage.myenum;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum EnumStatus {
   YES(1,"正常，是"),NO(0,"删除，否"),OR(2,"冻结");
   private Integer key;
   private String text;
}
