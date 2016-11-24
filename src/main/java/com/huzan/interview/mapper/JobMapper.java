package com.huzan.interview.mapper;

import com.huzan.interview.bean.JobBean;

import java.util.List;

/**
 * Created by huzan on 2016/11/22.
 */
public interface JobMapper {
    List<JobBean> getJob();
    JobBean getJobById(int id);
}
