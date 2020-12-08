package com.zh.oapi_oauthprovider.dao;


import com.zh.oapi_entity.entity.authc.OauthCodeLog;
import org.springframework.stereotype.Repository;

@Repository
public interface OauthCodeLogDao {
    int insert(OauthCodeLog codeLog);
}
