package com.zh.oapi_common.util;


import com.zh.oapi_common.vo.R;

public class RUtil {
    public static R setOK(){
        R r=new R();
        r.setCode(0);
        r.setMsg("OK");
        r.setData(null);
        return r;
    }
    public static R setOK(Object data){
        R r=new R();
        r.setCode(0);
        r.setMsg("OK");
        r.setData(data);
        return r;
    }
    public static R setERROR(){
        R r=new R();
        r.setCode(1);
        r.setMsg("ERROR");
        r.setData(null);
        return r;
    }
    public static R setERROR(String msg){
        R r=new R();
        r.setCode(1);
        r.setMsg(msg);
        r.setData(null);
        return r;
    }
    public static R setR(boolean issuccess){
        if(issuccess){
            return setOK();
        }else {
            return setERROR();
        }
    }
}
