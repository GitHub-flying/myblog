package com.ke.web.service.impl;

import com.ke.web.dao.ArticleDao;
import com.ke.web.dao.UserDao;
import com.ke.web.dao.impl.ArticleDaoImpl;
import com.ke.web.domain.dto.UserDto;
import com.ke.web.domain.vo.ArticleVo;
import com.ke.web.domain.vo.UserVo;
import com.ke.web.entity.User;
import com.ke.web.factory.DaoFactory;
import com.ke.web.service.UserService;
import com.ke.web.util.Message;
import com.ke.web.util.Result;
import com.ke.web.util.ResultCode;
import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author ke
 * @ClassName UserServiceImpl
 * @Description TOOD
 * @Date 2019/11/12
 * @Version 1.0
 **/
public class UserServiceImpl implements UserService {
    private UserDao userDao = DaoFactory.getUserDaoInstance();
    private ArticleDao articleDao = DaoFactory.getArticleDaoInstance();
    private static Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Override
    public Result signIn(UserDto userDto) {
        User user = null;
        try {
            user = userDao.findUserByMobile(userDto.getMobile());
        } catch (SQLException e) {
            logger.error("根据手机号查询用户出现异常");
        }
        if (user != null) {
            //数据库查到的用户密码和前端传递的用户密码（经过加密）相等
            if (user.getPassword().equals(DigestUtils.md5Hex(userDto.getPassword()))) {
                //登录成功
                return Result.success(user);
            } else {
                //密码错误
                return Result.failure(ResultCode.USER_PASSWORD_ERROR);
            }
        } else {
            //账号错误
            return Result.failure(ResultCode.USER_ACCOUNT_ERROR);
        }
    }


    @Override
    public Result registered(UserDto userDto) {
        User user = new User(userDto.getMobile(), userDto.getPassword(),userDto.getNickname());
        try {
            userDao.insert(user);
            return Result.success();
        } catch (SQLException e) {
            e.printStackTrace();
            logger.error("新增用户异常");
            return Result.failure(ResultCode.USER_SIGN_UP_FAIL);
        }
    }

    @Override
    public List<User> getUsers() {
        List<User> userList = null;
        try {
            userList = userDao.selectHotUsers();
        } catch (SQLException e) {
            logger.error("获取所有用户出现异常");
        }
        return userList;
    }
    @Override
    public Result getHotUsers() {
        List<User> userList = null;
        try {
            userList = userDao.selectHotUsers();
        } catch (SQLException e) {
            logger.error("获取热门用户出现异常");
        }
        if (userList != null) {
            //成功并返回数据
            return Result.success(userList);
        } else {
            return Result.failure(ResultCode.RESULT_CODE_DATA_NONE);
        }
    }
    @Override
    public Result getUser(long id) {
        UserVo userVo = null;
        try {
            userVo = userDao.getUser(id);
        } catch (SQLException e) {
            logger.error("根据id获取用户详情出现异常");
        }
        if (userVo != null) {
            try {
                ArticleDao articleDao = new ArticleDaoImpl();
                List<ArticleVo> articleVoList = articleDao.selectByUserId(id);
                userVo.setArticleList(articleVoList);
                return Result.success(userVo);
            } catch (SQLException e) {
                logger.error("根据用户id获取文章列表数据出现异常");
            }
        }
        return Result.failure(ResultCode.RESULT_CODE_DATA_NONE);
    }
    @Override
    public Result selectByKeywords(String keywords) {
        List<User> userList = null;
        try {
            userList = userDao.selectByKeywords(keywords);
        } catch (SQLException e) {
            logger.error("根据关键字查询用户出现异常");
        }
        if (userList != null) {
            return Result.success(userList);
        } else {
            return Result.failure(ResultCode.RESULT_CODE_DATA_NONE);
        }
    }

    @Override
    public Result checkMobile(String mobile) {
        User user = null;
        try {
            user = userDao.findUserByMobile(mobile);
        } catch (SQLException e) {
            logger.error("根据手机号查询用户信息出现异常");
        }
        if (user == null) {
            return Result.success(ResultCode.USER_NOT_EXIST);
        } else {
            return Result.failure(ResultCode.USER_HAS_EXISTED);
        }
    }

}
