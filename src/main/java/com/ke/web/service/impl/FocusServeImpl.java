package com.ke.web.service.impl;

import com.ke.web.dao.FocusDao;
import com.ke.web.entity.Focus;
import com.ke.web.factory.DaoFactory;
import com.ke.web.service.FocusServe;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author ke
 * @ClassName FocusServeImpl
 * @Description TOOD
 * @Date 2019/12/11
 * @Version 1.0
 **/
public class FocusServeImpl implements FocusServe {
    private static Logger logger = LoggerFactory.getLogger(FocusServeImpl.class);
    private FocusDao focusDao = DaoFactory.getFocusDaoInstance();

    public List<Focus> selectFocus () {
        List<Focus> focusList = new ArrayList<>();
        try {
            focusList = focusDao.selectFocus();
        } catch (SQLException e) {
            logger.error("查询热门文章出现异常");
        }
        return focusList;
    }
}
