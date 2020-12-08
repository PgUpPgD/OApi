package com.zh.oapi_common.util;

import com.zh.oapi_common.config.SystemConfig;

public class CodeUtil {
    //AES加密  生成授权码
    public static String createCode(String json){
        return EncryptionUtil.AESEnc(SystemConfig.CODE_KEY,json);
    }
    //AES解密 解析授权码
    public static String parseCode(String code){
        return EncryptionUtil.AESDec(SystemConfig.CODE_KEY,code);
    }

    public static void main(String[] args) {
        String code = createCode("1123456sdfsdf");
        System.out.println(code);
        String s = parseCode(code);
        System.out.println(s);

    }


}
