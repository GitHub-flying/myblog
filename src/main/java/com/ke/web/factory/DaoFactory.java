package com.ke.web.factory;

import com.ke.web.dao.*;
import com.ke.web.dao.impl.*;

/**
 * @author ke
 * @ClassName DaoFactory
 * @Description TOOD
 * @Date 2019/11/6
 * @Version 1.0
 **/
public class DaoFactory {

    public static StudentDao getStudentDaoInstance() {
        return new StudentDaoImpl();
    }

    public static UserDao getUserDaoInstance() {
        return new UserDaoImpl();
    }

    public static ArticleDao getArticleDaoInstance() {
        return new ArticleDaoImpl();
    }

    public static FocusDao getFocusDaoInstance() {return new FocusDaoImpl();}

    public static CommentDao getCommentDaoInstance() {return new CommentDaoImpl();}

    public static  TopicDao getTopicDaoInstance() {return new TopicDaoImpl();
    }
}
