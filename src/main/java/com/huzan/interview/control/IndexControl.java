package com.huzan.interview.control;

import com.huzan.interview.bean.JobBean;
import com.huzan.interview.bean.UserBean;
import com.huzan.interview.mapper.JobMapper;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
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

    @RequestMapping("/manage")
    public String manageIndex() {
        return "index/manageIndex";
    }

}

