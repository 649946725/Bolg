package com.qyzmode;

import com.qyzmode.dao.BlogDao;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class QBokeApplicationTests {

@Autowired
private BlogDao blogDao;
    @Test
    void contextLoads() {

        System.out.println(blogDao.findBlogTagCountBy(10));
    }

}
