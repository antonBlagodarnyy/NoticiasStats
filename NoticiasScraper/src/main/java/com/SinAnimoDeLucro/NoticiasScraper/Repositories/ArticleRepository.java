package com.SinAnimoDeLucro.NoticiasScraper.Repositories;

import com.SinAnimoDeLucro.NoticiasScraper.Entities.Article;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ArticleRepository extends JpaRepository<Article, Integer> {
  boolean existsByUrl(String url);
}
