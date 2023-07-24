package com.example.firstproject.service;

import com.example.firstproject.dto.ArticleForm;
import com.example.firstproject.entity.Article;
import com.example.firstproject.repository.ArticleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service // create Service object in Spring boot. it is used in ArticleApiController by DI
@Slf4j
public class ArticleService {
    @Autowired
    private ArticleRepository articleRepository;

    public List<Article> index() {
        return articleRepository.findAll();
    }

    public Article show(Long id) {
        return articleRepository.findById(id).orElse(null);
    }

    public Article create(ArticleForm dto) {
        Article article = dto.toEntity();
        if (article.getId() != null)
            return null;
        return articleRepository.save(article);
    }

    public Article update(Long id, ArticleForm dto) {
        // 1. create updating Entity
        Article article = dto.toEntity();
        log.info("before, id: {}, article: {}", id, article.toString());

        // 2. select target Entity
        Article target = articleRepository.findById(id).orElse(null);

        // 3. deal Bad Request(there is no target or different id)
        if (target == null || id != article.getId()) {
            // 400. Response "BAD Request"
            log.info("-!-BAD Request-!- id: {}, article: {}", id, article.toString());
            return null;
        }

        // 4. update and response
        target.patch(article);
        Article updated = articleRepository.save(target);
        return updated;
    }
}
