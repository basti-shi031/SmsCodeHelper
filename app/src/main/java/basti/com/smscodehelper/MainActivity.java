package basti.com.smscodehelper;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import basti.com.smscodehelper.constant.Constant;
import basti.com.smscodehelper.utils.L;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.i("onCreate", "onCreate");

        boolean b  = isNearKeyword("【哔哩哔哩】103947 为你的密码重置验证码，请在5分钟内完成密码重置。如非本人操作，请忽略或回复T退订。","103947");

        L.l(String.valueOf(b));
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
