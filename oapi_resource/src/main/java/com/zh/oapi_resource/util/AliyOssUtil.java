package com.zh.oapi_resource.util;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.PutObjectRequest;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

/**
 * 简单上传分为流式上传和文件上传。
 * 流式上传使用InputStream作为文件的数据源。
 * 文件上传使用本地文件作为OSS文件的数据源
 */
public class AliyOssUtil {
    //外网访问地域节点
    private static String endpoint = "oss-cn-shanghai.aliyuncs.com";
    //配置秘钥
    private static String accessKeyId = "";
    private static String accessKeySecret = "";
    //仓库名
    private static String bucketName = "tang-sh";

    //上传文件流
    public static String upload(String fileName, InputStream is,int months){
        // 创建OSSClient实例
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
        //创建putObjectRequest对象
        PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, fileName, is);
        // 如果需要上传时设置存储类型与访问权限，请参考以下示例代码。
//        ObjectMetadata metadata = new ObjectMetadata();
//        metadata.setHeader(OSSHeaders.OSS_STORAGE_CLASS, StorageClass.Standard.toString());
//        metadata.setObjectAcl(CannedAccessControlList.Private);
//        putObjectRequest.setMetadata(metadata);
        // 上传流文件
        ossClient.putObject(putObjectRequest);
        // 获取上传的可访问路径  失效时间 默认为10年
        String url;
        if(months>0){
            url = ossClient.generatePresignedUrl(bucketName,fileName,
                    TimeUtil.getDateByMonth(months)).toString();
        }else {
            url = ossClient.generatePresignedUrl(bucketName,fileName,
                    TimeUtil.getDateByYear(10)).toString();
        }
        ossClient.shutdown();
        return url;
    }
    public static String upload(String fileName, byte[] data){
        return upload(fileName,new ByteArrayInputStream(data),0);
    }


    //文件删除
    public static boolean delFile(String fileName){
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
        try{
            ossClient.deleteObject(bucketName,fileName);
            return true;
        }catch (Exception e){
            return false;
        }
    }


}
