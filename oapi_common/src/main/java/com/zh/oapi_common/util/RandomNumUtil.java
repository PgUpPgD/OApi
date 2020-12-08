package com.zh.oapi_common.util;

import java.util.Random;

/**
 * @program: OpenApi
 * @description:
 * @author: Feri
 * @create: 2019-10-29 10:30
 */
public class RandomNumUtil {

    //生成指定位数的数字 //3  100-999=(0-899  )+100=(0- (900-1))+100
    public static int createNum(int len){
        Random random=new Random();
        return (int)(random.nextInt((int)(Math.pow(10,len)-Math.pow(10,len-1)))+Math.pow(10,len-1));
    }//nextInt(10)   即 生成从0到9的随机数

//    public static void main(String[] args) {
//        for(int i=1;i<100000000;i++){
//            int n=createNum(6);//100000-999999
//            if(n<100000 || n>999999){
//                System.out.println(n);
//            }
//        }
//    }
}
