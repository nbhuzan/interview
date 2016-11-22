package com.huzan.interview.control;

import com.huzan.interview.bean.BasePageBean;
import com.huzan.interview.bean.SubjectBean;
import com.huzan.interview.bean.UserBean;
import com.huzan.interview.form.*;
import com.huzan.interview.mapper.PaperModelMapper;
import com.huzan.interview.mapper.SubjectMapper;
import com.huzan.interview.mapper.UserMapper;
import com.huzan.interview.util.Constant;
import com.huzan.interview.util.GsonUtil;
import com.huzan.interview.util.MethodUtil;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by huzan on 2016/11/21.
 */
@RestController
public class ManageRestControl {
    @Autowired
    SqlSessionFactory factory;
    @RequestMapping(value = MethodUtil.METHOD_MANAGE_LOGIN, method = RequestMethod.POST)
    public String manageLogin(@RequestParam(value = "ajaxdata") String json) {
        ManageLoginForm form = GsonUtil.fromJsonNoDecode(json,ManageLoginForm.class);
        ResultForm resultForm = new ResultForm();
        try (SqlSession session = factory.openSession()) {
            UserMapper  mapper = session.getMapper(UserMapper.class);
            String name = form.getName();
            String password = form.getPassword();
            UserBean userBean = mapper.manageLogin(name,password);
            if(userBean!=null) {
                resultForm.setCode(Constant.CODE_SUCCESS);
            }else {
                resultForm.setCode(Constant.CODE_MANAGE_LOGIN_ERROR);
            }
        }catch (Exception e){
            e.printStackTrace();
            resultForm.setCode(Constant.CODE_ERROR);
        }
        return GsonUtil.toJsonNoEncode(resultForm);
    }


    /**
     * 新增试题
     * @param json
     * @return
     */
    @RequestMapping(value = MethodUtil.METHOD_SUBJECT_REST, method = RequestMethod.POST)
    public String manageAddSubject(@RequestParam(value = "ajaxdata") String json){
        ManageAddSubjectForm form = GsonUtil.fromJsonNoDecode(json,ManageAddSubjectForm.class);
        ResultForm resultForm = new ResultForm();
        try (SqlSession session = factory.openSession(true)) {
            SubjectMapper subjectMapper = session.getMapper(SubjectMapper.class);
           subjectMapper.subjectAdd(form);
            int id = form.getId();
            if(id>0){
                resultForm.setCode(Constant.CODE_SUCCESS);
            }
        }
        return GsonUtil.toJsonNoEncode(resultForm);
    }


    /**
     * 获取题库
     * @return
     */
    @RequestMapping(value = MethodUtil.METHOD_SUBJECT_REST, method = RequestMethod.GET)
    public String getSubject(@RequestParam(value = "pageIndex", defaultValue = "1") int pageIndex){
        ManageResultForm<SubjectBean> resultForm = new ManageResultForm<>();

        int pageIndexStart = pageIndex == 1 ? 0 : (pageIndex - 1) * Constant.PAGESIZE + 1;
        int pageIndexEnd = pageIndex == 1 ? Constant.PAGESIZE : pageIndex * Constant.PAGESIZE;
        List<SubjectBean> listBean = new ArrayList<>();
        try (SqlSession session = factory.openSession()) {
            SubjectMapper subjectMapper = session.getMapper(SubjectMapper.class);
            BasePageBean pageBean = new BasePageBean();
            pageBean.setPageIndexStart(pageIndexStart);
            pageBean.setPageIndexEnd(pageIndexEnd);
            listBean = subjectMapper.subjectList(pageBean);
        }
        int page = 1;
        if (listBean.size() % Constant.PAGESIZE != 0) {
            page += listBean.size() / Constant.PAGESIZE;
        } else {
            page = listBean.size() / Constant.PAGESIZE;
        }


        List tableRank = new ArrayList();
        tableRank.add("描述");
        tableRank.add("题型");
        tableRank.add("种类");
        tableRank.add("标准答案");
        tableRank.add("答案数量");
        tableRank.add("操作");

        resultForm.setCode(Constant.CODE_SUCCESS);
        resultForm.setTitle("题库");
        resultForm.setTableRank(tableRank);
        resultForm.setList(listBean);
        resultForm.setPage(page);
        resultForm.setSize(listBean.size());


        return GsonUtil.toJsonNoEncode(resultForm);
    }


    /**
     * 获取试卷模版
     * @return
     */
    @RequestMapping(value = MethodUtil.METHOD_PAGEMODEL_REST, method = RequestMethod.GET)
    public String getPageModel(){
        ManageResultForm<UserBean> resultForm = new ManageResultForm<>();
        resultForm.setCode(Constant.CODE_SUCCESS);

        List list = new ArrayList();

        resultForm.setTitle("试题模版");
        List tableRank = new ArrayList();
        tableRank.add("试题种类");
        tableRank.add("详情");
        resultForm.setTableRank(tableRank);
        resultForm.setList(list);



        return GsonUtil.toJsonNoEncode(resultForm);
    }

    @RequestMapping(value = MethodUtil.METHOD_PAGEMODEL_REST, method = RequestMethod.POST)
    public String addPageModel(@RequestParam(value = "ajaxdata") String json ){
        ManageAddPageModelForm form = GsonUtil.fromJsonNoDecode(json,ManageAddPageModelForm.class);
        try(SqlSession session = factory.openSession(false)){
            PaperModelMapper mapper = session.getMapper(PaperModelMapper.class);

        }
        return "";
    }





}
