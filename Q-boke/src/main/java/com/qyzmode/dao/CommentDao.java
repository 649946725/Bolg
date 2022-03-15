package com.qyzmode.dao;


import com.qyzmode.prjo.Comment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface CommentDao {

    List<Comment> findByBlogId(Long blog_id);

    List<Comment>  findChildComment(Long parent_comment_id);

    int insertComment(@Param("comment") Comment comment);

}
