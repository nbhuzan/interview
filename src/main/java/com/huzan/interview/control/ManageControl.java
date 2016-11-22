package com.huzan.interview.control;

import com.huzan.interview.bean.BasePageBean;
import com.huzan.interview.bean.SubjectBean;
import com.huzan.interview.mapper.SubjectMapper;
import com.huzan.interview.util.Constant;
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
 * Created by huzan on 2016/11/21.
 */
@Controller
public class ManageControl {
    @Autowired
    SqlSessionFactory factory;

    @RequestMapping(value = "manage/manage", method = RequestMethod.GET)
    public String index(@RequestParam(value = "pageIndex", defaultValue = "1") int pageIndex,
                        Model model) {
//        int pageIndexStart = pageIndex == 1 ? 0 : (pageIndex - 1) * Constant.PAGESIZE + 1;
//        int pageIndexEnd = pageIndex == 1 ? Constant.PAGESIZE : pageIndex * Constant.PAGESIZE;
//        List<SubjectBean> listBean = new ArrayList<>();
//        try (SqlSession session = factory.openSession()) {
//            SubjectMapper subjectMapper = session.getMapper(SubjectMapper.class);
//            BasePageBean pageBean = new BasePageBean();
//            pageBean.setPageIndexStart(pageIndexStart);
//            pageBean.setPageIndexEnd(pageIndexEnd);
//            listBean = subjectMapper.subjectList(pageBean);
//        }
//        int page = 1;
//        if (listBean.size() % Constant.PAGESIZE != 0) {
//            page += listBean.size() / Constant.PAGESIZE;
//        } else {
//            page = listBean.size() / Constant.PAGESIZE;
//        }
//        List tableRank = new ArrayList();
//        tableRank.add("描述");
//        tableRank.add("题型");
//        tableRank.add("种类");
//        tableRank.add("标准答案");
//        tableRank.add("答案数量");
//        tableRank.add("操作");
//        model.addAttribute("tableRank", tableRank);  //table列名
//        model.addAttribute("list", listBean);  //数据
//        model.addAttribute("max", page);  //页数
//        model.addAttribute("size", listBean.size()); //总数
//        model.addAttribute("title", "题库"); //标题
        return "manage/manage";
    }

}
