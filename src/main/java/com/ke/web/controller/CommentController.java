package com.ke.web.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.ke.web.entity.Comment;
import com.ke.web.factory.DaoFactory;
import com.ke.web.factory.ServiceFactory;
import com.ke.web.service.CommentService;
import com.ke.web.util.HttpUtil;
import com.ke.web.util.Result;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

/**
 * @author ke
 * @ClassName CommentController
 * @Description TOOD
 * @Date 2019/12/17
 * @Version 1.0
 **/
@WebServlet(urlPatterns = "/api/comment")
public class CommentController extends HttpServlet {
    private CommentService commentService = ServiceFactory.getCommentInstcnce();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Gson gson = new GsonBuilder().create();
        String id = req.getParameter("articleId");
        Result result = commentService.selectByteComment(Long.valueOf(id));
        PrintWriter out = resp.getWriter();
        out.print(gson.toJson(result));
        out.close();
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        String requestBody = HttpUtil.getRequestBody(req);
        Gson gson = new GsonBuilder().create();
        Comment comment = gson.fromJson(requestBody, Comment.class);
        HttpUtil.getResponseBody(resp, commentService.input(comment));
    }

}
