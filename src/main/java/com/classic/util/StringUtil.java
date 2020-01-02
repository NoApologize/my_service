package com.classic.util;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;

import java.security.MessageDigest;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.UUID;

public class StringUtil {

    //消息参数替换
    public static String messageReplace(String message,String... args){
        if(args.length!=0){
            for (int i = 0; i < args.length; i++) {
                if( args[i]==null){
                    args[i] = "null";
                }
                message = message.replace("{"+i+"}", args[i]);
            }
        }
        return message;
    }

    //获取UUID
    public static String getRandomUUID(){
        return UUID.randomUUID().toString();
    }

    //获取app系统唯一key
    //20161107XXXX；XXXX为四位当日流水号；从0001开始
    private static int count = 0;
    public static String getAppUniqueKey(String dbValue) throws Exception {
        Thread.sleep(1);
        if(count==0&&dbValue==null){//服务器重启且数据库没申请过的情况或数据库没申请过的情况
            count = 1;
            return DateUtil.dateToString(new Date(), "yyyyMMdd")+String.format("%04d", count++);
        }else if(count==0&&dbValue!=null){//服务器重启且数据库有申请过的情况或数据库有申请过的情况
            int newValue = (Integer.parseInt(dbValue.substring(8,12))+1);
            return DateUtil.dateToString(new Date(), "yyyyMMdd")+String.format("%04d", newValue);
        }else{//count!=0
            return DateUtil.dateToString(new Date(), "yyyyMMdd")+String.format("%04d", count++);
        }
    }

    //获取APP系统唯一KEY序号
    public static synchronized String getAppUniqueKeyIndex(String appUniqueKey){
        try {
            Thread.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return String.valueOf(new Date().getTime());
    }

    //获得汉子的拼音
    public static String getHanziPinyin(String hanzi, HanyuPinyinOutputFormat defaultFormat){
        String out = "";
        try{
            out = PinyinHelper.toHanYuPinyinString(hanzi, defaultFormat,"",false);
        }catch(Exception e){
            //do nothing
        }
        return out;
    }

    //MD5加密
    public static String getMD5Secret(String str) throws Exception {
        MessageDigest messageDigest = MessageDigest.getInstance("MD5");
        messageDigest.update(str.getBytes());
        byte secretBytes[] = messageDigest.digest();
        return bytesToHexString(secretBytes).toUpperCase();
    }

    //字节转十六进制字符串
    public static String bytesToHexString(byte[] src){
        StringBuilder stringBuilder = new StringBuilder("");
        for (int i = 0; i < src.length; i++) {
            int v = src[i] & 0xFF;
            String hv = Integer.toHexString(v);
            if (hv.length() < 2) {
                stringBuilder.append(0);
            }
            stringBuilder.append(hv);
        }
        return stringBuilder.toString();
    }

    //字符转byte
    private static byte charToByte(char c) {
        return (byte) "0123456789ABCDEF".indexOf(c);
    }

    //16进制字符串转byte数组
    public static byte[] hexStringToBytes(String hexString) {
        if (hexString == null || hexString.equals("")) {
            return null;
        }
        hexString = hexString.toUpperCase();
        int length = hexString.length() / 2;
        char[] hexChars = hexString.toCharArray();
        byte[] d = new byte[length];
        for (int i = 0; i < length; i++) {
            int pos = i * 2;
            d[i] = (byte) (charToByte(hexChars[pos]) << 4 | charToByte(hexChars[pos + 1]));
        }
        return d;
    }

    //随机密码生成
    public static String randomSecretGenerate(int codeLength){
        final String str = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890~!@#$%^&*()_+-=<>,.?/;:'[]|{}";
        int length = str.length();
        StringBuilder stringBuilder = new StringBuilder();
        int tmp = 0;
        for (int i = 0; i < codeLength; i++) {
            int num = (int)(Math.random()*length);
            if(num-tmp<5&&num+5<length){
                stringBuilder.append(str.charAt(num+5));
                tmp = num;
            }else{
                stringBuilder.append(str.charAt(num));
            }
        }
        return stringBuilder.toString();
    }

    //格式化金额[double]
    public static String moneyFormat(double d){
        DecimalFormat decimalFormat = new DecimalFormat("#,##0.00");
        return decimalFormat.format(d);
    }

    //格式化金额[float]
    public static String moneyFormat(float d){
        DecimalFormat decimalFormat = new DecimalFormat("#,##0.00");
        return decimalFormat.format(d);
    }

}
