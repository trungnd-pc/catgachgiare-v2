package com.trunnd.dto;


import com.trunnd.utils.FriendlyUrlUtil;

import java.io.Serializable;

public class ArticleDTO implements Serializable{

    private Integer id;
    private String name;
    private String content;
    private String link;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getLink() {
        if (id != null && name != null ) {
            return FriendlyUrlUtil.getLink(name, id);
        }
        return null;
    }

}
