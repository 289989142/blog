package com.lhy.service;
import com.lhy.pojo.Blog;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public interface BlogService {


    Blog getBlog(Long id);

    Blog getAndConvertBlog(Long id);

    List<Blog> getAllBlog(Blog blog);

    List<Blog> getSearchBlog(String query);

    List<Blog> getBlogByTagId(Long tagId);

    int saveBlog(Blog blog);

    void deleteBlog(Long id);

    List<Blog> getNumRecommendBlog(int num);

    int getBlogNum();
}
