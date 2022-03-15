package com.qyzmode.controller.admin;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.qyzmode.prjo.Tag;
import com.qyzmode.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class TagController {

    @Autowired
    private TagService tagService;

    @GetMapping("/tags")
    public String tags(Model model,
                        @RequestParam(defaultValue = "1",value = "pageNum")
                                Integer pageNum){
        PageHelper.startPage(pageNum,10);
        List<Tag> tagList=tagService.selectTag();
        PageInfo<Tag> pageInfo=new PageInfo<>(tagList);
        model.addAttribute("page",pageInfo);
        return "/admin/tags";
    }
    @PostMapping("/addTag")
    public String addTag(Tag tag, RedirectAttributes redirectAttributes){
        Tag tag1=tagService.findTagByName(tag.getName());

        if(tag1!=null)
        {
            redirectAttributes.addFlashAttribute("message","操作失败！不能添加重复的标签！");
            return "redirect:/admin/tags/add-tag";
        }
        else {
            int i=tagService.insertTag(tag);
            if(i==0)
            {
                redirectAttributes.addFlashAttribute("message","添加失败！");
            }
            else {
                redirectAttributes.addFlashAttribute("message","恭喜您！添加成功");
            }
            return "redirect:/admin/tags";
        }
    }

    @PostMapping("/editTag/{id}")
    public String editTag(@PathVariable Long id,Tag tag, RedirectAttributes redirectAttributes){
        Tag tag1=tagService.findTagByName(tag.getName());

        if(tag1!=null)
        {
            redirectAttributes.addFlashAttribute("message","操作失败！不能添加重复的标签！");
            redirectAttributes.addFlashAttribute("tag",tag);
            return "redirect:/admin/tags/edit-tag";
        }
        else {
            tag.setId(id);
            int i=tagService.updateTag(tag);
            if(i==0)
            {
                redirectAttributes.addFlashAttribute("message","更新失败！");
            }
            else {
                redirectAttributes.addFlashAttribute("message","恭喜您！更新成功");
            }
            return "redirect:/admin/tags";
        }
    }
    @GetMapping("/tags/{id}/edit-tag")
    public String editTag(@PathVariable Long id,Model model){
        model.addAttribute("tag",tagService.findTagById(id));

        return "/admin/add-tag";
    }
    @GetMapping("/tags/{id}/delete")
    public String delete(@PathVariable Long id,RedirectAttributes redirectAttributes){
        int i=tagService.deleteTag(id);
        if(i==0)
        {
            redirectAttributes.addFlashAttribute("message","删除失败！");
        }
        else {
            redirectAttributes.addFlashAttribute("message","恭喜您！删除成功");
        }
        return "redirect:/admin/tags";
    }
}
