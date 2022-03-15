package com.qyzmode.service.Imp;

import com.qyzmode.dao.BlogDao;
import com.qyzmode.prjo.Blog;
import com.qyzmode.service.ArchivesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ArchivesServiceImp implements ArchivesService {

    @Autowired
    private BlogDao blogDao;

    @Override
    public Map<String, List<Blog>> archives() {
        Map<String, List<Blog>> map=new HashMap<>();
        List<String> years=blogDao.findBlogYear();
        for (String year:years
             ) {
            List<Blog> blogList=blogDao.findByYearBlog(year);
            map.put(year,blogList);
        }
        return map;
    }
}
