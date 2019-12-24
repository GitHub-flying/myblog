package com.ke.web.domain.dto;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author ke
 * @ClassName UserDto
 * @Description TOOD
 * @Date 2019/11/12
 * @Version 1.0
 **/
@Data
public class UserDto {
    private String mobile;
    private String password;
    private String nickname;
    private String code;


    public UserDto() {

    }

    public UserDto(String mobile, String password) {
        this.mobile = mobile;
        this.password = password;
    }
    public UserDto(String mobile, String password, String nickname) {
        this.mobile = mobile;
        this.password = password;
        this.nickname = nickname;
    }
    public UserDto(String mobile, String password, LocalDateTime create_time) {
        this.mobile = mobile;
        this.password = password;

    }
}
