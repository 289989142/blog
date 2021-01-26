package com.lhy.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lhy.NotFoundException1;
import com.lhy.mapper.UserMapper;
import com.lhy.pojo.Blog;
import com.lhy.pojo.User;
import com.lhy.service.BlogService;
import com.lhy.service.TagService;
import com.lhy.service.TypeService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class IndexController {
    @Autowired
    TagService tagService;
    @Autowired
    BlogService blogService;
    @Autowired
    TypeService typeService;

    @GetMapping("/")
    public String index(Model model, @RequestParam(defaultValue = "1",value = "pageNum") Integer pageNum){
        //按照排序字段 倒序 排序
        String orderBy = "update_time desc";
        PageHelper.startPage(pageNum,5,orderBy);
        List<Blog> list = blogService.getAllBlog(null);
        PageInfo<Blog> pageInfo = new PageInfo<Blog>(list);
        //    model.addAttribute("types",typeService.getAllType());
        model.addAttribute("pageInfo",pageInfo);
        model.addAttribute("types",typeService.getNumType(5));
        model.addAttribute("tags",tagService.getNumTag(10));
        model.addAttribute("recommendBlogs",blogService.getNumRecommendBlog(6));
        model.addAttribute("blogNum",blogService.getBlogNum());

        return "index";
    }

    //    搜索博客
    @PostMapping("/search")
    public String search(Model model,
                         @RequestParam(defaultValue = "1", value = "pageNum") Integer pageNum,
                         @RequestParam String query) {
        PageHelper.startPage(pageNum, 1000);
        List<Blog> searchBlog = blogService.getSearchBlog(query);
        PageInfo<Blog> pageInfo = new PageInfo<>(searchBlog);
        model.addAttribute("pageInfo", pageInfo);
        model.addAttribute("query", query);
        return "search";
    }

    @GetMapping("/blog/{id}")
    public String blog(@PathVariable Long id,Model model){

        model.addAttribute("blog",blogService.getAndConvertBlog(id));
        return "blog";
    }
    @GetMapping("/footer/newblog")
    public String newBlogs(Model model){
        model.addAttribute("newblogs",blogService.getNumRecommendBlog(3));

        return "_fragments :: newblogList";
    }
    @GetMapping("/random")
    public String random(){
        return "random";
    }

}
