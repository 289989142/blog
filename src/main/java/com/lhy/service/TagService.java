package com.lhy.service;

import com.lhy.pojo.Tag;
import com.lhy.pojo.TagQuery;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TagService {


    int saveTag(Tag tag);

    Tag getTag(Long id);

    List<Tag>getAllTag();

    List<Tag> listTag(String ids);

    List<Tag>getAllTagAndBlog();

    Tag getTagByName(String name);

    int updateTag(Tag tag);

    void deleteTag(Long id);

    List<TagQuery> getNumTag (int num);

    List<TagQuery> getNumTag1 ();

}
