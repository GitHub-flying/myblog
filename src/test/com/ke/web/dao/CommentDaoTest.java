package com.ke.web.dao;

import com.ke.web.domain.vo.CommentVo;
import com.ke.web.entity.Comment;
import com.ke.web.factory.DaoFactory;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class CommentDaoTest {
    private static Logger logger = LoggerFactory.getLogger(CommentDaoTest.class);
    CommentDao commentDao = DaoFactory.getCommentDaoInstance();
    @Test
    public void insert() throws SQLException {
        Comment comment = new Comment();
        comment.setUserId(1l);
        comment.setArticleId(2l);
        comment.setContent("shbdjskubdcksjdcksd");
        comment.setCreateTime(LocalDateTime.now());
        int n = commentDao.insert(comment);
        System.out.println(n);
    }

    @Test
    public void selectComment() throws SQLException {
        Comment comment = new Comment();
        comment.setArticleId(1l);
        List<CommentVo> commentList = new ArrayList<>();
        commentList = commentDao.getCommments(comment.getArticleId());
        System.out.println(commentList);
    }
}