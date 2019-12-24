package com.ke.web.util;

import com.ke.web.entity.Article;
import com.ke.web.entity.Student;
import com.ke.web.entity.User;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.PipedInputStream;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @author ke
 * @ClassName JSoupSpider
 * @Description TOOD
 * @Date 2019/11/7
 * @Version 1.0
 **/
public class JSoupSpider {

    private static Logger logger = LoggerFactory.getLogger(JSoupSpider.class);
    static Document document = null;

    //静态的共有无参方法，方法名自定，返回
    public static List<Student> getStudent() {
        //通过JSoup链接目标页
        try {
            document = (Document) Jsoup.connect("https://www.jianshu.com/recommendations/users?utm_source=desktop&utm_medium=index-users").get();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //选取所有class为col-xs-8的所有元素
        Elements divs = document.getElementsByClass("col-xs-8");
        List<Student> studentList = new ArrayList<>(divs.size());
        //对div遍历
        divs.forEach(div -> {
            //取出class为wrap的结点
            Element wrapDiv = div.child(0);
            Element link = wrapDiv.child(0);
//            Element img = link.child(0);
            Elements linkChildren = link.children();
            System.out.println("https:" + linkChildren.attr("src"));
//            System.out.println(wrapDiv.html());
//            System.out.println(wrapDiv.child(0).text());
            Student student = new Student();
            student.setUsername(linkChildren.get(1).text());
            System.out.println(linkChildren.get(1).text());
            student.setAvatar("https:" + linkChildren.get(0).attr("src"));
            student.setCreateTime(LocalDateTime.now());
            student.setDescription(linkChildren.get(2).text());
            System.out.println(linkChildren.get(2).text());
            studentList.add(student);
        });
        return studentList;
    }

    public static List<User> getUsers() {
        List<User> userList = new ArrayList<>(100);
        for (int i = 2; i <= 10; i++) {
            try {
                document = Jsoup.connect("https://www.jianshu.com/recommendations/users?utm_source=desktop&utm_medium=index-users&page=" + i).get();
            } catch (IOException e) {
                logger.error("连接失败");
            }
            Elements divs = document.getElementsByClass("col-xs-8");
            divs.forEach(div -> {
                Element wrapDiv = div.child(0);
                Element link = wrapDiv.child(0);
                Elements linkChildren = link.children();
                User user = new User();
                user.setMobile(DataUtil.getMoble());
                user.setPassword(DataUtil.getPassword());
                user.setGender(DataUtil.getGender());
                user.setAvatar("http:" + linkChildren.get(0).attr("src"));
                user.setNickname(linkChildren.get(1).text());
                user.setIntroduction(linkChildren.get(2).text());
                user.setBirthday(DataUtil.getBirthday());
                user.setCreateTime(LocalDateTime.now());
                userList.add(user);
            });
        }
        return userList;
    }


    public static List<Article> getArticles() {
        Document document = null;
        List<Article> articleList = new ArrayList<>(100);
        for (int i = 2; i <= 16; i++) {
            try {
                document = Jsoup.connect("http://www.stdaily.com/index/kejixinwen/kejixinwen_" +i+ ".shtml").get();
            } catch (IOException e) {
                logger.error("连接失败");
            }
            Elements divs = document.getElementsByClass("f_lieb_list");
            divs.forEach(div -> {
                System.out.println("http://www.stdaily.com" + div.child(0).child(0).child(0).attr("href"));
                String articleUrl =   div.child(0).child(0).child(0).attr("href");
                Document document1 = null;
                try {
                    document1 = Jsoup.connect("http://www.stdaily.com" + articleUrl).get();
                } catch (IOException e) {
                    logger.error("连接失败");
                }
                Element articleIntroduce = document1.getElementsByClass("content").get(0);
                Article article = new Article();
//                article.setContent(articleElement.html());

//                Elements elements  = div.children();
//                Element linkElement = elements.get(0);
//                Element divElement = elements.get(1);
                article.setContent(articleIntroduce.html());
                article.setUserId(DataUtil.getUserId());
                article.setTitle(div.child(0).child(0).child(0).text());
                article.setSummary(div.child(2).child(0).child(0).text());
                String img =  div.child(0).child(1).child(0).attr("src");
                article.setThumbnail("http://www.stdaily.com" +img);
//                Elements metaChildren = divElement.child(2).children();
//                String comments = metaChildren.get(2).text();
//                String likes = metaChildren.last().text();
//                try {
//                    article.setComments(Integer.parseInt(comments));
//                    article.setLikes(Integer.parseInt(likes));
//                } catch (NumberFormatException e) {
//                    logger.error("格式转换异常");
//                }
                article.setComments(DataUtil.getComments());
                article.setLikes(DataUtil.getLikes());
                article.setCreateTime(DataUtil.getCreateTime());
                articleList.add(article);
            });
        }
        System.out.println(articleList.size());
        return articleList;
    }
}


//public class JSoupSpider {
//    //无参方法，
//    public static List<Student> getStudents() {
//
//        //声明文档变量
//        Document document = null;
//        //通过JSoup连接目标页面
//        try {
//            document = Jsoup.connect("https://www.jianshu.com/recommendations/users?utm_source=desktop&utm_medium=index-users").get();
//        } catch (IOException e) {
//            System.err.println("连接失败");
//        }
//        //选取所有class为col-xs-8的元素集合·
//        Elements divs = document.getElementsByClass("col-xs-8");
//        //
//        List<Student> studentList = new ArrayList<>(divs.size());
//        //对divs遍历
//        divs.forEach(div-> {
//            //取出class为wrap的节点
//            Element wrapDiv = div.child(0);
//            Student student = new Student();
//            student.setUsername(wrapDiv.child(0).child(1).text());
//            student.setAvatar("https:" + wrapDiv.child(0).child(0).attr("src"));
//            student.setCreateTime(LocalDateTime.now());
//            studentList.add(student);
//        });
//
//        return studentList;
//    }
//}