package com.SinAnimoDeLucro.NoticiasScrapper.Repositories;

import com.SinAnimoDeLucro.NoticiasScrapper.Entities.Article;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleRepository extends JpaRepository<Article, Integer> {
}
