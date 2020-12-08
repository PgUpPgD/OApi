package com.zh.oapi_userprovider.dao;

import com.zh.oapi_entity.entity.user.User;
import com.zh.oapi_entity.entity.user.UserLog;
import org.apache.ibatis.annotations.ResultType;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao {
//    @Insert("insert into t_user(name,pass,phone) values(#{user},#{pass},#{phone})")
//    @Options(useGeneratedKeys = true,keyProperty = "id")
    //用户注册接口
    int insert(User user);
    //用户注册和登录信息日志
    int add(UserLog userLog);
    //用户昵称信息和手机号校验是否已存在
    @Select("select * from t_user where name = #{n} or phone = #{n}")
    @ResultType(User.class)
    User selectOne(String n);




}
