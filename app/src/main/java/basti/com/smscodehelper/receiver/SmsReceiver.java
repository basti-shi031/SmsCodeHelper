package basti.com.smscodehelper.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.SmsMessage;
import android.text.TextUtils;
import android.widget.Toast;

import java.util.Date;

import basti.com.smscodehelper.R;
import basti.com.smscodehelper.utils.L;
import basti.com.smscodehelper.utils.StringUtils;

/**
 * 接收到短信的广播
 * Created by Bowen on 2016-03-14.
 */
public class SmsReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {

        Object[] pdus = (Object[]) intent.getExtras().get("pdus");

        for (Object o : pdus) {
            byte[] sms = (byte[]) o;

            SmsMessage smsMessage = SmsMessage.createFromPdu(sms, "3gpp");
            String messagebody = smsMessage.getMessageBody();
            String originatingAddress = smsMessage.getOriginatingAddress();
            Date date = new Date(smsMessage.getTimestampMillis());

            L.l(messagebody + "   " + originatingAddress + "   " + String.valueOf(date));

            //判断是否是私人短信
            if (!StringUtils.isPersonalMobile(originatingAddress)) {
                String verification = StringUtils.getVerification(messagebody);

                //如果返回得到验证码，则说明验证码已经得到
                if (!TextUtils.isEmpty(verification)) {
                    Toast.makeText(context, context.getResources().getString(R.string.verification) + verification + context.getResources().getString(R.string.hint), Toast.LENGTH_LONG).show();
                    Toast.makeText(context, context.getResources().getString(R.string.verification) + verification + context.getResources().getString(R.string.hint), Toast.LENGTH_LONG).show();
                }
            }
        }
    }
}
