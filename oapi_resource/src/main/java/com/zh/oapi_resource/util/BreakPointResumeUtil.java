package com.zh.oapi_resource.util;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.CompleteMultipartUploadResult;
import com.aliyun.oss.model.ObjectMetadata;
import com.aliyun.oss.model.UploadFileRequest;
import com.aliyun.oss.model.UploadFileResult;

import java.io.IOException;

//断点上传
public class BreakPointResumeUtil {
    //外网访问地域节点
    private static String endpoint = "oss-cn-shanghai.aliyuncs.com";
    //配置秘钥
    private static String accessKeyId = "";
    private static String accessKeySecret = "";
    //仓库名
    private static String bucketName = "tang-sh";

    public static String upload(String fileName, String file)throws IOException {
        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
        try {
            ObjectMetadata meta = new ObjectMetadata();
            // 指定上传的内容类型。
            // meta.setContentType("text/plain");
            // 通过UploadFileRequest设置多个参数。
            UploadFileRequest uploadFileRequest = new UploadFileRequest(bucketName,fileName);
            // 通过UploadFileRequest设置单个参数。
            // 设置存储空间名称。
            //uploadFileRequest.setBucketName("<yourBucketName>");
            // 设置文件名称。
            //uploadFileRequest.setKey("<yourObjectName>");
            // 指定上传的本地文件地址。
            uploadFileRequest.setUploadFile(file);
            // 指定上传并发线程数，默认为1。
            uploadFileRequest.setTaskNum(2);
            // 指定上传的分片大小，范围为100KB~5GB，默认为文件大小/10000。
            // uploadFileRequest.setPartSize(1 * 1024 * 1024 = 1 MB);
            uploadFileRequest.setPartSize(1 * 1024 * 100);
            // 开启断点续传，默认关闭。
            uploadFileRequest.setEnableCheckpoint(true);
            // 记录本地分片上传结果的文件。开启断点续传功能时需要设置此参数，上传过程中的进度信息会保存在该文件中，
            // 如果某一分片上传失败，再次上传时会根据文件中记录的点继续上传。上传完成后，该文件会被删除。
            // 默认与待上传的本地文件同目录，为uploadFile.ucp。
            // uploadFileRequest.setCheckpointFile("uploadFile.ucp");
            // 文件的元数据。
            uploadFileRequest.setObjectMetadata(meta);
            // 断点续传上传。
            UploadFileResult uploadResult = ossClient.uploadFile(uploadFileRequest);
            CompleteMultipartUploadResult multipartUploadResult = uploadResult.getMultipartUploadResult();
            String eTag = multipartUploadResult.getETag();
            String key = multipartUploadResult.getKey();
            String url = ossClient.generatePresignedUrl(bucketName,fileName,
                        TimeUtil.getDateByYear(10)).toString();
            return eTag + "--" + key + "--" + url ;
        } catch (Throwable e) {
            e.printStackTrace();
        }finally {
            // 关闭OSSClient。
            ossClient.shutdown();
        }
        return null ;
    }

    public static void main(String[] args) {
        try {
            String upload = BreakPointResumeUtil.upload("test.docx", "F:\\视频\\干货教程\\MyBatis-Plus使用教程.docx");
            System.out.println(upload);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
