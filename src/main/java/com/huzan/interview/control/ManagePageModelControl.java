package com.huzan.interview.control;

import com.huzan.interview.bean.JobBean;
import com.huzan.interview.bean.SubjectKindBean;
import com.huzan.interview.bean.SubjectTypeBean;
import com.huzan.interview.mapper.JobMapper;
import com.huzan.interview.mapper.SubjectKindMapper;
import com.huzan.interview.mapper.SubjectTypeMapper;
import com.huzan.interview.util.MethodUtil;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by huzan on 2016/11/22.
 */
@Controller
public class ManagePageModelControl {
    @Autowired
    SqlSessionFactory factory;
    @RequestMapping(value = MethodUtil.METHOD_ADD_PAGEMODEL,method = RequestMethod.GET)
    public String addsubject(Model model){
        List<SubjectTypeBean> subjectTypeBeanList = new ArrayList<>();
        List<JobBean> jobBeanList = new ArrayList<>();
        try (SqlSession session = factory.openSession()) {
            SubjectTypeMapper subjectTypeMapper = session.getMapper(SubjectTypeMapper.class);
            subjectTypeBeanList = subjectTypeMapper.subjectTypeList();
            JobMapper jobMapper = session.getMapper(JobMapper.class);
            jobBeanList = jobMapper.getJob();
        }


        model.addAttribute("typeList",subjectTypeBeanList);
        model.addAttribute("jobList",jobBeanList);
        return "managePageModel/addPageModel";

    }
}
