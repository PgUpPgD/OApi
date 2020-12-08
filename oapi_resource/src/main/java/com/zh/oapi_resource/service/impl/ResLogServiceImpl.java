package com.zh.oapi_resource.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zh.oapi_resource.dao.ResLogDao;
import com.zh.oapi_resource.entity.ResLog;
import com.zh.oapi_resource.service.ResLogService;
import org.springframework.stereotype.Service;

@Service
public class ResLogServiceImpl extends
        ServiceImpl<ResLogDao, ResLog> implements ResLogService {
}
