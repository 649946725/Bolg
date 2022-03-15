package com.qyzmode.service.Imp;

import com.qyzmode.dao.BlogDao;
import com.qyzmode.dao.TagDao;
import com.qyzmode.dao.TypeDao;
import com.qyzmode.dao.UserDao;
import com.qyzmode.exception.NotFoundException;
import com.qyzmode.prjo.Blog;
import com.qyzmode.prjo.BlogTypeOrTagCount;
import com.qyzmode.prjo.Tag;
import com.qyzmode.service.BlogService;
import com.qyzmode.util.MarkDownUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class BlogServiceImp implements BlogService {

    @Autowired
    private BlogDao blogDao;

    @Autowired
    private TypeDao typeDao;
    @Autowired
    private TagDao tagDao;
    @Autowired
    private UserDao userDao;
    @Override
    public Blog findBlogById(Long id) {
        return blogDao.findBlogById(id);
    }

    @Override
    @Transactional
    public Blog findBlogByIdAndMarkdown(Long id) {
        Blog blog=blogDao.findBlogById(id);
        if(blog==null)
            throw new NotFoundException("该博客不存在或者已被删除");
        else {
            blog.setUser(userDao.findById(blog.getUser_id()));
            blog.setType(typeDao.findTypeById(blog.getType_id()).getName());
            String tag_id=blog.getTag_id();
            String [] array=tag_id.split(",");
            List<Tag> tagList=new ArrayList<>();
            for (String tag:array
            ) {
                tagList.add(tagDao.findTagById(Long.parseLong(tag)));
            }
            blog.setTags(tagList);
            String content=MarkDownUtil.MarkdownToHtmlAndMarkdownTable(blog.getContent());
            blog.setContent(content);
            blogDao.updateView(id);
            return blog;
        }

    }

    @Override
    public List<Blog> findByConditionBlog(Blog blog) {
        List<Blog> blogList=blogDao.findByConditionBlog(blog);
        for (Blog blog1:blogList
             ) {
            blog1.setType(typeDao.findTypeById(blog1.getType_id()).getName());
        }
        return blogList;
    }

    @Override
    public List<Blog> findByType(Long type_id) {
        List<Blog> blogList=blogDao.findByType(type_id);
        for (Blog blog1:blogList
        ) {
            blog1.setType(typeDao.findTypeById(blog1.getType_id()).getName());
            blog1.setUser(userDao.findById(blog1.getUser_id()));

        }
        return blogList;
    }

    @Override
    public List<Blog> findByTag(Long tag_id) {
        List<Blog> blogList=blogDao.findByTag(tag_id);
        for (Blog blog1:blogList
        ) {
            String [] array=blog1.getTag_id().split(",");
            List<Tag> tagList=new ArrayList<>();
            for (String tag:array
            ) {
                tagList.add(tagDao.findTagById(Long.parseLong(tag)));
            }
            blog1.setTags(tagList);
            blog1.setType(typeDao.findTypeById(blog1.getType_id()).getName());
            blog1.setUser(userDao.findById(blog1.getUser_id()));

        }
        return blogList;
    }

    @Override
    public List<Blog> selectBlog() {
        List<Blog> blogList=blogDao.selectBlog();
        for (Blog blog1:blogList
        ) {
            blog1.setUser(userDao.findById(blog1.getUser_id()));
            blog1.setType(typeDao.findTypeById(blog1.getType_id()).getName());
        }
        return blogList;
    }

    @Override
    public List<BlogTypeOrTagCount> findBlogTypeCountBy(Integer num) {

        return blogDao.findBlogTypeCountBy(num);
    }

    @Override
    public List<Blog> findRecommendByUpdateTimeBlog(Integer num) {
        return blogDao.findRecommendByUpdateTimeBlog(num);
    }

    @Override
    public List<BlogTypeOrTagCount> findBlogTagCountBy(Integer num) {

        return blogDao.findBlogTagCountBy(num);
    }

    @Override
    public List<Blog> searchBlog(String search_text) {
        List<Blog> blogList=blogDao.searchBlog(search_text);
        for (Blog blog1:blogList
        ) {
            blog1.setUser(userDao.findById(blog1.getUser_id()));
            blog1.setType(typeDao.findTypeById(blog1.getType_id()).getName());
        }
        return blogList;
    }

    @Override
    public Integer selectBlogCount() {
        return blogDao.selectBlogCount();
    }

    @Override
    @Transactional
    public int insertBlog(Blog blog) {
        //初始化一些数据
        blog.setCreate_time(new Timestamp(new Date().getTime()));
        blog.setUpdate_time(new Timestamp(new Date().getTime()));
        blog.setViews(0);

        return blogDao.insertBlog(blog);
    }

    @Override
    @Transactional
    public int updateBlog(Blog blog) {
        blog.setUpdate_time(new Timestamp(new Date().getTime()));
        int i = blogDao.updateBlog(blog);
        if (i == 0)
            throw new NotFoundException("不存在该博客");
        else
            return i;
    }

    @Override
    @Transactional
    public int deleteBlog(Long id) {
        return blogDao.deleteBlog(id);
    }
}
