package com.ke.web.util;

import java.util.Random;

/**
 * @author ke
 * @ClassName Verification
 * @Description TOOD
 * @Date 2019/11/20
 * @Version 1.0
 **/
public class VerificationUtil {

    public static String getCode () {
        StringBuffer stringBuffer = new StringBuffer();
        Random random = new Random();
        for(int i = 0; i < 4; i++) {
            int mun = random.nextInt(26);
            char c = (char)('a' + mun);
            stringBuffer.append(c);
        }
        return stringBuffer.toString();
    }
    public static void main(String args[]) {
//        String str = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
//        String uuid = null;
//        for (int i = 0; i < 4; i++){
//            char ch = str.charAt(new Random().nextInt(str.length()));
//            uuid += ch;
//        }
//        System.out.println("验证码"+uuid);

        System.out.println("验证码："+VerificationUtil.getCode());
    }
}
