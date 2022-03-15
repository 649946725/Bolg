package com.qyzmode.dao;

import com.qyzmode.prjo.Tag;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface TagDao {

    int insertTag(@Param("tag")Tag tag);
    int updateTag(@Param("tag")Tag tag);
    int deleteTag(Long id);
    List<Tag> selectTag();
    Tag findTagByName(String name);
    Tag findTagById(Long id);
}
