package com.ke.web.dao;

import com.ke.web.domain.dto.UserDto;
import com.ke.web.domain.vo.UserVo;
import com.ke.web.entity.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * @author ke
 * @ClassName UserDao
 * @Description TOOD
 * @Date 2019/11/12
 * @Version 1.0
 **/
public interface UserDao {
    /*
    新增用户
     */
    int insert(User user) throws SQLException;

    int[] batchInsert(List<User> userList) throws SQLException;
    /*
    根据手机号查找用户
     */
    User findUserByMobile(String mobile) throws SQLException;

    List<User> selectHotUsers() throws SQLException;


    /**
     * 根据id查询用户
     * @param  id
     * @return
     * @throws SQLException
     */
    UserVo getUser(long id) throws SQLException;
    /**
     * 模糊搜索用户
     * @param keywords
     * @return
     * @throws SQLException
     */
    List<User> selectByKeywords(String keywords) throws SQLException;
}
