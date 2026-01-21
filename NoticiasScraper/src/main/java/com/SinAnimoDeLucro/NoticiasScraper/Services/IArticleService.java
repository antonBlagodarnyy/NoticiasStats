package com.SinAnimoDeLucro.NoticiasScraper.Services;

import com.SinAnimoDeLucro.NoticiasScraper.Entities.Article;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface IArticleService {
  void saveAll(List<Article> articles);

  boolean existsByUrl(String url);
}
