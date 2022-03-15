package com.qyzmode.service;


import com.qyzmode.prjo.Tag;

import java.util.List;

public interface TagService {

    //增加Tag
    int insertTag(Tag tag);
    //通过id查询Tag
    Tag findTagById(long id);
    //通过name查询Tag
    Tag findTagByName(String name);
    //分页查询Type
    List<Tag> selectTag();
    //更改Type
    int updateTag(Tag tag);
    //删除Type
    int deleteTag(long id);
}
