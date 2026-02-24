package com.SinAnimoDeLucro.NoticiasApi.Services;

import com.SinAnimoDeLucro.NoticiasApi.Dto.ArticleDTO;
import com.SinAnimoDeLucro.NoticiasApi.Dto.GetArticlesRes;
import com.SinAnimoDeLucro.NoticiasApi.Entities.Article;
import com.SinAnimoDeLucro.NoticiasApi.Enums.Period;
import org.springframework.data.domain.Page;

import java.time.LocalDate;
import java.util.List;

public interface IArticleService {
    Page<ArticleDTO> getArticlesByDate(LocalDate date, int page, int size);
    Page<ArticleDTO> getArticlesByFilters(Integer newspaperId, LocalDate startDate, LocalDate endDate, int page, int size);
    long countArticlesByPeriod(Period period);
    String getTopNewspaperByPeriod(Period period);
}
