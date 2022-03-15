package com.qyzmode.service;


import com.qyzmode.prjo.Blog;
import com.qyzmode.prjo.BlogTypeOrTagCount;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BlogService {

    //查询一个博客
    Blog findBlogById(Long id);

    Blog findBlogByIdAndMarkdown(Long id);
    //用于分页查询 通过一些条件
    List<Blog> findByConditionBlog(Blog blog);
    List<Blog> findByType(Long type_id);
    List<Blog> findByTag(@Param("tag_id") Long tag_id);
    List<Blog> selectBlog();
    List<BlogTypeOrTagCount> findBlogTypeCountBy(Integer num);

    //查看最新推荐的博客
    List<Blog> findRecommendByUpdateTimeBlog(Integer num);

    List<BlogTypeOrTagCount> findBlogTagCountBy(Integer num);

    List<Blog> searchBlog(String search_text);

    Integer selectBlogCount();

    int insertBlog(Blog blog);

    int updateBlog(Blog blog);

    int deleteBlog(Long id);

}
