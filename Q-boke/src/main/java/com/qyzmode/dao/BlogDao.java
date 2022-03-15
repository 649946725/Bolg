package com.qyzmode.dao;


import com.qyzmode.prjo.Blog;
import com.qyzmode.prjo.BlogTypeOrTagCount;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Mapper
@Repository
public interface BlogDao {

    //查询一个博客
    Blog findBlogById(Long id);

    //用于分页查询 通过一些条件
    List<Blog> findByConditionBlog(@Param("blog") Blog blog);

    List<Blog> selectBlog();
    //查看最新推荐的博客

    List<Blog> findByType(@Param("type_id") Long type_id);

    List<Blog> findByTag(@Param("tag_id") Long tag_id);

    List<Blog> findRecommendByUpdateTimeBlog(Integer num);

    List<BlogTypeOrTagCount> findBlogTypeCountBy(Integer num);

    List<BlogTypeOrTagCount> findBlogTagCountBy(Integer num);

    List<Blog> searchBlog(String search_text);

    List<String> findBlogYear();

    List<Blog>   findByYearBlog(String year);

    Integer selectBlogCount();

    int insertBlog(@Param("blog") Blog blog);

    int updateBlog(@Param("blog") Blog blog);

    int deleteBlog(Long id);

    int updateView(Long id);


}
