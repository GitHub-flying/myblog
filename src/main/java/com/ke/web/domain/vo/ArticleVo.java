package com.ke.web.domain.vo;

import com.ke.web.entity.Article;
import com.ke.web.entity.Topic;
import com.ke.web.entity.User;
import lombok.Data;

/**
 * @author ke
 * @ClassName ArticleVo
 * @Description TOOD
 * @Date 2019/11/16
 * @Version 1.0
 **/
@Data
public class ArticleVo {
    private Long id;
    private Long userId;
    private String nickname;
    private String avatar;
    private String title;
    private String summary;
    private String thumbnail;
    private Integer likes;
    private Integer comments;
    private String content;
    private Article article;
    private User author;
    private Topic topic;
}

