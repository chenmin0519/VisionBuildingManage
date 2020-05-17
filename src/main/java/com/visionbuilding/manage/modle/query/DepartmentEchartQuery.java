package com.visionbuilding.manage.modle.query;

import com.visionbuilding.manage.modle.entity.BaseEntity;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;


@Data
@RequiredArgsConstructor
public class DepartmentEchartQuery extends BaseEntity {
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    Date startTime;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    Date endTime;
    Long department;
}
