package com.ke.web.controller;

import com.ke.web.util.ImageUtil;
import com.ke.web.util.VerificationUtil;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;

/**
 * @author ke
 * @ClassName CodeController
 * @Description TOOD
 * @Date 2019/11/20
 * @Version 1.0
 **/
@WebServlet(urlPatterns = "/api/code")
public class CodeController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String code = VerificationUtil.getCode();
        //筛选Id
        HttpSession session = req.getSession();
        System.out.println(session.getId());
        session.setAttribute("code", code);

        resp.setHeader("Access-Token",session.getId());
        BufferedImage img = ImageUtil.getImage(code,200,100);
        resp.setContentType("image/jpg");
        OutputStream out = resp.getOutputStream();
        ImageIO.write(img,"jpg",out);
        out.close();
    }
}
