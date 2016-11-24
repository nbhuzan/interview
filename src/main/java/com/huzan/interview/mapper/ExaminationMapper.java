package com.huzan.interview.mapper;

import com.huzan.interview.bean.ExaminationBean;
import com.huzan.interview.bean.PaperBean;

import java.util.List;

/**
 * Created by huzan on 2016/11/23.
 */
public interface ExaminationMapper {
    void addExamination(List<ExaminationBean> examinationBeanList);
    List<ExaminationBean> getExaminationBypaperId(int paperId);
    List<ExaminationBean> getExaminationRecordBypaperId(int paperId);
    void submitRespond(ExaminationBean examinationBean);
}
