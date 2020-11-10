package com.lhy.mapper;

import com.lhy.pojo.Blog;
import com.lhy.pojo.Type;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Mapper
@Repository
public interface BlogMapper {

    Blog getBlog(Long id);

    int updateViews(Long id);

    List<Blog> getAllBlog();

    List<Blog> getAllBlogBySearch(Blog blog);

    List<Blog> getNumRecommendBlog(int num);

    List<Blog> getSearchBlog(String query);

    List<Blog> getBlogByTagId(Long tagId);

    int saveBlog(Blog blog);

    int updateBlog(Blog blog);

    void deleteBlog(Long id);

    int saveBlogWithTag(List blogTag,Long blogId);

    int getBlogNum();

    int getCommentCountById(Long id);
}
