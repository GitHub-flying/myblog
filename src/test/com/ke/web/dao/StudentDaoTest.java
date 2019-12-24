package com.ke.web.dao;

import com.ke.web.entity.Student;
import com.ke.web.factory.DaoFactory;
import com.ke.web.util.JSoupSpider;
import org.junit.Test;

import java.sql.SQLException;
import java.time.LocalDateTime;

import static org.junit.Assert.*;

public class StudentDaoTest {
    private StudentDao studentDao = DaoFactory.getStudentDaoInstance();

    @Test
    public void insert() throws SQLException {
        Student student = new Student();
        student.setUsername("用户");
        student.setAvatar("1.jpg");
        student.setCreateTime(LocalDateTime.now());
        int n = studentDao.insert(student);
        assertEquals(1,n);
    }

//    @Test
//    public void batchInsert() throws SQLException {
//        int[] n = studentDao.batchInsert(JSoupSpider.getStudent());
//        assertEquals(24, n.length);
//    }
}