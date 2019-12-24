package com.ke.web.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.ke.web.entity.Focus;
import com.ke.web.factory.DaoFactory;
import com.ke.web.factory.ServiceFactory;
import com.ke.web.service.FocusServe;
import com.ke.web.util.ResponseObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * @author ke
 * @ClassName FocusController
 * @Description TOOD
 * @Date 2019/12/11
 * @Version 1.0
 **/
@WebServlet(urlPatterns = "/api/focus")
public class FocusController extends HttpServlet {
    private static Logger logger = LoggerFactory.getLogger(FocusController.class);
    private FocusServe focusServe = ServiceFactory.getFocusServeInstance();

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Focus> focusList = focusServe.selectFocus();
        Gson gson = new GsonBuilder().create();
        resp.setContentType("application/json;charset=utf-8");
        PrintWriter out = resp.getWriter();
        ResponseObject responseObject = new ResponseObject();
        responseObject.setCode(resp.getStatus());
        responseObject.setMsg(responseObject.getCode() == 200 ? "成功" : "失败");
        responseObject.setData(focusList);
        out.print(gson.toJson(responseObject));
        out.close();
    }
}
