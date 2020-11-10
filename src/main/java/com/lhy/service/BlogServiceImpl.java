package com.lhy.service;

import com.lhy.NotFoundException1;
import com.lhy.mapper.BlogMapper;
import com.lhy.pojo.Blog;
import com.lhy.util.MarkDownUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class BlogServiceImpl implements BlogService {

    @Autowired
    BlogMapper blogMapper;

    @Override
    public Blog getBlog(Long id) {
        return blogMapper.getBlog(id);
    }

    @Override
    public Blog getAndConvertBlog(Long id) {
        Blog blog = blogMapper.getBlog(id);
        if(blog==null){
            throw new NotFoundException1("该博客不存在");
        }
        blogMapper.updateViews(id);
        Blog b = new Blog();
        BeanUtils.copyProperties(blog,b);
        String content = b.getContent();
        b.setContent(MarkDownUtils.markdownToHtmlExtensions(content));
        return b;
    }

    @Override
    public List<Blog> getAllBlog(Blog blog) {
        //如果查询条件为空 就执行查询所有
        if (blog==null){
            List<Blog> blogs =  blogMapper.getAllBlog();

            System.out.println("get1");
            for (Blog blog1 : blogs) {
                System.out.println(blog1);
            }

            return blogs;
        }else {
            List<Blog> blogs =  blogMapper.getAllBlogBySearch(blog);

            System.out.println("get2");
            for (Blog blog1 : blogs) {
                System.out.println(blog1);
            }

            return blogs;
        }


    }

    @Override
    public List<Blog> getSearchBlog(String query) {
        return blogMapper.getSearchBlog(query);
    }

    @Override
    public List<Blog> getBlogByTagId(Long tagId) {
        return blogMapper.getBlogByTagId(tagId);
    }

    @Override
    public int saveBlog(Blog blog) {
        if (blog.getId() == null){
            blog.setCreateTime(new Date());
            blog.setUpdateTime(new Date());
            blog.setViews(0);

            //将博客id和标签id放在了blogTag中
            int result = blogMapper.saveBlog(blog);
            Long blogId = blog.getId();
            String tags = blog.getTagIds();
            String[] tagArray = tags.split(",");
            List<String> tagList = Arrays.asList(tagArray);
            blogMapper.saveBlogWithTag(tagList,blogId);

            return result;
        }else{
            blog.setUpdateTime(new Date());

            return blogMapper.updateBlog(blog);

        }
    }

    public List<Blog> getNumRecommendBlog(int num){
        return blogMapper.getNumRecommendBlog(num);
    }

    public int getBlogNum(){
        return blogMapper.getBlogNum();
    }

    @Override
    public void deleteBlog(Long id) {
        blogMapper.deleteBlog(id);
    }
}
