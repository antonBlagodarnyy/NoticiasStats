package com.SinAnimoDeLucro.NoticiasApi.Services;

import com.SinAnimoDeLucro.NoticiasApi.Dto.ArticleDTO;
import com.SinAnimoDeLucro.NoticiasApi.Dto.GetArticlesByDateRes;
import com.SinAnimoDeLucro.NoticiasApi.Entities.Article;

import java.time.LocalDate;

public interface IArticleService {
    GetArticlesByDateRes getArticlesByDate(LocalDate date);
    ArticleDTO mapToDto(Article a);
}
