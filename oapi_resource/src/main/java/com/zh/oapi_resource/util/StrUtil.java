package com.zh.oapi_resource.util;

import java.util.Random;

/**
 * @program: OpenApi
 * @description:
 * @author: Feri
 * @create: 2019-10-30 14:45
 */
public class StrUtil {
    //重命名 上传的文件名
    public static String renameFile(String filename){
        if(filename.length()>20){
            filename=filename.substring(filename.length()-20);
        }
        return System.currentTimeMillis()+"_"+new Random().nextInt(10000)+"_"+filename;
    }
}
