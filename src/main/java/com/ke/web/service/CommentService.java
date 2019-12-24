package com.ke.web.service;

import com.ke.web.entity.Comment;
import com.ke.web.util.Result;

public interface CommentService {
    /*
    写入评论
     */
    Result input (Comment comment);

    Result selectByteComment(Long id);
}
