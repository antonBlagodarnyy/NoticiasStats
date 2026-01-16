package com.SinAnimoDeLucro.NoticiasScraper.Services;

import com.SinAnimoDeLucro.NoticiasScraper.Entities.Article;

import java.util.List;

public interface IArticleService {
  void saveAll(List<Article> articles);
}
