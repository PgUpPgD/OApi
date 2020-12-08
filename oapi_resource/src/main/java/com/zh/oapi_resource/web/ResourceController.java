package com.zh.oapi_resource.web;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.api.R;
import com.zh.oapi_resource.entity.ResLog;
import com.zh.oapi_resource.entity.ResUrl;
import com.zh.oapi_resource.service.ResLogService;
import com.zh.oapi_resource.service.ResUrlService;
import com.zh.oapi_resource.service.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

/**
 * @program: OpenApi
 * @description:  实现资源操作接口
 * @author: Feri
 * @create: 2019-10-30 14:38
 */
@RestController
public class ResourceController {

    @Autowired
    private ResourceService resourceService;
    @Autowired
    private ResUrlService urlService;
    @Autowired
    private ResLogService logService;
    //文件上传
    @PostMapping("/api/resource/uploadFile.do")
    public R upload(@RequestParam MultipartFile file, HttpServletRequest request) throws IOException {
        //https://www.iteye.com/blog/momodog-295946   获取用户的Ip地址
        R upload = resourceService.upload(file, request.getRemoteAddr());
        return R.ok(upload);
    }

    //文件删除
    @PostMapping("/api/resource/deleteFile.do")
    public R upload(@RequestParam String name) throws IOException {
        R upload = resourceService.delFile(name);
        return R.ok(upload);
    }

    //文件路径查询
    @GetMapping("/api/resource/queryFile.do")
    public R getUrl(String name){
        ResUrl objName = urlService.getOne(new QueryWrapper<ResUrl>().eq("obj_name", name));
        return R.ok(objName);
    }

    //操作记录查询
    @GetMapping("/api/resource/allLog.do")
    public R allLog(){
        List<ResLog> list = logService.list();
        return R.ok(list);
    }
}
