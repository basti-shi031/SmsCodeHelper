# SmsCodeHelper

## 简介
短信验证码自动拦截，长按复制。

## 集成方法
 1. 将项目中 SmsReceiver 复制放入项目。
 2. 在 AndroidMainfest.xml 中注册Receiver。
 
 在收取短信短信后Receiver会拦截到该短信，判断是否是验证码短信，并截取其中的验证码存入Clipboard，只要在EditText中的长按复制即可。
