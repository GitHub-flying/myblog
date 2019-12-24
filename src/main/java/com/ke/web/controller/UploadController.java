package com.ke.web.controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Collection;
import java.util.UUID;

/**
 * @author ke
 * @ClassName UploadController
 * @Description TOOD
 * @Date 2019/11/21
 * @Version 1.0
 **/
@MultipartConfig(maxFileSize = 1024 * 1024 * 50)
@WebServlet(urlPatterns = "/upload")
public class UploadController extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Collection<Part> parts = req.getParts();
        //以当前日期为目录名
        String realPath = req.getSession().getServletContext().getRealPath("") + LocalDate.now().toString() + "/";
        System.out.println(realPath);

        String filename = "";
        //文件不存在则创建
        File file = new File(realPath);
        if (!file.exists()){
            file.mkdir();
        }
        resp.setContentType("image/jpg");
        long maxFileSize = 1024 * 1024 * 10;
        for (Part part : parts) {
            if (part.getSize() <= maxFileSize && part.getSize()>0) {
                String name = part.getSubmittedFileName();
                //获得文件后缀
                String suffix = name.substring(name.lastIndexOf(".")-1);
                System.out.println(suffix);
                //文件名为UUID随机生成
                filename = UUID.randomUUID() + suffix;
                System.out.println(filename);

                part.write(realPath + filename);
                req.setAttribute("msg", "上传成功!");

            }
            else if (part.getSize() ==0) {
                req.setAttribute("msg", "请点击加号上传图片");
            }
            else {
                req.setAttribute("msg", "图片文件过大，请重新上传");
            }

        }
        req.setAttribute("url", realPath );
        req.getRequestDispatcher("/upload.jsp").forward(req, resp);
    }
}