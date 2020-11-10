package com.lhy.controller.admin;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lhy.pojo.Tag;
import com.lhy.service.TagService;
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

    //    分页查询标签列表
    @GetMapping("/tags")
    public String list(Model model, @RequestParam(defaultValue = "1",value = "pageNum") Integer pageNum){
        //按照排序字段 倒序 排序
        String orderBy = "id desc";
        PageHelper.startPage(pageNum,10,orderBy);
        List<Tag> list = tagService.getAllTag();
        PageInfo<Tag> pageInfo = new PageInfo<Tag>(list);
        model.addAttribute("pageInfo",pageInfo);
        return "admin/tags";
    }

    //    返回新增标签页面
    @GetMapping("/tags/input")
    public String input(Model model){
        model.addAttribute("tag", new Tag());
        return "admin/tags-input";
    }

  //  新增标签
    @PostMapping("/tags")
    public String post( Tag Tag, RedirectAttributes attributes) {
        Tag Tag1 = tagService.getTagByName(Tag.getName());
        if (Tag1 != null) {
            attributes.addFlashAttribute("message", "不能添加重复的标签");
            return "redirect:/admin/tags/input";
        }
        int t = tagService.saveTag(Tag);
        if (t == 0) {
            attributes.addFlashAttribute("message", "新增失败");
        } else {
            attributes.addFlashAttribute("message", "新增成功");
        }
        return "redirect:/admin/tags";
    }

    //    跳转修改标签页面
    @GetMapping("/tags/{id}/input")
    public String editInput(@PathVariable Long id, Model model) {
        model.addAttribute("tag", tagService.getTag(id));
        return "admin/Tags-input";
    }

  //    编辑修改标签
    @PostMapping("/tags/{id}")
    public String editPost( Tag Tag, RedirectAttributes attributes) {
        Tag Tag1 = tagService.getTagByName(Tag.getName());
        if (Tag1 != null) {
            attributes.addFlashAttribute("message", "不能添加重复的标签");
            return "redirect:/admin/tags/input";
        }
        int t = tagService.updateTag(Tag);
        if (t == 0 ) {
            attributes.addFlashAttribute("message", "编辑失败");
        } else {
            attributes.addFlashAttribute("message", "编辑成功");
        }
        return "redirect:/admin/tags";
    }

    //    删除标签
    @GetMapping("/tags/{id}/delete")
    public String delete(@PathVariable Long id,RedirectAttributes attributes) {
        tagService.deleteTag(id);
        attributes.addFlashAttribute("message", "删除成功");
        return "redirect:/admin/tags";
    }
}
