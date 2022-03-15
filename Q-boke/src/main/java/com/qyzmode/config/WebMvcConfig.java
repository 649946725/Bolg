package com.qyzmode.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    @Autowired
    private MyInterceptor myInterceptor;

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/admin").setViewName("/admin/login.html");
        registry.addViewController("/admin/index").setViewName("/admin/index.html");
        registry.addViewController("/admin/blog/fabu").setViewName("/admin/fabu.html");
        registry.addViewController("/admin/blog").setViewName("/admin/blog.html");
        registry.addViewController("/admin/types").setViewName("/admin/types.html");
        registry.addViewController("/admin/tags").setViewName("/admin/tags.html");
        registry.addViewController("/admin/types/add-type").setViewName("/admin/add-type.html");
        registry.addViewController("/admin/tags/add-tag").setViewName("/admin/add-tag.html");
        registry.addViewController("/admin/types/edit-type").setViewName("/admin/add-type.html");
        registry.addViewController("/admin/tags/edit-tag").setViewName("/admin/add-tag.html");
        registry.addViewController("/").setViewName("index.html");
        registry.addViewController("/about").setViewName("about.html");
        registry.addViewController("/archives").setViewName("archives.html");
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //注册自己的拦截器
        registry.addInterceptor(myInterceptor).
                addPathPatterns("/admin/**").excludePathPatterns("/admin")
                .excludePathPatterns("/admin/login")
                .excludePathPatterns("/admin/captcha");
    }

}
