package com.SinAnimoDeLucro.NoticiasScraper.Repositories;

import com.SinAnimoDeLucro.NoticiasScraper.Entities.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface ArticleRepository extends JpaRepository<Article, Integer> {
  boolean existsByUrl(String url);

  @Modifying
  @Query("""
    DELETE FROM Article a
    WHERE a.publishedAt < :date
""")
  int deleteArticlesOlderThan(@Param("date") LocalDate date);
}
