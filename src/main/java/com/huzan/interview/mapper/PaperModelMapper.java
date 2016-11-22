package com.huzan.interview.mapper;

import com.huzan.interview.bean.PaperModelBean;

import java.util.List;

/**
 * Created by huzan on 2016/11/22.
 */
public interface PaperModelMapper {
    List<PaperModelBean> getPaperModelByJobId(int jobId);
}
