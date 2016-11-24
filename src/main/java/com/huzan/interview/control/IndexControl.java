package com.huzan.interview.control;

import com.huzan.interview.bean.*;
import com.huzan.interview.form.LoginForm;
import com.huzan.interview.mapper.*;
import com.huzan.interview.util.GsonUtil;
import com.huzan.interview.util.MethodUtil;
import com.huzan.interview.util.RandomUtil;
import com.huzan.interview.util.TimeFormatUtil;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by huzan on 2016/11/21.
 */
@Controller
public class IndexControl {
    @Autowired
    SqlSessionFactory factory;

    @RequestMapping("/")
    public String index(Model model) {
        List list = new ArrayList();
        try (SqlSession session = factory.openSession()) {
            JobMapper jobMapper = session.getMapper(JobMapper.class);
            list = jobMapper.getJob();
        }
        model.addAttribute("list", list);
        return "index/index";
    }


    @RequestMapping(value = MethodUtil.METHOD_EXAMINATION, method = RequestMethod.GET)
    public String startExamination(@ModelAttribute LoginForm form) {
        List<ExaminationBean> ebList = new ArrayList<>();//试题列表（考试）
        try (SqlSession session = factory.openSession(false)) {
            PaperMapper paperMapper = session.getMapper(PaperMapper.class);
            PaperModelMapper paperModelMapper = session.getMapper(PaperModelMapper.class);
            SubjectMapper subjectMapper = session.getMapper(SubjectMapper.class);
            ExaminationMapper examinationMapper = session.getMapper(ExaminationMapper.class);


            //意外情况（eg:停电，不小心关闭网页）获取当天试卷
            PaperBean pb = paperMapper.getPaperByUserId(form.getId(),form.getJobId());
            if (pb != null) {
                ebList = examinationMapper.getExaminationBypaperId(pb.getId());
                System.out.println(1);
            } else {

                //创建paper
                PaperBean paperBean = new PaperBean();
                paperBean.setUserId(form.getId());
                paperBean.setJobId(form.getJobId());
                paperBean.setStartTime(TimeFormatUtil.TimeFormat(System.currentTimeMillis()));
                paperMapper.addPaper(paperBean);

                //获取试题模版，

                List<PaperModelBean> paperModelBeanList = paperModelMapper.getPaperModelByJobId(form.getJobId());
                List<ExaminationBean> listSubjectId = new ArrayList<>();
                for (int i = 0; i < paperModelBeanList.size(); i++) {
                    PaperModelBean pm = paperModelBeanList.get(i);
                    int pmCount = pm.getNum();
                    int typeId = pm.getTypeId();
                    List<SubjectBean> listSubject = subjectMapper.getSubjectByTypeId(typeId);
                    System.out.println(1);
                    if (listSubject.size() > 0) {
                        for (int j = 0; j < pmCount; j++) {
                            SubjectBean subjectBean = listSubject.get(RandomUtil.getRandom(listSubject.size() - 1));
                            ExaminationBean eBean = new ExaminationBean();
                            eBean.setPaperId(paperBean.getId());
                            eBean.setSubjectId(subjectBean.getId());
                            listSubjectId.add(eBean);
                        }
                    }
                }

                examinationMapper.addExamination(listSubjectId);
                ebList = examinationMapper.getExaminationBypaperId(paperBean.getId());
//            session.commit();
            }
        }
        return "";
    }


}

