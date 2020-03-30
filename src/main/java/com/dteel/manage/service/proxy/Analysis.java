package com.dteel.manage.service.proxy;

import com.dteel.manage.modle.po.DmsQuestionImport;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface Analysis {
    public List<List<DmsQuestionImport>> analysisFiel(MultipartFile importFile);
}
