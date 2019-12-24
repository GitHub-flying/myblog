package com.ke.web.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.ke.web.entity.Student;
import com.ke.web.factory.DaoFactory;
import com.ke.web.factory.ServiceFactory;
import com.ke.web.service.StudentService;
import com.ke.web.util.ResponseObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author ke
 * @ClassName StudentController
 * @Description TOOD
 * @Date 2019/11/7
 * @Version 1.0
 **/
@WebServlet(urlPatterns = "/student")
public class StudentController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException {
//        req.setCharacterEncoding("utf-8");
//
//        BufferedReader reader = req.getReader();
//        StringBuilder stringBuilder = new StringBuilder();
//        String line = null;
//        while ((line = reader.readLine()) != null) {
//            stringBuilder.append(line);
//        }
//        System.out.println(stringBuilder.toString());
//
//        Gson gson = new GsonBuilder().create();
//        Student student = gson.fromJson(stringBuilder.toString(), Student.class);
//
//        student.setCreateTime(LocalDateTime.now());
//        List<Student> students = null;
//        try {
//            students = DaoFactory.getStudentDaoInstance().selectAll();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//
//        resp.setContentType("application/json; charset = utf-8");
//        int code = resp.getStatus();
//        String msg = code == 200?"成功":"失败";
//        ResponseObject ro = ResponseObject.success(code, msg, student);
//        PrintWriter out = resp.getWriter();
//        out.print(gson.toJson(ro));
//        out.close();
        StudentService studentService = ServiceFactory.getStudentServiceInstance();
        List<Student> studentList = studentService.listStudent();
        Gson gson = new Gson();
        resp.setContentType("application/json;charset=utf-8");
        PrintWriter out = resp.getWriter();

        ResponseObject responseObject = new ResponseObject();
        responseObject.setCode(resp.getStatus());
        responseObject.setMsg(responseObject.getCode() == 200 ? "成功" : "失败");
        responseObject.setData(studentList);
        out.print(gson.toJson(responseObject));
        out.close();
    }
}
