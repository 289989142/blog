package com.lhy.service;

import com.lhy.mapper.TagMapper;
import com.lhy.pojo.Tag;
import com.lhy.pojo.TagQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class TagServiceImpl implements TagService {
    @Autowired
    private TagMapper TagMapper;



    @Transactional
    @Override
    public int saveTag(Tag tag) {
        return TagMapper.saveTag(tag);
    }

    @Transactional
    @Override
    public Tag getTag(Long id) {
        return TagMapper.getTag(id);
    }

    @Transactional
    @Override
    public List<Tag> getAllTag() {
        return TagMapper.getAllTag();
    }

    @Override
    public List<Tag> listTag(String ids) {
        List<Tag> tagList = new ArrayList<>();

        List<Long> idList = convertToList(ids);

        for (Long aLong : idList) {
            tagList.add(TagMapper.getTag(aLong));
        }
        return tagList;
    }
    private  List<Long> convertToList(String ids){
        ArrayList<Long> list = new ArrayList<>();
        if("".equals(ids) && ids!=null){
            String [] idArray = ids.split(",");
            for (int i = 0; i < idArray.length; i++) {
                list.add(new Long(idArray[i]));
            }
        }
        return list;
    }

    @Override
    public List<Tag> getAllTagAndBlog() {
        return TagMapper.getAllTagAndBlog();
    }

    @Override
    public Tag getTagByName(String name) {
        return TagMapper.getTagByName(name);
    }

    @Transactional
    @Override
    public int updateTag(Tag tag) {
        return TagMapper.updateTag(tag);
    }

    @Transactional
    @Override
    public void deleteTag(Long id) {
        TagMapper.deleteTag(id);
    }

    @Override
    public List<TagQuery> getNumTag(int num) {
        return TagMapper.getNumTag(num);
    }

    @Override
    public List<TagQuery> getNumTag1() {
        return TagMapper.getNumTag1();
    }

}
