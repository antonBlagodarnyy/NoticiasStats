package com.SinAnimoDeLucro.NoticiasApi.Services;

import com.SinAnimoDeLucro.NoticiasApi.Dto.ArticleDTO;
<<<<<<< HEAD
=======
import com.SinAnimoDeLucro.NoticiasApi.Dto.GetArticlesRes;
import com.SinAnimoDeLucro.NoticiasApi.Entities.Article;
>>>>>>> 5f696306d0ff6be512fa96581b03b16e0260feda
import org.springframework.data.domain.Page;

import java.time.LocalDate;

public interface IArticleService {
<<<<<<< HEAD
    Page<ArticleDTO> getArticlesInRange(
            LocalDate start,
            LocalDate end,
            int page,
            int size,
            String newspaperName
    );

=======
    Page<ArticleDTO> getArticlesByDate(LocalDate date, int page, int size);
    Page<ArticleDTO> getArticlesByFilters(Integer newspaperId, LocalDate startDate, LocalDate endDate, int page, int size);
    long countArticlesByDate(LocalDate date);
    long countArticlesByDateRange(LocalDate startDate, LocalDate endDate);
>>>>>>> 5f696306d0ff6be512fa96581b03b16e0260feda
}
