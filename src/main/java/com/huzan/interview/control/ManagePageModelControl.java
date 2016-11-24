package com.huzan.interview.control;

import com.huzan.interview.bean.JobBean;
import com.huzan.interview.bean.PaperModelBean;
import com.huzan.interview.bean.SubjectKindBean;
import com.huzan.interview.bean.SubjectTypeBean;
import com.huzan.interview.mapper.JobMapper;
import com.huzan.interview.mapper.PaperModelMapper;
import com.huzan.interview.mapper.SubjectKindMapper;
import com.huzan.interview.mapper.SubjectTypeMapper;
import com.huzan.interview.util.GsonUtil;
import com.huzan.interview.util.MethodUtil;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by huzan on 2016/11/22.
 */
@Controller
public class ManagePageModelControl {
    @Autowired
    SqlSessionFactory factory;
    @RequestMapping(value = MethodUtil.METHOD_ADD_PAPERMODEL,method = RequestMethod.GET)
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
        return "managePaperModel/addPaperModel";

    }

    @RequestMapping(value = MethodUtil.METHOD_UPDATE_PAPERMODEL,method = RequestMethod.GET)
    public String updatesubject(@RequestParam(value = "id",defaultValue = "0")int id, Model model){
        List<SubjectTypeBean> subjectTypeBeanList = new ArrayList<>();
        List<PaperModelBean> list = new ArrayList<>();
        JobBean jobBean = new JobBean();
        try (SqlSession session = factory.openSession()) {
            PaperModelMapper paperModelMapper = session.getMapper(PaperModelMapper.class);
            list = paperModelMapper.getPaperModelByJobId(id);
            SubjectTypeMapper subjectTypeMapper = session.getMapper(SubjectTypeMapper.class);
            subjectTypeBeanList = subjectTypeMapper.subjectTypeList();
            JobMapper jobMapper = session.getMapper(JobMapper.class);
            jobBean = jobMapper.getJobById(id);
            for (int i = 0; i < list.size(); i++) {
                PaperModelBean pm = list.get(i);
                for (int j = 0; j < subjectTypeBeanList.size(); j++) {
                    SubjectTypeBean job = subjectTypeBeanList.get(j);
                    if(job.getId()==pm.getTypeId()){
                       job.setNum(pm.getNum());
                    }
                }

            }

        }
        model.addAttribute("job",jobBean);
        model.addAttribute("typeList",subjectTypeBeanList);
        return "managePaperModel/updatePaperModel";

    }


}
