package com.zh.oapi_common.test;

import org.springframework.util.StringUtils;

public class Test {

    public String decryptToken(String encryptData) {
        AES AES_INSTANCE = new AES("");
        String data = AES_INSTANCE.decrypt(encryptData, "UTF-8");
        String str = "";
        if (data.indexOf("|") > 0) {
            str =  StringUtils.split(data, "|")[0];
        }
        return str;
    }

    public static void main(String[] args) {
       String str =  new Test().decryptToken("hvcj3EiXbTy/vC+p8np28kPzaVfUoGaBTi8XRFPfgoo=");
       System.out.println(str);
    }
}
