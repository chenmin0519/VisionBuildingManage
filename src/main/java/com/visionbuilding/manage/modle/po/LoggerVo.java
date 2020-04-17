package com.visionbuilding.manage.modle.po;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class LoggerVo<T> {
    private Long id;
    private Integer type;
    private T oldValue;
    private T newValue;
    private Long user;
}
