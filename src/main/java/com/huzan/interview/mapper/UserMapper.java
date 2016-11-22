package com.huzan.interview.mapper;

import com.huzan.interview.bean.UserBean;

/**
 * Created by huzan on 2016/11/21.
 */
public interface UserMapper {
     UserBean manageLogin(String name, String password);
}
