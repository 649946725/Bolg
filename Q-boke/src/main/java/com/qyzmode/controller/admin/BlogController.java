package com.qyzmode.controller.admin;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.qyzmode.prjo.Blog;
import com.qyzmode.prjo.User;
import com.qyzmode.service.BlogService;
import com.qyzmode.service.TagService;
import com.qyzmode.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class BlogController {

    @Autowired
    private BlogService blogService;

    @Autowired
    private TypeService typeService;
    @Autowired
    private TagService tagService;

    @GetMapping("/blog")
    public String blog(Model model,Blog blog, @RequestParam(defaultValue = "1",value = "pageNum") Integer pageNum)
    {
        PageHelper.startPage(pageNum,10);
        List<Blog> blogList=blogService.findByConditionBlog(blog);
        PageInfo<Blog> pageInfo=new PageInfo<>(blogList);
        model.addAttribute("page",pageInfo);
        model.addAttribute("types",typeService.selectType());
        return "/admin/blog";
    }
    @PostMapping("/blog/serach")
    public String blogSerach(Model model,Blog blog, @RequestParam(defaultValue = "1",value = "pageNum") Integer pageNum)
    {
        PageHelper.startPage(pageNum,10);
        List<Blog> blogList=blogService.findByConditionBlog(blog);
        PageInfo<Blog> pageInfo=new PageInfo<>(blogList);
        model.addAttribute("page",pageInfo);

        return "/admin/blog :: bloglist";
    }

    @GetMapping("/blog/fabu")
    public String fabu(Model model)
    {
        model.addAttribute("types",typeService.selectType());
        model.addAttribute("tags",tagService.selectTag());
        return "admin/fabu";
    }
    @GetMapping("/blog/{id}/edit-blog")
    public String editBlog(@PathVariable long id, Model model)
    {
        model.addAttribute("types",typeService.selectType());
        model.addAttribute("tags",tagService.selectTag());
        model.addAttribute("blog",blogService.findBlogById(id));
        return "admin/fabu";
    }

    @PostMapping("/addblog")
    public String addblog(Blog blog, RedirectAttributes redirectAttributes, HttpSession session)
    {
        User user= (User) session.getAttribute("user");
        blog.setUser_id(user.getId());
       int i= blogService.insertBlog(blog);
       if(i==0)
       {
           redirectAttributes.addFlashAttribute("message","添加失败");

       }
       else {
           redirectAttributes.addFlashAttribute("message","添加成功");
       }
        return "redirect:/admin/blog";
    }
    @PostMapping("/editBlog/{id}")
    public String editblog(@PathVariable Long id,Blog blog, RedirectAttributes redirectAttributes, HttpSession session)
    {
        User user= (User) session.getAttribute("user");
        blog.setUser_id(user.getId());
        int i= blogService.updateBlog(blog);
        if(i==0)
        {
            redirectAttributes.addFlashAttribute("message","修改失败");

        }
        else {
            redirectAttributes.addFlashAttribute("message","修改成功");
        }
        return "redirect:/admin/blog";
    }

    @GetMapping("/blog/{id}/delete")
    public String delete(@PathVariable long id,RedirectAttributes redirectAttributes)
    {
        int i=blogService.deleteBlog(id);
        if(i==0)
        {
            redirectAttributes.addFlashAttribute("message","删除失败！");
        }
        else {
            redirectAttributes.addFlashAttribute("message","恭喜您！删除成功");
        }
        return "redirect:/admin/blog";
    }

}

