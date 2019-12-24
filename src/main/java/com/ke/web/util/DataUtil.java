package com.ke.web.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.ke.web.domain.dto.City;
import com.ke.web.domain.dto.Province;
import com.ke.web.domain.dto.Provinces;
import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.URL;
import java.nio.file.Files;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;

/**
 * @author ke
 * @ClassName DataUtil
 * @Description TOOD
 * @Date 2019/11/12
 * @Version 1.0
 **/
public class DataUtil {
    private static Logger logger = LoggerFactory.getLogger(DataUtil.class);

    /*
    获取电话号码

     */
    public static String getMoble() {
        StringBuilder mobile = new StringBuilder(139);
         Random random = new Random();
         for(int i = 0; i < 7; i++) {
             int num = random.nextInt(10);
             mobile.append(num);
         }
         return mobile.toString();
    }

    /*
    获取密码
     */
    public static String getPassword() {
        StringBuilder password = new StringBuilder();
        Random random = new Random();
        for(int i = 0; i < 8; i++) {
            int num = random.nextInt();
            password.append(num);
        }
        return DigestUtils.md5Hex(password.toString());
    }

    /*
    获取随机生日数据
     */
    public static LocalDate getBirthday() {
        LocalDate now = LocalDate.now();
        Random random = new Random();
        int bound = random.nextInt(8888);
        return now.minusDays(bound);
    }

    /*
    获取随机性别
     */
     public static String getGender() {
         String [] genders = new String[] {"男", "女"};
         Random random = new Random();
         int index = random.nextInt(2);
         return genders[index];
     }

     public static String getAddress() {
         StringBuilder address = new StringBuilder();
         ClassLoader classLoader = DataUtil.class.getClassLoader();
         URL resource = classLoader.getResource("address.json");
         assert resource != null;
         String path = resource.getPath();
         File file = new File(path);
         Reader reader = null;
         try {
             reader = new FileReader(file);
         } catch (FileNotFoundException e) {
             logger.error("文件找不到");
         }
         assert reader != null;
         BufferedReader br = new BufferedReader(reader);
         String line;
         try {
             while ((line = br.readLine()) != null) {
                 address.append(line);
             }
         } catch(IOException e) {
             logger.error("文件io异常");

         }
         Gson gson = new GsonBuilder().create();
         Provinces provinces = gson.fromJson(address.toString(), Provinces.class);
         List<Province> provinceList = provinces.getProvinces();
         int size = provinceList.size();
         Random random = new Random();
         int index = random.nextInt(size);
         Province province = provinceList.get(index);
         List<City> cityList = province.getCities();
         size = cityList.size();
         index = random.nextInt(size);
         City city = cityList.get(index);
         return province.getName() + city.getName();
     }

    public static LocalDateTime getCreateTime(){
        LocalDateTime now = LocalDateTime.now();
        Random random = new Random();
        int bound = random.nextInt(999);
        return now.minusHours(bound);
    }

    public static Long getUserId(){
        Random random = new Random();
        Long bound = (long) (random.nextInt(61) + 1);
        return bound;
    }
    public  static int getComments() {
        Random random = new Random();
        int comment = random.nextInt(50);
        return comment;
    }
    public static int getLikes() {
        Random random = new Random();
        int likes = random.nextInt(40);
        return likes;
    }

    public static void main(String[] args) {
        System.out.println(DigestUtils.md5Hex("111"));
    }
}
