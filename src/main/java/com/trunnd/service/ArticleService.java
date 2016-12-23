package com.trunnd.service;

import com.trunnd.dao.ArticleDAO;
import com.trunnd.model.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ArticleService {

    @Autowired
    private ArticleDAO dao;

    public Article findById(Integer id) {
        return dao.findOne(id);
    }
}
