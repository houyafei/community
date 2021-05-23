package com.lightmatterstudio.community.mappers;

import com.lightmatterstudio.community.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * Yafei
 * 2021/5/23
 */

@Mapper
public interface GithubUserMappers {


    @Insert("insert into user(account_id, name, token, gmt_create, gmt_modified) values(#{accountId},#{name},#{token},#{gmtCreate},#{gmtModified})")
    void insertUser(User user);


    @Select("select id,account_id as accountId , name, token, gmt_create as gmtCreate , gmt_modified  as gmtModified from user where token = #{token}")
    User getUser(String token);
}
