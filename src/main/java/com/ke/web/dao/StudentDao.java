package com.ke.web.dao;

import com.ke.web.entity.Student;

import javax.swing.text.html.parser.Entity;
import java.sql.SQLException;
import java.util.List;

/**
 * @author ke
 * @ClassName StudentDao
 * @Description TOOD
 * @Date 2019/11/6
 * @Version 1.0
 **/
public interface StudentDao {

    /*
    用于查询用户信息
     */
//    List<Entity> findAll() throws SQLException;

     /*

      */
    int insert(Student student) throws SQLException;

    /*
    批量
     */
    int[] batchInsert(List<Student> studentList) throws SQLException;
    List<Student> selectAll() throws SQLException;
}
