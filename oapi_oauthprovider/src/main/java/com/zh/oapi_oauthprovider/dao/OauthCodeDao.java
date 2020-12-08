package com.zh.oapi_oauthprovider.dao;

import com.zh.oapi_entity.entity.authc.OauthCode;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultType;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
public interface OauthCodeDao {

    @Insert("insert into t_oauthcode (code, apply_key, redirect_url, status, ctime)" +
            " values (#{code}, #{applyKey}, #{redirectUrl}, #{status}, #{ctime})")
    int insert(OauthCode code);

    @Select("select * from t_oauthcode where code=#{code} and apply_key=#{key}")
    @ResultType(OauthCode.class)
    OauthCode selectByKey(@Param("code") String code, @Param("key") String key);
}
