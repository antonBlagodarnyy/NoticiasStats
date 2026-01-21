package com.SinAnimoDeLucro.NoticiasScraper.Services;

import com.SinAnimoDeLucro.NoticiasScraper.Entities.Article;
import com.SinAnimoDeLucro.NoticiasScraper.Repositories.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ArticleServiceImpl implements IArticleService{
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
}
