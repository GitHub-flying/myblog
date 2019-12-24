package com.ke.web.domain.vo;

import com.ke.web.entity.Topic;
import com.ke.web.entity.User;
import lombok.Data;

import java.util.List;

/**
 * @author ke
 * @ClassName UserVo
 * @Description TOOD
 * @Date 2019/12/12
 * @Version 1.0
 **/
@Data
public class UserVo {
    private User user;
    private List<ArticleVo> articleList;
    private List<Topic> topicList;
    private List<User> fansList;
}
