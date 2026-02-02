package com.SinAnimoDeLucro.NoticiasApi.Services;

import com.SinAnimoDeLucro.NoticiasApi.Entities.Article;
import org.springframework.data.domain.Page;

import java.time.LocalDate;

public interface IArticleService {
    Page<Article> getArticlesInRange(
            LocalDate start,
            LocalDate end,
            int page,
            int size,
            String newspaperName
    );

}
