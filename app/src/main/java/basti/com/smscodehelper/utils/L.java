package basti.com.smscodehelper.utils;

import android.util.Log;

/**
 * Created by Bowen on 2016-03-14.
 */
public class L {

    private static final boolean Debug_Mode = true;

    private static void l(String tag, String message, boolean isDebug) {

        if (isDebug) {
            Log.i(tag, message);
        }
    }

    public static void l(String message) {
        l("TAG", message, Debug_Mode);
    }

}
