package com.SinAnimoDeLucro.NoticiasScraper.Services.Implementacion;

import com.SinAnimoDeLucro.NoticiasScraper.Entities.Article;
import com.SinAnimoDeLucro.NoticiasScraper.Repositories.ArticleRepository;
import com.SinAnimoDeLucro.NoticiasScraper.Services.Interfaces.IArticleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
@Slf4j
public class ArticleServiceImpl implements IArticleService {
    @Autowired
    private ArticleRepository articleRepository;

    @Transactional
    @Override
    public void saveAll(List<Article> articles) {
        articleRepository.saveAll(articles);
    }

    @Transactional(readOnly = true)
    @Override
    public boolean existsByUrl(String url) {
        return articleRepository.existsByUrl(url);
    }

    @Transactional(readOnly = true)
    @Override
    public void deleteArticlesOlderThan(LocalDate date) {
        int deleted = articleRepository.deleteArticlesOlderThan(date);
        log.info(
                "-----> [CleanOldArticlesStep] -> {} noticias eliminadas <-----",
                deleted
        );
    }
}
