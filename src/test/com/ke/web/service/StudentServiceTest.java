package com.ke.web.service;

import com.ke.web.entity.Student;
import com.ke.web.factory.ServiceFactory;
import org.junit.Test;

import java.sql.SQLException;
import java.util.List;

import static org.junit.Assert.*;

public class StudentServiceTest {
    private StudentService studentService = ServiceFactory.getStudentServiceInstance();
    @Test
    public void listStudent() throws SQLException {
        List<Student> studentList = studentService.listStudent();
        studentList.forEach(System.out::println);
    }
}