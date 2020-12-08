package com.zh.oapi_resource.service;

import com.baomidou.mybatisplus.extension.api.R;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface ResourceService {

    //文件上传
    R upload(MultipartFile file, String ip) throws IOException;
    //文件删除
    R delFile(String name);
}
