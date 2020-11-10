package com.lhy.mapper;

import com.lhy.pojo.Tag;
import com.lhy.pojo.TagQuery;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface TagMapper {

    int saveTag(Tag tag);

    Tag getTag(Long id);

    List<Tag> getAllTag();

    List<Tag> getAllTagAndBlog();

    List<TagQuery> getNumTag(int num);
    List<TagQuery> getNumTag1();

    Tag getTagByName(String name);

    int updateTag(Tag tag);

    void deleteTag(Long id);
}
