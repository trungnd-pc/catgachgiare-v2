package com.trunnd.controller;

import com.trunnd.dto.ArticleDTO;
import com.trunnd.model.Article;
import com.trunnd.service.ArticleService;
import com.trunnd.utils.FriendlyUrlUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class ArticleController {

    private ArticleService service;

    @RequestMapping(value = "/dich-vu/{link}.html")
    public String getServiceById(String link, Model model) {
//        Integer id = FriendlyUrlUtil.getIdFromLink(link);
//        model.addAttribute("service", service.findById(id));
        return "client/service-detail";
    }

    private ArticleDTO convertToServiceDTO(Article article) {

        ArticleDTO dto = new ArticleDTO();
        dto.setId(article.getId());
        dto.setName(article.getName());
        dto.setContent(article.getContent());
        return dto;
    }

    private Article covertToService(ArticleDTO dto) {
        Article article = new Article();
        article.setId(dto.getId());
        article.setName(dto.getName());
        article.setContent(dto.getContent());
        return article;
    }

    private List<ArticleDTO> convertToListServiceDTO(List<Article> articles) {
        return articles.stream().map(service -> convertToServiceDTO(service)).collect(Collectors.toList());
    }
}
