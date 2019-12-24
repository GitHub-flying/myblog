package com.ke.web.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.ke.web.domain.dto.UserDto;
import com.ke.web.factory.ServiceFactory;
import com.ke.web.listener.MySessionContext;
import com.ke.web.service.UserService;
import com.ke.web.util.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

/**
 * @author ke
 * @ClassName UserController
 * @Description TOOD
 * @Date 2019/11/12
 * @Version 1.0
 **/
@WebServlet(urlPatterns = {"/api/user", "/api/user/*"})
public class UserController extends HttpServlet {

    private static Logger logger = LoggerFactory.getLogger(UserController.class);
    private UserService userService = ServiceFactory.getUserServiceInstance();


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String uri = req.getRequestURI().trim();
        if (UrlPatten.USER.equals(uri)) {
            String page = req.getParameter("page");
            String keywords = req.getParameter("keywords");
            String count = req.getParameter("count");
//            if (page != null) {
//                HttpUtil.getResponseBody(resp, userService.selectByPage(Integer.parseInt(page), Integer.parseInt(count)));
            if (keywords != null) {
                HttpUtil.getResponseBody(resp, userService.selectByKeywords(keywords));
            } else {
                HttpUtil.getResponseBody(resp, userService.getHotUsers());
            }
        } else {
            System.out.println(uri);
            HttpUtil.getResponseBody(resp, userService.getUser(Long.parseLong(HttpUtil.getPathParam(req))));
        }
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String uri = req.getRequestURI().trim();
        switch (uri) {
            case UrlPatten.USER_SIGN_IN:
                signIn(req, resp);
                break;
            case UrlPatten.USER_SIGN_UP:
                registered(req, resp);
                break;
            case UrlPatten.USER_CHECK_MOBILE:
                String mobile = req.getParameter("mobile");
                HttpUtil.getResponseBody(resp, userService.checkMobile(mobile));
                break;
            default:
        }
    }

    private void signIn(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String requestBody = HttpUtil.getRequestBody(req);
        logger.info("登录用户信息：" + requestBody);
        Gson gson = new GsonBuilder().create();
        UserDto userDto = gson.fromJson(requestBody, UserDto.class);
        //客户端输入的验证码
        String inputCode = userDto.getCode().trim();
        System.out.println(inputCode);
        //取得客户端请求头里带来的token
        String sessionId = req.getHeader("Access-Token");
        System.out.println(sessionId);
        //从自定义的监听代码中取得之前的session对象
        MySessionContext myc = MySessionContext.getInstance();
        HttpSession session = myc.getSession(sessionId);
        //取得当时存入的验证码
        String correctCode = session.getAttribute("code").toString();
        //忽略大小写比对
        if (inputCode.equalsIgnoreCase(correctCode)) {
            HttpUtil.getResponseBody(resp, userService.signIn(userDto));
            //验证码正确，进入登录业务逻辑调用
        } else {
            //验证码错误，直接将错误信息返回给客户端，不要继续登录流程了
            HttpUtil.getResponseBody(resp, Result.failure(ResultCode.USER_VERIFY_CODE_ERROR));
        }
    }


    private void registered(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String requestBody = HttpUtil.getRequestBody(req);
        logger.info("登录用户信息：" + requestBody);
        Gson gson = new GsonBuilder().create();
        UserDto userDto = gson.fromJson(requestBody, UserDto.class);
        Result result = userService.registered(userDto);
        //取得客户端请求头里带来的token
        String sessionId = req.getHeader("Access-Token");
        System.out.println(sessionId);
        //从自定义的监听代码中取得之前的session对象
//        MySessionContext myc = MySessionContext.getInstance();
//        HttpSession session = myc.getSession(sessionId);
        if(result != null) {
            HttpUtil.getResponseBody(resp, userService.registered(userDto));
        } else {
            HttpUtil.getResponseBody(resp, Result.failure(ResultCode.USER_SIGN_UP_FAIL));
        }

    }
}
