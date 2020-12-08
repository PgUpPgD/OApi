package com.zh.oapi_oauthprovider.dao;

import com.zh.oapi_entity.entity.apply.Apply;
import com.zh.oapi_entity.entity.dto.ApplyUrlDto;
import org.apache.ibatis.annotations.ResultType;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

/**
 * @program: OpenApi
 * @description:
 * @author: Feri
 * @create: 2019-11-06 15:45
 */
@Repository
public interface ApplyDao {
    //查询app的具体信息
    Apply selectByKey(String key);

    @Select("select a.uid,a.applyKey,p.redirectUrl from t_apply a inner join t_pcapplydetail p on a.id=p.aid where a.applyKey=#{key}")
    @ResultType(ApplyUrlDto.class)
    ApplyUrlDto selectKey(String key);
}
