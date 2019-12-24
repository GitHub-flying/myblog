package com.ke.web.domain.vo;

import com.ke.web.entity.Topic;
import com.ke.web.entity.User;
import lombok.Data;

import java.util.List;

/**
 * @author ke
 * @ClassName TopicVo
 * @Description TOOD
 * @Date 2019/12/19
 * @Version 1.0
 **/
@Data
public class TopicVo {
    private Topic topic;
    private User admin;
    private List<ArticleVo> articleList;
    private List<User> followList;
}
