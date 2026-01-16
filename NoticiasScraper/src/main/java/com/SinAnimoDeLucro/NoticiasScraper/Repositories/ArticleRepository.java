package com.SinAnimoDeLucro.NoticiasScraper.Repositories;

import com.SinAnimoDeLucro.NoticiasScraper.Entities.Article;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleRepository extends JpaRepository<Article, Integer> {
}
