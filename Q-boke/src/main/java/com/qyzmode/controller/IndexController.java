package com.qyzmode.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.qyzmode.prjo.Blog;
import com.qyzmode.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class IndexController {

   @Autowired
   private BlogService blogService;
    @GetMapping("/")
    public String getIndex(Model model, @RequestParam(defaultValue = "1",value ="pageNum" ) Integer pageNum){

        PageHelper.startPage(pageNum,10);
        List<Blog> blogList=blogService.selectBlog();
        PageInfo<Blog> pageInfo=new PageInfo<>(blogList);
        model.addAttribute("page",pageInfo);
        model.addAttribute("types",blogService.findBlogTypeCountBy(6));
        model.addAttribute("tags",blogService.findBlogTagCountBy(10));
        model.addAttribute("blog_recommends",blogService.findRecommendByUpdateTimeBlog(8));
        return "index";
    }

    @GetMapping("/blog/{id}/detail")
     public String detail(@PathVariable Long id,Model model){
        model.addAttribute("blog",blogService.findBlogByIdAndMarkdown(id));
        return "detail";
     }
     @GetMapping("/blog/search")
    public String search(@RequestParam(defaultValue = "1",value = "pageNum") Integer pageNum,String search_text,Model model)
     {
         System.out.println(search_text);
         PageHelper.startPage(pageNum,10);
         List<Blog> blogList=blogService.searchBlog(search_text);
         PageInfo<Blog> pageInfo=new PageInfo<>(blogList);
         model.addAttribute("page",pageInfo);
         model.addAttribute("search_text",search_text);
         return "search";
     }

}
