package com.SinAnimoDeLucro.NoticiasScraper.Services.Interfaces;

import com.SinAnimoDeLucro.NoticiasScraper.Entities.Article;

import java.util.List;

public interface IArticleService {
  void saveAll(List<Article> articles);

  boolean existsByUrl(String url);
}
