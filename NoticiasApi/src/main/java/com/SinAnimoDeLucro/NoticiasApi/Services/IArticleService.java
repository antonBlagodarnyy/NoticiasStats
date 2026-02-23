package com.SinAnimoDeLucro.NoticiasApi.Services;

import com.SinAnimoDeLucro.NoticiasApi.Dto.ArticleDTO;
import org.springframework.data.domain.Page;

import java.time.LocalDate;

public interface IArticleService {
    Page<ArticleDTO> getArticlesInRange(
            LocalDate start,
            LocalDate end,
            int page,
            int size,
            String newspaperName
    );

}
