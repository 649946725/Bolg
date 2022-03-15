package com.qyzmode.controller.admin;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.qyzmode.prjo.Type;
import com.qyzmode.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class TypeController {

    @Autowired
    private TypeService typeService;

    @GetMapping("/types")
    public String types(Model model,
                        @RequestParam(defaultValue = "1",value = "pageNum")
                                Integer pageNum){
        PageHelper.startPage(pageNum,10);
        List<Type> typeList=typeService.selectType();
        PageInfo<Type> pageInfo=new PageInfo<>(typeList);
        model.addAttribute("page",pageInfo);
        return "/admin/types";
    }
    @PostMapping("/addType")
    public String addType(Type type, RedirectAttributes redirectAttributes){
        Type type1=typeService.findTypeByName(type.getName());

        if(type1!=null)
        {
            redirectAttributes.addFlashAttribute("message","操作失败！不能添加重复的分类！");
            return "redirect:/admin/types/add-type";
        }
        else {
            int i=typeService.insertType(type);
            if(i==0)
            {
                redirectAttributes.addFlashAttribute("message","添加失败！");
            }
            else {
                redirectAttributes.addFlashAttribute("message","恭喜您！添加成功");
            }
            return "redirect:/admin/types";
        }
    }

    @PostMapping("/editType/{id}")
    public String editType(@PathVariable Long id,Type type, RedirectAttributes redirectAttributes){
        Type type1=typeService.findTypeByName(type.getName());

        if(type1!=null)
        {
            redirectAttributes.addFlashAttribute("message","操作失败！不能添加重复的分类！");
            redirectAttributes.addFlashAttribute("type",type);
            return "redirect:/admin/types/edit-type";
        }
        else {
            type.setId(id);
            int i=typeService.updateType(type);
            if(i==0)
            {
                redirectAttributes.addFlashAttribute("message","更新失败！");
            }
            else {
                redirectAttributes.addFlashAttribute("message","恭喜您！更新成功");
            }
            return "redirect:/admin/types";
        }
    }
    @GetMapping("/types/{id}/edit-type")
    public String editType(@PathVariable Long id,Model model){
        model.addAttribute("type",typeService.findTypeById(id));

        return "/admin/add-type";
    }
    @GetMapping("/types/{id}/delete")
    public String delete(@PathVariable Long id,RedirectAttributes redirectAttributes){
        int i=typeService.deleteType(id);
        if(i==0)
        {
            redirectAttributes.addFlashAttribute("message","删除失败！");
        }
        else {
            redirectAttributes.addFlashAttribute("message","恭喜您！删除成功");
        }
        return "redirect:/admin/types";
    }
}
