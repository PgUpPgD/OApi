package com.zh.oapi_resource.util;

import cn.hutool.core.thread.ThreadFactoryBuilder;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.OSSException;
import com.zh.oapi_common.util.DateUtil;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Date;
import java.util.concurrent.*;

/**
 * @author cao
 * @create 2018-06-12 上午10:18
 * 阿里云上传文件
 **/

public class AliyunOSS {

    private static String endpoint = "http://oss-cn-shanghai.aliyuncs.com";
    private static String accessKeyId = "";
    private static String accessKeySecret = "";
    private static String bucketName = "xrk-xbb";

    private static String uploadFile = "finance/";
    private static String imageBaseUrl = "http://xbb.oss.muzhongyun.com/";


    /**
     * 上传文件到阿里云
     * @param filePath
     * @return
     */
    public String upLoadFile(String filePath)
    {
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

        String newFilePath =uploadFile+ DateUtil.format(new Date(),"yyyy/MM/dd")+"/";

        File file = new File(filePath);

        newFilePath = newFilePath + file.getName();

        ThreadFactory namedThreadFactory = new ThreadFactoryBuilder().setNamePrefix("aliyun-oss-pool-%d").build();

        //Common Thread Pool
        ExecutorService threadPool = new ThreadPoolExecutor(5, 200,
                0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<Runnable>(1024), namedThreadFactory, new ThreadPoolExecutor.AbortPolicy());


        String finalNewFilePath = newFilePath;
        FutureTask<String> future = new FutureTask<String>(new Callable<String>() {
            @Override
            public String call(){
                try {
                    InputStream stream =  new FileInputStream(file);
                    ossClient.putObject(bucketName, finalNewFilePath, stream);

                } catch (OSSException oe) {
                    oe.printStackTrace();
                } catch (Throwable e) {
                    e.printStackTrace();
                } finally {
                    ossClient.shutdown();
                }
                return "";
            }
        });

        threadPool.execute(future);

        //关闭线程池
        if(!threadPool.isShutdown()){
            threadPool.shutdown();
        }

        return imageBaseUrl+finalNewFilePath;

    }

    /**
     * 上传文件到阿里云
     * @param fileName  文件名，前面不用加/线
     * @param inputStream
     * @return
     */
    public String upLoadFile(String fileName,InputStream inputStream)
    {
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

        String newFilePath =uploadFile+ DateUtil.format(new Date(),"yyyy/MM/dd")+"/"+fileName;

        ThreadFactory namedThreadFactory = new ThreadFactoryBuilder().setNamePrefix("aliyun-oss-pool-%d").build();

        //Common Thread Pool
        ExecutorService threadPool = new ThreadPoolExecutor(5, 30,
                0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<Runnable>(1024), namedThreadFactory, new ThreadPoolExecutor.AbortPolicy());

        String finalNewFilePath = newFilePath;
        FutureTask<String> future = new FutureTask<String>(new Callable<String>() {
            @Override
            public String call(){
                try {
                    //内容准备
                    InputStream stream =  inputStream;//bufferedImageToInputStream(image);
                    ossClient.putObject(bucketName, finalNewFilePath, stream);

                } catch (OSSException oe) {
                    oe.printStackTrace();
                } catch (Throwable e) {
                    e.printStackTrace();
                } finally {
                    ossClient.shutdown();
                }
                return "";
            }
        });

        threadPool.execute(future);

        //关闭线程池
        if(!threadPool.isShutdown()){
            threadPool.shutdown();
        }

        return imageBaseUrl+finalNewFilePath;
    }



    /**
     * 将BufferedImage转换为InputStream
     * @param image
     * @return
     */
    private InputStream bufferedImageToInputStream(BufferedImage image){
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        try {
            ImageIO.write(image, "png", os);
            InputStream input = new ByteArrayInputStream(os.toByteArray());
            return input;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
