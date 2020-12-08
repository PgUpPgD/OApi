package com.zh.oapi_msgprovider.dao;

import com.zh.oapi_entity.entity.msg.Sms;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.ResultType;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SmsDao {
    //添加发送短信的记录
    @Insert("insert into t_sms(recphone, msg, ctime, flag)"
             + "values(#{recphone}, #{msg}, now(), #{flag})")
    int insert(Sms sms);

    //查询所有短信记录
    @Select("select * from t_sms order by ctime desc")
    @ResultType(Sms.class)
    List<Sms> selectAll();

    //查询某个手机号的短信记录
    @Select("select * from t_sms where recphone = #{phone} order by ctime desc")
    @ResultType(Sms.class)
    Sms selectOne(String phone);

}
