package com.ke.web.util;

import com.ke.web.entity.Article;
import com.ke.web.entity.User;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;



/**
 * @author ke
 * @ClassName JSoupDemo
 * @Description TOOD
 * @Date 2019/11/15
 * @Version 1.0
 **/
public class JSoupDemo {
    //    public static void main(String[] args) {
//        Logger logger = LoggerFactory.getLogger(JSoupSpider.class);
//        Document document = null;
//        List<Article> articleList = new ArrayList<> (100);
//        for (int i = 1; i < 10; i++) {
//            try {
//                document = Jsoup.connect("http://www.stdaily.com/index/kejixinwen/kejixinwen_" + i+".shtml").get();
//            } catch (IOException e) {
//                logger.error("连接失败");
//            }
//            Elements divs = document.getElementsByClass("f_lieb_list");
//            divs.forEach(div -> {
//                String articleUrl = div.child(0).attr("href");
//                Document document1 = null;
//                Element articleElement = document1.getElementsByClass("listBox").get(0);
//                Article article = new Article();
//                article.setContent(articleElement.html());
//                Elements elements  = div.children();
//                Element linkElement = elements.get(0);
//                Element divElement = elements.get(1);
////                article.setUserId(DataUtil.getUserId());
////                article.setTitle(divElement.child(0).text());
////                article.setSummary(divElement.child(1).text());
//                String img = "https:" + linkElement.child(0).child(0).attr("src");
//                System.out.println(img);
//                int index = img.indexOf("?");
//                article.setThumbnail(img.substring(0, index));
//                Elements metaChildren = divElement.child(2).children();
//                String comments = metaChildren.get(2).text();
//                String likes = metaChildren.last().text();
//                try {
//                    article.setComments(Integer.parseInt(comments));
//                    article.setLikes(Integer.parseInt(likes));
//                } catch (NumberFormatException e) {
//                    logger.error("格式转换异常");
//                }
//                article.setCreateTime(DataUtil.getCreateTime());
//                articleList.add(article);
//                System.out.println(divElement.child(0).text());
//            });
//        }
//
//        System.out.println(articleList.size());
//    }
    public static void main(String[] args) {
        Logger logger = LoggerFactory.getLogger(JSoupSpider.class);
        Document document = null;
        List<Article> articleList = new ArrayList<>(100);
        for (int i = 2; i <= 10; i++) {
            try {
                document = Jsoup.connect("http://www.stdaily.com/index/kejixinwen/kejixinwen_" + i + ".shtml").get();
//                document = Jsoup.connect("http://www.stdaily.com/index/kejixinwen/2019-12/09/content_841081.shtml").get();
            } catch (IOException e) {
                logger.error("连接失败");
            }
            Elements divs = document.getElementsByClass("f_lieb_list");
            divs.forEach(div -> {
//                System.out.println(div.children());
                System.out.println("http://www.stdaily.com" + div.child(0).child(0).child(0).attr("href"));
                String articleUrl =   div.child(0).child(0).child(0).attr("href");
                Document document1 = null;
                try {
                    document1 = Jsoup.connect("http://www.stdaily.com" + articleUrl).get();
                } catch (IOException e) {
                    logger.error("连接失败");
                }
                Element articleIntroduce = document1.getElementsByClass("content").get(0);
                System.out.println(articleIntroduce.html());
                System.out.println(div.child(0).child(0).child(0).text());
                System.out.println("http://www.stdaily.com" + div.child(0).child(1).child(0).attr("src"));
                System.out.println(div.child(2).child(0).child(0).text());
            });
        }
    }
}