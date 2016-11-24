package com.huzan.interview.mapper;

import com.huzan.interview.bean.PaperBean;

import java.util.List;


/**
 * Created by huzan on 2016/11/23.
 */
public interface PaperMapper {
    void addPaper(PaperBean paperBean);
    void endPaper(PaperBean paperBean);
    PaperBean getPaperByUserId(int userId,int jobId);
    PaperBean getTodayPaperByUserId(int userId,int jobId);
    List<PaperBean> getPaperList();
}
