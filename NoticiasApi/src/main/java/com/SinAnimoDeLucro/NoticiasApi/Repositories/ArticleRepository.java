package com.SinAnimoDeLucro.NoticiasApi.Repositories;

import com.SinAnimoDeLucro.NoticiasApi.Entities.Article;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface ArticleRepository extends JpaRepository<Article, Integer> {
    List<Article> findArticleByPublishedAt(LocalDate publishedAt);
}
