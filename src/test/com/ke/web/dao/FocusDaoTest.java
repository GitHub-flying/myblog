package com.ke.web.dao;

import com.ke.web.entity.Focus;
import com.ke.web.factory.DaoFactory;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class FocusDaoTest {
    private static Logger logger = LoggerFactory.getLogger(ArticleDaoTest.class);
    public FocusDao focusDao = DaoFactory.getFocusDaoInstance();
    @Test
    public void selectFocus() throws SQLException {
        List<Focus> focusList = focusDao.selectFocus();
        focusList.forEach(a -> System.out.println(a));
    }
}