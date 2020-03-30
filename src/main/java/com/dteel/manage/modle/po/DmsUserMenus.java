package com.dteel.manage.modle.po;

import com.dteel.manage.modle.entity.DmsMenu;
import lombok.Data;

import java.util.List;

@Data
public class DmsUserMenus extends DmsMenu {

    /**
     * 子集菜单
     */
    private List<DmsMenu> chrildMenus;

    public List<DmsMenu> getChrildMenus() {
        return chrildMenus;
    }

    public void setChrildMenus(List<DmsMenu> chrildMenus) {
        this.chrildMenus = chrildMenus;
    }
}
