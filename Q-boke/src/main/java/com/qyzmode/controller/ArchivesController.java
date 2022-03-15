package com.qyzmode.controller;

import com.qyzmode.service.ArchivesService;
import com.qyzmode.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ArchivesController {

    @Autowired
    private ArchivesService archivesService;
    @Autowired
    private BlogService blogService;

    @GetMapping("/archives")
    public String archives(Model model) {

        model.addAttribute("archives",archivesService.archives());
        model.addAttribute("blog_count",blogService.selectBlogCount());

        return "archives";
    }
}
