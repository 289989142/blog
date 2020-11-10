package com.lhy.controller.admin;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lhy.pojo.Blog;
import com.lhy.pojo.Type;
import com.lhy.pojo.User;
import com.lhy.service.BlogService;
import com.lhy.service.TagService;
import com.lhy.service.TypeService;
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
    TagService tagService;
    @Autowired
    BlogService blogService;
    @Autowired
    TypeService typeService;

/*    @GetMapping("/blogs")
    public String list(){
     //   blogService.getAllBlog(null);
        Blog blog = new Blog();
        blog.setId((long) 1);
        blog.setTitle("测试");
        blog.setRecommend(true);
        blog.setTypeId((long)1);
        blogService.getAllBlog(blog);
        return "admin/blogs";
    }*/

    //博客列表
    @GetMapping("/blogs")
    public String blogs(Model model, @RequestParam(defaultValue = "1",value = "pageNum") Integer pageNum){
        //按照排序字段 倒序 排序
        String orderBy = "update_time desc";
        PageHelper.startPage(pageNum,10,orderBy);
        List<Blog> list = blogService.getAllBlog(null);
        PageInfo<Blog> pageInfo = new PageInfo<Blog>(list);
    //    model.addAttribute("types",typeService.getAllType());
        model.addAttribute("pageInfo",pageInfo);
        model.addAttribute("types",typeService.getAllType());
        return "admin/blogs";
    }


    //    搜索博客
    @PostMapping("/blogs/search")
    public String search(Blog blog, Model model,
                         @RequestParam(defaultValue = "1",value = "pageNum") Integer pageNum) {
        List<Blog> blogBySearch = blogService.getAllBlog(blog);
        PageHelper.startPage(pageNum, 10);
        PageInfo<Blog> pageInfo = new PageInfo<>(blogBySearch);
        model.addAttribute("pageInfo", pageInfo);
        return "admin/blogs :: blogList";
    }

    //跳转博客新增页面
    @GetMapping("/blogs/input")
    public String input(Model model) {
        model.addAttribute("types",typeService.getAllType());
        model.addAttribute("tags",tagService.getAllTag());
        model.addAttribute("blog", new Blog());
        return "admin/blogs-input";
    }


    //    博客新增
    @PostMapping("/blogs")
    public String post(Blog blog, RedirectAttributes attributes, HttpSession session){
        blog.setUser((User) session.getAttribute("user"));
        //设置blog的type
        blog.setType(typeService.getType(blog.getType().getId()));
        //设置blog中typeId属性
        blog.setTypeId(blog.getType().getId());
        //设置blog的tag
        blog.setTags(tagService.listTag(blog.getTagIds()));

        //设置用户id
        blog.setUserId(blog.getUser().getId());
        int b = blogService.saveBlog(blog);

        if(b == 0){
            attributes.addFlashAttribute("message", "新增失败");
        }else {
            attributes.addFlashAttribute("message", "新增成功");
        }
        return "redirect:/admin/blogs";
    }

    //    跳转编辑修改文章
    @GetMapping("/blogs/{id}/input")
    public String editInput(@PathVariable Long id, Model model) {

        model.addAttribute("types",typeService.getAllType());
        model.addAttribute("tags",tagService.getAllTag());

        Blog blog = blogService.getBlog(id);


        model.addAttribute("blog", blog);
        return "admin/blogs-input";
    }

    //    删除文章
    @GetMapping("/blogs/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes attributes) {
        blogService.deleteBlog(id);
        attributes.addFlashAttribute("message", "删除成功");
        return "redirect:/admin/blogs";
    }

}
