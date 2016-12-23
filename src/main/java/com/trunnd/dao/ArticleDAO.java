package com.trunnd.dao;


import com.trunnd.model.Article;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleDAO extends JpaRepository<Article, Integer> {

}
