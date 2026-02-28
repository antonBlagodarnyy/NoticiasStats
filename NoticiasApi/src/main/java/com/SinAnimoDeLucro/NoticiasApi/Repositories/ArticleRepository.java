package com.SinAnimoDeLucro.NoticiasApi.Repositories;


import com.SinAnimoDeLucro.NoticiasApi.Entities.Article;
import com.SinAnimoDeLucro.NoticiasApi.Entities.Newspaper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;


public interface ArticleRepository extends JpaRepository<Article, Integer> {

    Page<Article> findByPublishedAtBetweenAndNewspaper_Id(
            LocalDate start,
            LocalDate end,
            Integer newspaperId,
            Pageable pageable
    );

    long countByPublishedAtBetween(LocalDate start, LocalDate end);

    @Query("""
    SELECT a.newspaper.name
    FROM Article a
    WHERE a.published_at BETWEEN :start AND :end
    GROUP BY a.newspaper
    ORDER BY COUNT(a.newspaper) DESC
""")
    List<Newspaper> findMostFrequentNewspaperBetweenDates(
            LocalDate start,
            LocalDate end,
            Pageable pageable
    );

}
