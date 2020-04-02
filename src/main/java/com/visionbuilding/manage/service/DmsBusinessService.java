package com.visionbuilding.manage.service;

import com.visionbuilding.manage.modle.ResultPOListBean;
import com.visionbuilding.manage.modle.entity.DmsBusiness;

public interface DmsBusinessService {
    DmsBusiness selectByPrimaryKey(Long id);
    void deleteByPrimaryKey(Long id);
    void insertSelective(DmsBusiness param);
    void updateByPrimaryKeySelective(DmsBusiness params);
    ResultPOListBean<DmsBusiness> queryPage(DmsBusiness department);
}
