package com.huzan.interview.control;

import com.huzan.interview.bean.*;
import com.huzan.interview.form.*;
import com.huzan.interview.mapper.*;
import com.huzan.interview.util.Constant;
import com.huzan.interview.util.GsonUtil;
import com.huzan.interview.util.MethodUtil;
import com.huzan.interview.util.TimeFormatUtil;
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
     * 禁用题库
     * @param json
     * @return
     */
    @RequestMapping(value = "manage/deleteSubject",method = RequestMethod.GET)
    public String deleteSubject(@RequestParam(value = "ajaxdata") String json){
        ManageSubjectForm form = GsonUtil.fromJsonNoDecode(json,ManageSubjectForm.class);
        ResultForm resultForm = new ResultForm();
        try(SqlSession session = factory.openSession(true)){
            SubjectMapper mapper = session.getMapper(SubjectMapper.class);
            SubjectBean bean = mapper.getSubject(form.getId());
            form.setAnswer(bean.getAnswer());
            form.setAnswerNum(bean.getAnswerNum());
            form.setDesc(bean.getDescription());
            form.setKind(bean.getKindId());
            form.setType(bean.getTypeId());
            form.setDel(Constant.SUBJECT_DEL_Y);
            mapper.subjectUpdate(form);
            resultForm.setCode(Constant.CODE_SUCCESS);
        }
        return GsonUtil.toJsonNoEncode(resultForm);
    }

    /**
     * 解禁题库
     * @param json
     * @return
     */
    @RequestMapping(value = MethodUtil.METHOD_SUBJECT_REST,method = RequestMethod.PATCH)
    public String disDeleteSubject(@RequestParam(value = "ajaxdata") String json){
        ManageSubjectForm form = GsonUtil.fromJsonNoDecode(json,ManageSubjectForm.class);
        ResultForm resultForm = new ResultForm();
        try(SqlSession session = factory.openSession(true)){
            SubjectMapper mapper = session.getMapper(SubjectMapper.class);
            SubjectBean bean = mapper.getSubject(form.getId());
            form.setAnswer(bean.getAnswer());
            form.setAnswerNum(bean.getAnswerNum());
            form.setDesc(bean.getDescription());
            form.setKind(bean.getKindId());
            form.setType(bean.getTypeId());
            form.setDel(Constant.SUBJECT_DEL_N);
            mapper.subjectUpdate(form);
            resultForm.setCode(Constant.CODE_SUCCESS);
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
    @RequestMapping(value = MethodUtil.METHOD_PAPERMODEL_REST, method = RequestMethod.GET)
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
            map.put("id",pm.getJobId());
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
    @RequestMapping(value = MethodUtil.METHOD_PAPERMODEL_REST, method = RequestMethod.POST)
    public String addPaperModel(@RequestParam(value = "ajaxdata") String json ){
        ManagePageModelForm form = GsonUtil.fromJsonNoDecode(json,ManagePageModelForm.class);
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

    /**
     * 修改试卷模版
     * @param json
     * @return
     */
    @RequestMapping(value = MethodUtil.METHOD_PAPERMODEL_REST, method = RequestMethod.PUT)
    public String updatePaperModel(@RequestParam(value = "ajaxdata") String json ){
        ManagePageModelForm form = GsonUtil.fromJsonNoDecode(json,ManagePageModelForm.class);
        ResultForm resultForm = new ResultForm();
        try(SqlSession session = factory.openSession(false)){
            PaperModelMapper mapper = session.getMapper(PaperModelMapper.class);
            int x = mapper.delPaperModel(form.getJobId());
            System.out.println(x);
            if(x==form.getTypeNumList().size()){
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

    /**
     * 获取考试记录
     * @return
     */
    @RequestMapping(value = MethodUtil.METHOD_RECORD_REST, method = RequestMethod.GET)
    public String getRecord(){
        ManageResultForm<PaperBean> resultForm = new ManageResultForm<>();
        List<PaperBean> paperList = new ArrayList<>();
        try(SqlSession session = factory.openSession()){
            PaperMapper paperMapper = session.getMapper(PaperMapper.class);
            ExaminationMapper examinationMapper = session.getMapper(ExaminationMapper.class);
            paperList = paperMapper.getPaperList();
            for (int i = 0; i < paperList.size() ; i++) {
                PaperBean p = paperList.get(i);



                double score = 0;
                int answerNum = 0;
                List<ExaminationBean> subjectList =  examinationMapper.getExaminationRecordBypaperId(p.getId());
                for (int j = 0; j < subjectList.size(); j++) {
                    ExaminationBean eb = subjectList.get(j);
                    score+= eb.getScore();
                    answerNum += eb.getAnswerNum();
                }
                p.setScore(new Double(score/answerNum*100).intValue());
                if(p.getStartTime()!=null){
                    p.setsTime(TimeFormatUtil.dateToString(p.getStartTime(),1));
                }else{
                    p.setsTime("");
                }
                if (p.getEndTime() != null) {
                    p.seteTime(TimeFormatUtil.dateToString(p.getEndTime(), 1));
                } else {
                    p.seteTime("");
                }
            }

        }
        int page = 1;
        if (paperList.size() % Constant.PAGESIZE != 0) {
            page += paperList.size() / Constant.PAGESIZE;
        } else {
            page = paperList.size() / Constant.PAGESIZE;
        }
        resultForm.setCode(Constant.CODE_SUCCESS);
        resultForm.setTitle("考试记录");
        List tableRank = new ArrayList();
        tableRank.add("姓名");
        tableRank.add("电话");
        tableRank.add("申请岗位");
        tableRank.add("得分");
        tableRank.add("开始时间");
        tableRank.add("结束时间");
        resultForm.setTableRank(tableRank);
        resultForm.setList(paperList);
        resultForm.setPage(page);
        resultForm.setSize(paperList.size());
        return GsonUtil.toJsonNoEncode(resultForm);
    }







}
