package com.zh.oapi_msgprovider.util;

import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;

public class AliSmsUtil {
    public static String sendSms(String temCode,String phone,String msg) {
        DefaultProfile profile = DefaultProfile.getProfile(
                "cn-hangzhou"
                , ""
                , "");
        IAcsClient client = new DefaultAcsClient(profile);
        CommonRequest request = new CommonRequest();
        request.setMethod(MethodType.POST);
        request.setDomain("dysmsapi.aliyuncs.com");
        request.setVersion("2017-05-25");
        request.setAction("SendSms");
        request.putQueryParameter("RegionId", "cn-hangzhou");
        request.putQueryParameter("PhoneNumbers", phone);
        request.putQueryParameter("SignName", "来自小地雷的短信");
        request.putQueryParameter("TemplateCode", temCode);
        request.putQueryParameter("TemplateParam", msg);
        try {
            CommonResponse response = client.getCommonResponse(request);
            return response.getData();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
