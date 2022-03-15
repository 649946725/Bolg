package com.qyzmode.controller;


import com.qyzmode.prjo.Comment;
import com.qyzmode.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class CommentController {

    @Autowired
    private CommentService commentService;
    @GetMapping("/comments/{id}")
   public String commentBlog(@PathVariable Long id, Model model)
   {

       model.addAttribute("comments",commentService.findByBlogId(id));

       return "detail :: commentList";
   }

   @PostMapping("/comments")
    public String comments(Comment comment){
        commentService.insertComment(comment);
        return "redirect:comments/"+comment.getBlog_id();
   }

}
