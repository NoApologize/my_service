package com.classic.util;

import java.util.Date;

public class VerificationUtil {

    public static String someKey(String code) throws Exception {
        if (code.equals("")) {
            return code;
        }
        String keyword = "newcrm*1234" + DateUtil.dateToString(new Date(), "yyyy-MM-dd");
        String descode = StringUtil.getMD5Secret(code + keyword);
        return code + ":" + descode;
    }

    public static void main(String[] args) {
        try {
            System.out.println(VerificationUtil.someKey("115683"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
