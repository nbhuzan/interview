package com.huzan.interview.mapper;

import com.huzan.interview.bean.PaperBean;

import java.util.Date;

/**
 * Created by huzan on 2016/11/23.
 */
public interface PaperMapper {
    void addPaper(PaperBean paperBean);
    PaperBean getPaperByUserId(int userId,int jobId);
}
