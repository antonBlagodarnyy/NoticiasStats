package com.SinAnimoDeLucro.NoticiasApi.Repositories;

import com.SinAnimoDeLucro.NoticiasApi.Entities.Article;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;


public interface ArticleRepository extends JpaRepository<Article, Integer> {
    Page<Article> findByPublishedAtBetweenAndNewspaper_Name(
            LocalDate start,
            LocalDate end,
            String newspaperName,
            Pageable pageable
    );
}
