package com.ke.web.dao;

import com.ke.web.domain.vo.CommentVo;
import com.ke.web.entity.Comment;

import java.sql.SQLException;
import java.util.List;

public interface CommentDao {
    int insert(Comment comment) throws SQLException;


    /**
     * 根据文章id查出所有评论
     * @param articleId
     * @return
     * @throws SQLException
     */
    List<CommentVo> getCommments(long articleId) throws SQLException;

}
