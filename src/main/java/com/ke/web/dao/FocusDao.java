package com.ke.web.dao;

import com.ke.web.entity.Focus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;
import java.util.List;

public interface FocusDao {

    List<Focus> selectFocus()throws SQLException;
}
