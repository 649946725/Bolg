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
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class TypesContrller {

    @Autowired
    private BlogService blogService;
    @GetMapping("/types/{id}")
    public String types(@PathVariable Long id,Model model,@RequestParam(defaultValue = "1",value ="pageNum" ) Integer pageNum)
    {
        PageHelper.startPage(pageNum,10);
        List<Blog> blogList=blogService.findByType(id);
        PageInfo<Blog> pageInfo=new PageInfo<>(blogList);
        model.addAttribute("page",pageInfo);
        model.addAttribute("types",blogService.findBlogTypeCountBy(1000));
        model.addAttribute("active_id",id);
        return "types";
    }



}
