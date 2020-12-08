package com.zh.oapi_userprovider.dao;

import com.zh.oapi_entity.entity.user.UserReal;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.ResultType;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRealDao {
    //新增
    @Insert("insert into t_userreal(uid,realname,cardno,fronturl,backurl,handurl,ctime)" +
            "values(#{uid},#{realname},#{cardno},#{fronturl},#{backurl},#{handurl},now())")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insert(UserReal real);   //添加实名认证信息

    //查询
    @Select("select * from t_userreal where uid = #{uid}")
    @ResultType(UserReal.class)
    UserReal selectByUid(int uid);     //查找用户实名认证信息
}
