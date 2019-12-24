package com.ke.web.factory;

import com.ke.web.entity.Focus;
import com.ke.web.service.*;
import com.ke.web.service.impl.*;

/**
 * @author ke
 * @ClassName ServiceFactory
 * @Description TOOD
 * @Date 2019/11/7
 * @Version 1.0
 **/
public class ServiceFactory {
    public static StudentService getStudentServiceInstance () { return new StudentServiceImpl();};

    public static UserService getUserServiceInstance() {
        return new UserServiceImpl();
    }

    public static ArticleService getArticleServiceInstance() {
        return new ArticleServiceImpl();
    }

    public static FocusServe getFocusServeInstance() {return new FocusServeImpl(); }

    public static CommentService getCommentInstcnce() {return new CommentServiceImpl();}

    public static  TopicService getTopicServiceInstance() {return new TopicServiceImpl();}
}
