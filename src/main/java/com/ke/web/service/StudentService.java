package com.ke.web.service;

import com.ke.web.entity.Student;

import java.sql.SQLException;
import java.util.List;

/**
 * @author ke
 * @ClassName StudentService
 * @Description TOOD
 * @Date 2019/11/7
 * @Version 1.0
 **/
public interface StudentService {
    List<Student> listStudent();
}
