package com.qyzmode.service;


import com.qyzmode.prjo.Comment;

import java.util.List;

public interface CommentService {

   List<Comment>  findByBlogId(Long blog_id);


   int insertComment(Comment comment);
}
