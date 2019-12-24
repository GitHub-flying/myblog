package com.ke.web.service.impl;

import com.ke.web.dao.CommentDao;
import com.ke.web.domain.vo.CommentVo;
import com.ke.web.entity.Comment;
import com.ke.web.factory.DaoFactory;
import com.ke.web.service.CommentService;
import com.ke.web.util.Result;
import com.ke.web.util.ResultCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @author ke
 * @ClassName CommentServiceImpl
 * @Description TOOD
 * @Date 2019/12/17
 * @Version 1.0
 **/
public class CommentServiceImpl implements CommentService {
    private static Logger logger = LoggerFactory.getLogger(CommentServiceImpl.class);
    private CommentDao commentDao = DaoFactory.getCommentDaoInstance();
    @Override
    public Result input(Comment comment) {
        comment.setCreateTime(LocalDateTime.now());
        try {
           int n = commentDao.insert(comment);
           return Result.success();
        } catch (SQLException e) {
            logger.error("评论写入异常");
            return  Result.failure(ResultCode.USER_WRITER_FAIL);
        }
    }

    @Override
    public Result selectByteComment(Long id) {
        List<CommentVo> commentVoList = new ArrayList<>();
        try{
            commentVoList = commentDao.getCommments(id);

        } catch (SQLException e) {
            logger.error("无数据");

        }
        if(commentVoList != null) {
            return Result.success(commentVoList);
        }else {
            return Result.failure(ResultCode.RESULT_CODE_DATA_NONE);
        }
    }
}
