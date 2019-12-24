package com.ke.web.dao.impl;

import com.ke.web.dao.CommentDao;
import com.ke.web.domain.vo.CommentVo;
import com.ke.web.entity.Comment;
import com.ke.web.entity.User;
import com.ke.web.util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author ke
 * @ClassName CommentDaoImpl
 * @Description TOOD
 * @Date 2019/12/17
 * @Version 1.0
 **/
public class CommentDaoImpl implements CommentDao {
    @Override
    public int insert(Comment comment) throws SQLException {
        Connection connection = DBUtil.getConnection();
        String sql = "INSERT INTO t_comment(user_id, article_id, content, create_time) VALUES(?, ?, ?, ?)";
        PreparedStatement pstmt = connection.prepareStatement(sql);
        pstmt.setLong(1, comment.getUserId());
        pstmt.setLong(2, comment.getArticleId());
        pstmt.setString(3, comment.getContent());
        pstmt.setObject(4, comment.getCreateTime());
        int n = pstmt.executeUpdate();
        return n;
    }

    @Override
    public List<CommentVo> getCommments(long articleId) throws SQLException {
        Connection connection = DBUtil.getConnection();
        String sql = "SELECT a.*,b.avatar,b.nickname FROM t_comment a LEFT JOIN t_user b ON a.user_id = b.id WHERE a.article_id=?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setLong(1, articleId);
        ResultSet resultSet = preparedStatement.executeQuery();

        CommentVo commentVo = null;
        List<CommentVo> commentVoList = new ArrayList<>();
        while (resultSet.next()) {
            Comment  comment = new Comment();
            comment.setId(resultSet.getLong("id"));
            comment.setArticleId(resultSet.getLong("article_id"));
            comment.setUserId(resultSet.getLong("user_id"));
            comment.setContent(resultSet.getString("content"));
            comment.setCreateTime(resultSet.getTimestamp("create_time").toLocalDateTime());
            //写这篇评论的作者的基本信息
            User author = new User();
            author.setNickname(resultSet.getString("nickname"));
            author.setAvatar(resultSet.getString("avatar"));

            commentVo = new CommentVo();
            commentVo.setAuthor(author);
            commentVo.setComment(comment);
            commentVoList.add(commentVo);
        }
        return commentVoList;
    }
}
