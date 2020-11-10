package com.lhy.pojo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Blog {
    private Long id;
    private String title;
    private String content;
    private String firstPicture;
    private String flag;
    private Integer views;

    private Integer commentCount;


    private boolean shareStatement;
    private boolean comment;
    private boolean published;
    private boolean recommend;
    private Date createTime;
    private Date updateTime;

    private Long typeId;
    private String tagIds;
    private Long userId;
    private String description;
    private Type type;
    private User user;
    private List<Tag> tags = new ArrayList<Tag>();
    private List<Comment> comments = new ArrayList<Comment>();

    public void init(){
        this.tagIds = tagsToIds(this.getTags());
    }

    private String tagsToIds(List<Tag> tags){
        if (!tags.isEmpty()){
            StringBuilder ids = new StringBuilder();
            boolean flag = false;
            for (Tag tag : tags) {
                if (flag){
                    ids.append(",");
                }else{
                    flag = true;
                }
                ids.append(tag.getId());
            }
            return ids.toString();
        }else{
            return tagIds;
        }
    }

}
