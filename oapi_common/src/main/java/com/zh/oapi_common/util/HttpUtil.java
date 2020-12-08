package com.zh.oapi_common.util;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * @program: OpenApi
 * @description: 网络请求 可以去请求其他的接口
 * @author: Feri
 * @create: 2019-11-07 09:58
 */
public class HttpUtil {

    //只发消息 不接消息
    public static boolean sendMsg(String u,String msg){

        try {
            //1、创建URL对象
            URL url=new URL(u);
            //2、连接对象
            HttpURLConnection huc=(HttpURLConnection) url.openConnection();
            //3、设置请求信息
            huc.setRequestMethod("POST");
            huc.setDoOutput(true);
            OutputStream os=huc.getOutputStream();
            os.write(msg.getBytes());
            os.flush();
            if(huc.getResponseCode()==200){
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    //发起网络请求 msg为null 为GET /不为null 则为post
    public static String postSend(String u,String msg){
        try {
            //1、创建URL对象
            URL url=new URL(u);
            //2、连接对象
            HttpURLConnection huc=(HttpURLConnection) url.openConnection();
            //3、设置请求信息
            if(msg!=null){
                huc.setRequestMethod("POST");
                huc.setDoOutput(true);
                OutputStream os=huc.getOutputStream();
                os.write(msg.getBytes());
                os.flush();
            }else {
                huc.setRequestMethod("GET");
            }

            if(huc.getResponseCode()==200){
                InputStream is=huc.getInputStream();
                byte[] data=new byte[1024];
                int len;
                StringBuffer buffer=new StringBuffer();
                while ((len=is.read(data))!=-1){
                    buffer.append(new String(data,0,len));
                }
                is.close();
                return buffer.toString();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
