package com.SinAnimoDeLucro.NoticiasApi.Repositories;

import com.SinAnimoDeLucro.NoticiasApi.Dto.ArticleDTO;
import com.SinAnimoDeLucro.NoticiasApi.Entities.Article;
import org.springframework.cglib.core.Local;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface ArticleRepository extends JpaRepository<Article, Integer> {
  Page<ArticleDTO> findArticleByPublishedAt(LocalDate publishedAt, Pageable pageable);

  @Query("""
        SELECT new com.SinAnimoDeLucro.NoticiasApi.Dto.ArticleDTO(
            a.headline,
            a.url,
            a.category,
            a.publishedAt,
            a.newspaper.name
        )
        FROM Article a
        WHERE (:newspaperId IS NULL OR a.newspaper.id = :newspaperId)
        AND a.publishedAt BETWEEN :start AND :end
        ORDER BY a.publishedAt DESC
    """)
  Page<ArticleDTO> findByNewspaperIdAndDate(
    @Param("newspaperId") Integer newspaperId,
    @Param("start") LocalDate start,
    @Param("end") LocalDate end,
    Pageable pageable
  );

  long countByPublishedAt(LocalDate publishedAt);

  long countByPublishedAtBetween(LocalDate start, LocalDate end);

  @Query("""
        SELECT n.name
        FROM Article a
        JOIN a.newspaper n
        WHERE a.publishedAt = :date
        GROUP BY n.name
        ORDER BY COUNT(a) DESC
        LIMIT 1
    """)
  String findTopNewspaperByDate(@Param("date") LocalDate date);

  @Query("""
        SELECT n.name
        FROM Article a
        JOIN a.newspaper n
        WHERE a.publishedAt BETWEEN :start AND :end
        GROUP BY n.name
        ORDER BY COUNT(a) DESC
        LIMIT 1
    """)
  String findTopNewspaperByDateRange(
    @Param("start") LocalDate start,
    @Param("end") LocalDate end
  );
}
