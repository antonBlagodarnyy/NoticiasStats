package com.SinAnimoDeLucro.NoticiasScrapper.Services;

import com.SinAnimoDeLucro.NoticiasScrapper.Entities.Article;

import java.util.List;

public interface IArticleService {
  void saveAll(List<Article> articles);
}
