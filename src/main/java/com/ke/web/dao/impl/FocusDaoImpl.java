package com.ke.web.dao.impl;

import com.ke.web.dao.FocusDao;
import com.ke.web.entity.Focus;
import com.ke.web.util.DBUtil;
import com.mysql.jdbc.Statement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author ke
 * @ClassName FocusDao
 * @Description TOOD
 * @Date 2019/12/10
 * @Version 1.0
 **/
public class FocusDaoImpl implements FocusDao {
    private static Logger logger = LoggerFactory.getLogger(FocusDaoImpl.class);


    @Override
    public List<Focus> selectFocus () throws SQLException {
        List<Focus> focusList =  new ArrayList<Focus>();
        Connection connection = DBUtil.getConnection();
        String sql = "SELECT * From focus";
        PreparedStatement preparedStatement =  connection.prepareStatement(sql);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            Focus focus = new Focus();
            focus.setId(resultSet.getInt("id"));
            focus.setFocusTitle(resultSet.getString("focus-title"));
            focus.setFocusHomepage(resultSet.getString("focus-homepage"));
            focusList.add(focus);
        }
        return focusList;
    }

}
