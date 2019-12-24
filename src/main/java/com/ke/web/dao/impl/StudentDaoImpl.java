package com.ke.web.dao.impl;

import com.ke.web.dao.StudentDao;
import com.ke.web.entity.Student;
import com.ke.web.util.DBUtil;

import javax.swing.text.html.parser.Entity;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author ke
 * @ClassName StudentDaoImpl
 * @Description TOOD
 * @Date 2019/11/6
 * @Version 1.0
 **/
public class StudentDaoImpl  implements StudentDao {
    @Override
    public int insert(Student student) throws SQLException {
        Connection connection = DBUtil.getConnection();
        connection.setAutoCommit(false);
        String sql = "INSERT INTO t_student VALUES (NULL,?,?,?) ";
        PreparedStatement pstmt = connection.prepareStatement(sql);
        pstmt.setString(1, student.getUsername());
        pstmt.setString(2, student.getAvatar());
        pstmt.setTimestamp(3, Timestamp.valueOf(student.getCreateTime()));
        int n = pstmt.executeUpdate();
        connection.commit();
//        pstmt.close();
//        connection.close();
        return n;
    }

//    @Override
//    public int[] batchInsert(List<Student> studentList) throws SQLException {
//        Connection connection = DBUtil.getConnection();
//        String sql = "INSERT INTO t_student VALUES (NULL, ?, ?, ?)";
//        PreparedStatement pstmt = connection.prepareStatement(sql);
//        connection.setAutoCommit(false);
//        studentList.forEach(student -> {
//            try {
//                pstmt.setString(1, student.getUsername());
//                pstmt.setString(2, student.getAvatar());
//                pstmt.setTimestamp(3, Timestamp.valueOf(student.getCreateTime()));
//                pstmt.addBatch();
//            } catch(SQLException e) {
//                e.printStackTrace();
//            }
//        });
//        int[] result = pstmt.executeBatch();
//        connection.commit();
//        pstmt.close();
//        connection.close();
//        return result;
//    }

    @Override
    public int[] batchInsert(List<Student> studentList) throws SQLException {
        //获取连接
        Connection connection = DBUtil.getConnection();
        //sql语句
        String sql = "INSERT INTO t_student VALUES (NULL,?,?,?,?) ";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        connection.setAutoCommit(false);
        studentList.forEach(student -> {
            try {
                preparedStatement.setString(1, student.getUsername());
                preparedStatement.setString(2, student.getAvatar());
                preparedStatement.setTimestamp(3, Timestamp.valueOf(student.getCreateTime()));
                preparedStatement.setString(4, student.getDescription());
                //预处理事务集添加批处理
                preparedStatement.addBatch();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        });
        //
        int[] result = preparedStatement.executeBatch();
        //提交
        connection.commit();
        //关闭操作
//        preparedStatement.close();
//        connection.close();
        return result;
    }

    @Override
    public List<Student> selectAll() throws SQLException {
        List<Student> studentList = new ArrayList<>();
        Connection connection = DBUtil.getConnection();
        String sql = "SELECT * FROM t_student ORDER BY id DESC";
        PreparedStatement pstmt = connection.prepareStatement(sql);
        ResultSet rs = pstmt.executeQuery();
        while(rs.next()) {
            Student student = new Student();
            student.setUsername(rs.getString("username"));
            student.setAvatar(rs.getString("avatar"));
            student.setDescription(rs.getString("description"));
            Timestamp timestamp = rs.getTimestamp("create_time");
            student.setCreateTime(timestamp.toLocalDateTime());
            studentList.add(student);
        }
        return studentList;
    }
}
