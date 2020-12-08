package com.zh.oapi_userprovider.util;

import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;

import java.util.HashMap;
import java.util.Map;

public class IdCardUtil {
	public static void main(String[] args) {
		String host = "https://ocridcard.market.alicloudapi.com";
	    String path = "/idCardAuto";
	    String method = "POST";
	    String appcode = "ccb75f2ba413476a9daa52f641c491b4";
	    Map<String, String> headers = new HashMap<String, String>();
	    //最后在header中的格式(中间是英文空格)为Authorization:APPCODE 83359fd73fe94948385f570e3c139105
		headers.put("Authorization", "APPCODE " + appcode);
	    //根据API的要求，定义相对应的Content-Type
	    headers.put("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
		Map<String, String> querys = new HashMap<String, String>();
		Map<String, String> bodys = new HashMap<String, String>();
	    bodys.put("image", "https://tang-sh.oss-cn-shanghai.aliyuncs.com/QQ%E5%9B%BE%E7%89%8720190617091949.jpg");
		//或者base64
		//bodys.put("image", "data:image/jpeg;base64,........");   //jpg图片
		//bodys.put("image", "data:image/png;base64,........");   //png图片
	    try {
			HttpResponse response = HttpUtils.doPost(host, path, method, headers, querys, bodys);
			System.out.println(response.toString());//如不输出json, 请打开这行代码，打印调试头部状态码。
			//状态码: 200 正常；400 URL无效；401 appCode错误； 403 次数用完； 500 API网管错误
			//获取response的body
	    	System.out.println(EntityUtils.toString(response.getEntity()));
	    } catch (Exception e) {
	    	e.printStackTrace();
	    }
	}

}
