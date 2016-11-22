package com.huzan.interview.control;

import com.huzan.interview.bean.SubjectBean;
import com.huzan.interview.bean.SubjectKindBean;
import com.huzan.interview.bean.SubjectTypeBean;
import com.huzan.interview.bean.UserBean;
import com.huzan.interview.mapper.SubjectKindMapper;
import com.huzan.interview.mapper.SubjectMapper;
import com.huzan.interview.mapper.SubjectTypeMapper;
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

import static com.sun.tools.doclint.Entity.sub;

/**
 * Created by huzan on 2016/11/22.
 */
@Controller
public class ManageSubjectControl {
    @Autowired
    SqlSessionFactory factory;
    @RequestMapping(value = MethodUtil.METHOD_ADD_SUBJECT,method = RequestMethod.GET)
    public String addsubject(Model model){
        List<SubjectKindBean> subjectKindBeanList = new ArrayList<>();
        List<SubjectTypeBean> subjectTypeBeanList = new ArrayList<>();
        try (SqlSession session = factory.openSession()) {
            SubjectKindMapper subjectKindMapper = session.getMapper(SubjectKindMapper.class);
            SubjectTypeMapper subjectTypeMapper = session.getMapper(SubjectTypeMapper.class);
            subjectKindBeanList = subjectKindMapper.subjectKindList();
            subjectTypeBeanList = subjectTypeMapper.subjectTypeList();
        }


        model.addAttribute("kindList",subjectKindBeanList);
        model.addAttribute("typeList",subjectTypeBeanList);

        return "manageSubject/addSubject";

    }


@RequestMapping(value = MethodUtil.METHOD_UPDATE_SUBJECT,method = RequestMethod.GET)
    public String updateSubject(@RequestParam(value = "id",defaultValue ="0") int id,
                                Model model){
        List<SubjectKindBean> subjectKindBeanList = new ArrayList<>();
        List<SubjectTypeBean> subjectTypeBeanList = new ArrayList<>();
        SubjectBean subjectBean = new SubjectBean();
        try (SqlSession session = factory.openSession()) {
            SubjectKindMapper subjectKindMapper = session.getMapper(SubjectKindMapper.class);
            SubjectTypeMapper subjectTypeMapper = session.getMapper(SubjectTypeMapper.class);
            subjectKindBeanList = subjectKindMapper.subjectKindList();
            subjectTypeBeanList = subjectTypeMapper.subjectTypeList();
            SubjectMapper subjectMapper = session.getMapper(SubjectMapper.class);
            subjectBean = subjectMapper.getSubject(id);
        }

        model.addAttribute("kindList",subjectKindBeanList);
        model.addAttribute("typeList",subjectTypeBeanList);
        model.addAttribute("info",subjectBean);

        return "manageSubject/updateSubject";

    }


}
