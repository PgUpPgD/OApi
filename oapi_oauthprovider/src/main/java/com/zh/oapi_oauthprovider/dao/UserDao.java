package com.zh.oapi_oauthprovider.dao;

import com.zh.oapi_entity.entity.user.User;
import org.apache.ibatis.annotations.ResultType;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao {
    @Select("select * from t_user where name=#{n} or phone=#{n}")
    @ResultType(User.class)
    User selectByName(String name);
}
