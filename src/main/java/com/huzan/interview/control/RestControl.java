package com.huzan.interview.control;

import com.huzan.interview.bean.*;
import com.huzan.interview.form.LoginForm;
import com.huzan.interview.form.ResultForm;
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
                PaperBean pb = paperMapper.getPaperByUserId(userBean.getId(),form.getJobId());
                if (pb == null) {
                    //以前笔试过
                    resultForm.setCode(Constant.CODE_ERROR);
                    resultForm.setMsg("以前参加过笔试，请告知管理员");
                }else{
                    form.setId(userBean.getId());
                    resultForm.setCode(Constant.CODE_SUCCESS);
                    resultForm.setMsg(GsonUtil.toJsonNoEncode(form));
                }
            }

        }

        return GsonUtil.toJsonNoEncode(resultForm);
    }
}
