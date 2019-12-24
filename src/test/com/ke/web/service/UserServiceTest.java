package com.ke.web.service;

import com.ke.web.domain.dto.UserDto;
import com.ke.web.factory.ServiceFactory;
import com.ke.web.util.Result;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class UserServiceTest {
    private UserService userService = (UserService) ServiceFactory.getUserServiceInstance();

    @Test
    public void signIn(){

        UserDto userDto = new UserDto("13917310803","111");
        Result result = userService.signIn(userDto);
        System.out.println(result.getCode());
        System.out.println(result.getMsg());
    }

    @Test
    public void registered() {

    }
}