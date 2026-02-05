package com.SinAnimoDeLucro.NoticiasApi.Services;

import com.SinAnimoDeLucro.NoticiasApi.Dto.ArticleDTO;
import com.SinAnimoDeLucro.NoticiasApi.Dto.GetArticlesRes;
import com.SinAnimoDeLucro.NoticiasApi.Entities.Article;
import org.springframework.data.domain.Page;

import java.time.LocalDate;

public interface IArticleService {
    GetArticlesRes getArticlesByDate(LocalDate date);
    ArticleDTO mapToDto(Article a);
    Page<ArticleDTO> getArticlesByFilters(Integer newspaperId, LocalDate startDate, LocalDate endDate, int page, int size);
}
