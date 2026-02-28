package com.SinAnimoDeLucro.NoticiasApi.Services;

import com.SinAnimoDeLucro.NoticiasApi.Dto.ArticleStatsDTO;
import com.SinAnimoDeLucro.NoticiasApi.Dto.PaginatedArticles;
import java.time.LocalDate;

public interface IArticleService {

    PaginatedArticles getArticlesInRange(
            LocalDate start,
            LocalDate end,
            int page,
            int size,
            Integer newspaperId
    );

    ArticleStatsDTO countArticles();
}
