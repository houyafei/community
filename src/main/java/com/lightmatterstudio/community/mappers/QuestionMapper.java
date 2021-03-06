package com.lightmatterstudio.community.mappers;

import com.lightmatterstudio.community.model.Question;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

/**
 * Yafei
 * 2021/5/24
 */
@Mapper
public interface QuestionMapper {


    @Insert("insert into question(title,description,gmt_create,gmt_modified,creator,tag) values(#{title},#{description},#{gmtCreate},#{gmtModified},#{creator},#{tag})")
    void createQuestion(Question question);

}
