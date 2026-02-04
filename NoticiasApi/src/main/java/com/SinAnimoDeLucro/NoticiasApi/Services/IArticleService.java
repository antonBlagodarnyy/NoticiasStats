package com.SinAnimoDeLucro.NoticiasApi.Services;

import com.SinAnimoDeLucro.NoticiasApi.Dto.ArticleDTO;
import com.SinAnimoDeLucro.NoticiasApi.Dto.GetArticlesRes;
import com.SinAnimoDeLucro.NoticiasApi.Entities.Article;

import java.time.LocalDate;

public interface IArticleService {
    GetArticlesRes getArticlesByDate(LocalDate date);
    ArticleDTO mapToDto(Article a);
    GetArticlesRes getArticlesByNewspaperIdAndDate(Integer newspaperId, LocalDate startDate, LocalDate endDate);

}
