package com.ke.web.service.impl;

import com.ke.web.dao.ArticleDao;
import com.ke.web.dao.TopicDao;
import com.ke.web.domain.vo.ArticleVo;
import com.ke.web.domain.vo.TopicVo;
import com.ke.web.entity.Topic;
import com.ke.web.factory.DaoFactory;
import com.ke.web.service.TopicService;
import com.ke.web.util.Result;
import com.ke.web.util.ResultCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;
import java.util.List;

/**
 * @author ke
 * @ClassName TopicServiceImpl
 * @Description TOOD
 * @Date 2019/12/19
 * @Version 1.0
 **/
public class TopicServiceImpl implements TopicService {

    private TopicDao topicDao = DaoFactory.getTopicDaoInstance();
    private ArticleDao articleDao = DaoFactory.getArticleDaoInstance();
    private static Logger logger = LoggerFactory.getLogger(TopicServiceImpl.class);

    @Override
    public Result getHotTopics() {
        List<Topic> topicList = null;
        try {
            topicList = topicDao.selectHotTopics();
        } catch (SQLException e) {
            logger.error("获取热门专题出现异常");
        }
        if (topicList != null) {
            return Result.success(topicList);
        } else {
            return Result.failure(ResultCode.RESULT_CODE_DATA_NONE);
        }
    }


    @Override
    public Result getTopic(long id) {
        TopicVo topicVo = null;
        try {
            topicVo = topicDao.getTopic(id);
        } catch (SQLException e) {
            logger.error("根据id获取专题详情出现异常");
        }
        if (topicVo != null) {
            try {
                List<ArticleVo> articleVoList = articleDao.selectByTopicId(topicVo.getTopic().getId());
                topicVo.setArticleList(articleVoList);

            } catch (SQLException e) {
                logger.error("根据专题id获取所有文章出现异常");
            }
            return Result.success(topicVo);
        } else {
            return Result.failure(ResultCode.RESULT_CODE_DATA_NONE);
        }
    }

    @Override
    public Result selectByKeywords(String keywords) {
        List<Topic> topicList = null;
        try {
            topicList = topicDao.selectByKeywords(keywords);
        } catch (SQLException e) {
            logger.error("根据关键字查询专题出现异常");
        }
        if (topicList != null) {
            return Result.success(topicList);
        } else {
            return Result.failure(ResultCode.RESULT_CODE_DATA_NONE);
        }
    }

    @Override
    public Result selectByPage(int currentPage, int count) {
        List<Topic> topicList = null;
        try {
            topicList = topicDao.selectByPage(currentPage, count);
        } catch (SQLException e) {
            logger.error("分页查询专题出现异常");
        }
        if (topicList != null) {
            return Result.success(topicList);
        } else {
            return Result.failure(ResultCode.RESULT_CODE_DATA_NONE);
        }
    }
}
