package com.SinAnimoDeLucro.NoticiasScrapper.Services;

import com.SinAnimoDeLucro.NoticiasScrapper.Entities.Article;
import com.SinAnimoDeLucro.NoticiasScrapper.Repositories.ArticleRepository;
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
}
