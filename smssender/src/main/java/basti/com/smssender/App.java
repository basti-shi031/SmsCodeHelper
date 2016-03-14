package basti.com.smssender;

import android.app.Application;

import cn.smssdk.SMSSDK;

/**
 * Created by Bowen on 2016-03-14.
 */
public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        SMSSDK.initSDK(this,"10679a3217b00","8ea5775e91329638932e12d39fd20e2d");
    }
}
