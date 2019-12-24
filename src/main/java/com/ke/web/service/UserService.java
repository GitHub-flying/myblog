package com.ke.web.service;

import com.ke.web.domain.dto.UserDto;
import com.ke.web.entity.User;
import com.ke.web.util.Result;

import java.util.List;
import java.util.Map;

public interface UserService {
    /**
     * 用户登录功能
     *
     * @param userDto
     * @return Result
     */
    Result signIn(UserDto userDto);

    /*
    注册新用户
     */
    Result registered(UserDto userDto);
    /*
    分页获取用户信息
     */
    List<User> getUsers();

    /**
     * 获取热门用户信息
     * @return Result
     */
    Result getHotUsers();
    /**
     * 根据id查询用户详情数据
     * @param id
     * @return Result
     */
    Result getUser(long id);
    /**
     * 根据昵称或简介模糊搜索用户
     *
     * @param keywords
     * @return Result
     */
    Result selectByKeywords(String keywords);
    /**
     * 验证手机号是否可用
     * @param mobile
     * @return Result
     */
    Result checkMobile(String mobile);

}
