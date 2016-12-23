package com.trunnd.dto;


import com.trunnd.utils.FriendlyUrlUtil;

import java.io.Serializable;
import java.util.List;

public class ProductDTO implements Serializable {

    private Integer id;
    private String name;
    private String categories;
    private String description;
    private float price;
    private List<String> images;
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

    public String getCategories() {
        return categories;
    }

    public void setCategories(String categories) {
        this.categories = categories;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }

    public String getLink() {
        if (id != null && name != null ) {
            return FriendlyUrlUtil.getLink(name, id);
        }
        return null;
    }

}
