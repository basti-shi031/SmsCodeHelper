package basti.com.smscodehelper.utils;

import android.text.TextUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import basti.com.smscodehelper.constant.Constant;

/**
 * Created by Bowen on 2016-03-14.
 */
public class StringUtils {

    //检查手机号是否是私人手机号
    public static boolean isPersonalMobile(String phone) {

        if (phone != null && !TextUtils.isEmpty(phone)) {
            Pattern phonePattern = Pattern.compile("^[1][358][0-9]{9}$");
            Matcher phoneMatcher = phonePattern.matcher(phone);

            if (phoneMatcher == null) {
                return false;
            } else {
                return phoneMatcher.matches();
            }
        }

        return false;
    }


    //获取验证码
    public static String getVerification(String messagebody) {

        Pattern continuousNumberPattern = Pattern.compile("[a-zA-Z0-9\\.]+");
        Matcher m = continuousNumberPattern.matcher(messagebody);

        while (m.find()) {
            //匹配到类似验证码的字段
            if (m.group().length() > 3 && m.group().length() < 8) {
                //如果在关键字周围，则说明m.Group是验证码的概率很大
                if (StringUtils.isNearKeyword(messagebody, m.group())) {
                    return m.group();
                }
            }
        }

        return "";
    }


    //判断是否在关键字附近
    private static boolean isNearKeyword(String messagebody, String code) {

        int start = 0;
        int end = messagebody.length() - 1;

        int indexOfCode = messagebody.indexOf(code);

        if (indexOfCode > 12) {
            start = indexOfCode - 12;
        }

        if (indexOfCode + code.length() + 12 < end) {
            end = indexOfCode + code.length() + 12;
        }

        //截取疑似验证码附近的字符进行判断
        String keyString = messagebody.substring(start, end);

        boolean isNearKeyWord = false;
        for (String key : Constant.KEYWORDS) {
            if (keyString.contains(key)) {
                isNearKeyWord = true;

                return isNearKeyWord;
            }
        }
        return false;
    }
}
