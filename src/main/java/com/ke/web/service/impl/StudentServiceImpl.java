package com.ke.web.service.impl;

import com.ke.web.dao.StudentDao;
import com.ke.web.entity.Student;
import com.ke.web.factory.DaoFactory;
import com.ke.web.service.StudentService;

import java.sql.SQLException;
import java.util.List;

/**
 * @author ke
 * @ClassName StudentServiceImpl
 * @Description TOOD
 * @Date 2019/11/7
 * @Version 1.0
 **/
public class StudentServiceImpl implements StudentService {
    private StudentDao studentDao = DaoFactory.getStudentDaoInstance();

    @Override
    public List<Student> listStudent () {

        List<Student> studentList = null;
        try {
            studentList = studentDao.selectAll();
        } catch (SQLException e){
            e.printStackTrace();
            System.out.println("查询所有学生出现异常");
        }
        return studentList;
    }
}
