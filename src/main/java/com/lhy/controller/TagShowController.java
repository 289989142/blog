package com.lhy.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lhy.pojo.Blog;
import com.lhy.pojo.TagQuery;
import com.lhy.service.BlogService;
import com.lhy.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class TagShowController {
    @Autowired
    TagService tagService;
    @Autowired
    BlogService blogService;
    //    分页查询分类
    @GetMapping("/tags/{id}")
    public String types(@RequestParam(defaultValue = "1",value = "pageNum") Integer pageNum, @PathVariable Long id, Model model) {

        List<TagQuery> tags = tagService.getNumTag1();
        System.out.println("tags:"+tags);
        //-1表示从首页导航点进来的
        if (id == -1) {
            id = tags.get(0).getId();
        }

        model.addAttribute("tags", tags);
        List<Blog> blogs = blogService.getBlogByTagId(id);

        PageHelper.startPage(pageNum, 5);
        PageInfo<Blog> pageInfo = new PageInfo<>(blogs);
        model.addAttribute("pageInfo", pageInfo);
        model.addAttribute("activeTagId", id);
        return "tags";
    }
}
