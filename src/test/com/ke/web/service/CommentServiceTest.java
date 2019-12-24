package com.ke.web.service;

import com.ke.web.entity.Comment;
import com.ke.web.factory.ServiceFactory;
import com.ke.web.util.Result;
import org.junit.Test;

import static org.junit.Assert.*;

public class CommentServiceTest {
    private  CommentService commentService = ServiceFactory.getCommentInstcnce();

    @Test
    public void selectByteComment() {
        Comment comment = new Comment();
        comment.setArticleId(1l);
        Result result = commentService.selectByteComment(comment.getArticleId());
        System.out.println(result.getMsg());
    }
}