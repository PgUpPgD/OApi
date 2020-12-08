package com.zh.oapi_resource.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zh.oapi_resource.dao.ResUrlDao;
import com.zh.oapi_resource.entity.ResUrl;
import com.zh.oapi_resource.service.ResUrlService;
import org.springframework.stereotype.Service;

@Service
public class ResUrlServiceImpl extends
        ServiceImpl<ResUrlDao, ResUrl> implements ResUrlService {
}
