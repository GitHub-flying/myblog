package com.ke.web.domain.vo;

import com.ke.web.entity.Comment;
import com.ke.web.entity.User;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author ke
 * @ClassName CommentVo
 * @Description TOOD
 * @Date 2019/12/18
 * @Version 1.0
 **/
@Data
public class CommentVo {
    private User author;
    private Comment comment;
}

