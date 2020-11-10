package com.lhy.controller;

import com.lhy.pojo.Blog;
import com.lhy.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class ArchiveShowController {

    @Autowired
    private BlogService blogService;

    @GetMapping("/archives")
    public String archive(Model model){
        List<Blog> blogs = blogService.getAllBlog(null);
        model.addAttribute("blogs", blogs);
        return "archives";
    }

}
