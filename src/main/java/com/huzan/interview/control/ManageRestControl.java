package com.huzan.interview.control;

import com.huzan.interview.bean.BasePageBean;
import com.huzan.interview.bean.PaperModelBean;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        ManageSubjectForm form = GsonUtil.fromJsonNoDecode(json,ManageSubjectForm.class);
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
     * 更新题库
     * @param json
     * @return
     */
    @RequestMapping(value = MethodUtil.METHOD_SUBJECT_REST,method = RequestMethod.PUT)
    public String updateSubject(@RequestParam(value = "ajaxdata") String json){
        ManageSubjectForm form = GsonUtil.fromJsonNoDecode(json,ManageSubjectForm.class);
        ResultForm resultForm = new ResultForm();
        try(SqlSession session = factory.openSession(true)){
            SubjectMapper mapper = session.getMapper(SubjectMapper.class);
            mapper.subjectUpdate(form);
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
    public String getSubject(@RequestParam(value = "pageIndex",  defaultValue = "1") int pageIndex){
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
        ManageResultForm<PaperModelBean> resultForm = new ManageResultForm<>();
        resultForm.setCode(Constant.CODE_SUCCESS);

        List<PaperModelBean> list = new ArrayList();
        try(SqlSession session = factory.openSession(false)) {
            PaperModelMapper mapper = session.getMapper(PaperModelMapper.class);
            list = mapper.getPaperModel();
        }
        List outList = new ArrayList();

        for (int i = 0; i < list.size() ; i++) {
            PaperModelBean pm = list.get(i);
            Map map = new HashMap();
            map.put("jobName",pm.getJobName());
            boolean is = false;
            for (int j = 0; j < outList.size(); j++) {
                Map m = (Map) outList.get(j);
                if(m.get("jobName")!=null&&m.get("jobName").toString().equals(pm.getJobName())){
                    is=true;
                    break;
                }
            }
            if(is){
                continue;
            }

            StringBuffer sb = new StringBuffer();
            for (int j = 0; j < list.size(); j++) {
                PaperModelBean pm1 = list.get(j);
                if(map.get("jobName").toString().equals(pm1.getJobName())){
                    sb.append(pm1.getTypeName()+"("+pm1.getNum()+"题),");
                }
            }
            map.put("subject",sb.toString());
            outList.add(map);

        }

        int page = 1;
        if (outList.size() % Constant.PAGESIZE != 0) {
            page += outList.size() / Constant.PAGESIZE;
        } else {
            page = outList.size() / Constant.PAGESIZE;
        }
        resultForm.setTitle("试题模版");
        List tableRank = new ArrayList();
        tableRank.add("试题种类");
        tableRank.add("详情");
        tableRank.add("操作");
        resultForm.setTableRank(tableRank);
        resultForm.setList(outList);
        resultForm.setPage(page);
        resultForm.setSize(outList.size());


        return GsonUtil.toJsonNoEncode(resultForm);
    }

    /**
     * 新增试卷模版
     * @param json
     * @return
     */
    @RequestMapping(value = MethodUtil.METHOD_PAGEMODEL_REST, method = RequestMethod.POST)
    public String addPageModel(@RequestParam(value = "ajaxdata") String json ){
        ManageAddPageModelForm form = GsonUtil.fromJsonNoDecode(json,ManageAddPageModelForm.class);
        ResultForm resultForm = new ResultForm();
        try(SqlSession session = factory.openSession(false)){
            PaperModelMapper mapper = session.getMapper(PaperModelMapper.class);
            List<PaperModelBean> tempList = mapper.getPaperModelByJobId(form.getJobId());
            if(tempList.size()>0){
                //已存在
            }else if(tempList.size()==0){
                //不存在
                List typeNumList = form.getTypeNumList();
                List pmList = new ArrayList();
                for (int i = 0; i < typeNumList.size(); i++) {
                    Map map = (Map) typeNumList.get(i);
                    PaperModelBean pm = new PaperModelBean();
                    pm.setJobId(form.getJobId());
                    pm.setNum(Integer.parseInt(map.get("num").toString()));
                    pm.setTypeId(Integer.parseInt(map.get("id").toString()));
                    pmList.add(pm);
                }
                mapper.addPaperModel(pmList);
                System.out.println(1);
                session.commit();
                resultForm.setCode(Constant.CODE_SUCCESS);

            }
        }
        return GsonUtil.toJsonNoEncode(resultForm);
    }







}
