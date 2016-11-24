package com.huzan.interview.control;

import com.huzan.interview.bean.*;
import com.huzan.interview.form.LoginForm;
import com.huzan.interview.form.ResultForm;
import com.huzan.interview.form.SubmitSubjectForm;
import com.huzan.interview.mapper.*;
import com.huzan.interview.util.*;
import org.apache.catalina.User;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by huzan on 2016/11/23.
 */
@RestController
public class RestControl {
    @Autowired
    SqlSessionFactory factory;

    @RequestMapping(value = MethodUtil.METHOD_LOGIN_REST, method = RequestMethod.POST)
    public String login(@RequestParam(value = "ajaxdata") String json) {
        LoginForm form = GsonUtil.fromJsonNoDecode(json, LoginForm.class);
        ResultForm resultForm = new ResultForm();
        try (SqlSession session = factory.openSession(false)) {
            PaperMapper paperMapper = session.getMapper(PaperMapper.class);
            UserMapper mapper = session.getMapper(UserMapper.class);


            //判断用户表中是否有，insertorupdate
            UserBean userBean = mapper.selectUserByNameAndPhone(form);
            if (userBean == null) {
                mapper.saveUser(form);
                resultForm.setCode(Constant.CODE_SUCCESS);
                resultForm.setMsg(GsonUtil.toJsonNoEncode(form));
                session.commit();
            } else {
                //意外情况（eg:停电，不小心关闭网页）获取当天试卷
                PaperBean pb = paperMapper.getPaperByUserId(userBean.getId(), form.getJobId());
                if (pb != null) {
                    //以前笔试过
                    resultForm.setCode(Constant.CODE_ERROR);
                    resultForm.setMsg("以前参加过笔试，请告知管理员");
                } else {
                    form.setId(userBean.getId());
                    resultForm.setCode(Constant.CODE_SUCCESS);
                    resultForm.setMsg(GsonUtil.toJsonNoEncode(form));
                }
            }

        }

        return GsonUtil.toJsonNoEncode(resultForm);
    }


    /**
     * 提交答案，每题一提交
     *
     * @param json
     * @return
     */
    @RequestMapping(value = MethodUtil.METHOD_EXAMINATION, method = RequestMethod.PUT)
    public String updateSubject(@RequestParam(value = "ajaxdata") String json) {
        SubmitSubjectForm form = GsonUtil.fromJsonNoDecode(json, SubmitSubjectForm.class);
        ResultForm resultForm = new ResultForm();
        try (SqlSession session = factory.openSession(false)) {
            SubjectMapper subjectMapper = session.getMapper(SubjectMapper.class);
            ExaminationMapper examinationMapper = session.getMapper(ExaminationMapper.class);
            PaperMapper paperMapper = session.getMapper(PaperMapper.class);

            SubjectBean subjectBean = subjectMapper.getSubject(form.getSubjectId());
            //比对答案，多选答案按照|隔开
            String answer = subjectBean.getAnswer();
            String[] answers = answer.split("|");
            String[] responds = form.getRespond().split("|");
            double score = 0;
            for (int i = 0; i < responds.length; i++) {
                String r = responds[i];
                for (int j = 0; j < answers.length; j++) {
                    String a = answers[j];
                    if (r.toUpperCase().equals(a)) {
                        score += 1;
                        break;
                    }
                }
            }
            ExaminationBean eBean = new ExaminationBean();
            eBean.setId(form.getId());
            eBean.setRespond(form.getRespond());
            eBean.setScore(score);
            examinationMapper.submitRespond(eBean);

            PaperBean paperBean = new PaperBean();
            paperBean.setId(form.getPaperId());
            paperBean.setEndTime(TimeFormatUtil.TimeFormat(System.currentTimeMillis()));
            paperMapper.endPaper(paperBean);
            session.commit();
            resultForm.setCode(Constant.CODE_SUCCESS);
        }

        return GsonUtil.toJsonNoEncode(resultForm);
    }
}
