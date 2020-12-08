package com.zh.oapi_resource.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.api.R;
import com.zh.oapi_resource.dao.ResLogDao;
import com.zh.oapi_resource.dao.ResUrlDao;
import com.zh.oapi_resource.entity.ResLog;
import com.zh.oapi_resource.entity.ResUrl;
import com.zh.oapi_resource.service.ResourceService;
import com.zh.oapi_resource.util.AliyOssUtil;
import com.zh.oapi_resource.util.StrUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Date;

@Service
public class ResourceServiceImpl implements ResourceService{

    @Autowired
    private ResUrlDao resUrlDao;
    @Autowired
    private ResLogDao resLogDao;

    @Override
    public R upload(MultipartFile file, String ip) {
        String url = "";
        ResUrl resUrl = new ResUrl();
        //验证上传的文件名称，并命名处理
        if (!file.isEmpty()){
            ResLog resLog = new ResLog();
            resLog.setCtime(new Date());
            //获取上传的文件名称并命名处理
            String filename = file.getOriginalFilename();
            String fn= StrUtil.renameFile(filename);
            //将文件内容上传到oss
            try {
                url= AliyOssUtil.upload(fn,file.getBytes());
                resUrl.setCtime(new Date());
                resUrl.setFlagMonths(240);
                resUrl.setObjUrl(url);
                resUrl.setObjName(fn);
                resUrl.setProname("默认项目");
                if(resUrlDao.insert(resUrl)>0){
                    resLog.setInfo("文件上传并新增成功："+fn);
                    resLog.setRid(resUrl.getId());
                }else {
                    resLog.setInfo("文件上传成功，数据库添加记录失败："+url);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            resLog.setIp(ip);
            resLogDao.insert(resLog);
        }
        return null;
    }

    @Override
    public R delFile(String name) {
        if(AliyOssUtil.delFile(name)){
            int i = resUrlDao.delete(new QueryWrapper<ResUrl>().eq("obj_name", name));
            if (i > 0){
                return R.ok("删除成功");
            }
            return R.ok("Oss删除成功,数据库记录删除失败");
        }else {
            return R.ok("删除失败");
        }
    }
}
