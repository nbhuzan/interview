package com.huzan.interview.mapper;

import com.huzan.interview.bean.BasePageBean;
import com.huzan.interview.bean.SubjectBean;
import com.huzan.interview.form.ManageSubjectForm;

import java.util.List;

/**
 * Created by huzan on 2016/11/21.
 */
public interface SubjectMapper {
    List<SubjectBean> subjectList(BasePageBean pageBean);
    List<SubjectBean> getSubjectByTypeId(int typeId);
    int subjectAdd(ManageSubjectForm form);
    SubjectBean getSubject(int id);
    void subjectUpdate(ManageSubjectForm form);

}
