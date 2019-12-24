package com.ke.web.dao;

import com.ke.web.dao.impl.UserDaoImpl;
import com.ke.web.entity.User;
import com.ke.web.factory.DaoFactory;
import com.ke.web.util.JSoupSpider;
import org.junit.Test;

import java.sql.SQLException;
import java.util.List;

import static org.junit.Assert.*;

public class UserDaoTest {
    private UserDao userDao = DaoFactory.getUserDaoInstance();

    @Test
    public void batchInsert() throws SQLException {
        int[] result = userDao.batchInsert(JSoupSpider.getUsers());
        System.out.println(result.length);
    }

    @Test
    public void findUserByMobile() throws SQLException {
        User user = userDao.findUserByMobile("13917310803");
        System.out.println(user);
    }

    @Test
    public void selectAll() throws SQLException {
        List<User> userList = userDao.selectHotUsers();
        userList.forEach(System.out::println);
    }

    @Test
    public void insert() throws SQLException {
        User user = new User("123", "111", "dsijn");
        UserDao userDao = new UserDaoImpl();
        int n = userDao.insert(user);
    }

    //   @Test
//    public void insert() {
//        User user = userDao.insert("123")
//    }
}