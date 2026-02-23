package com.SinAnimoDeLucro.NoticiasApi.Dto;

import java.time.LocalDate;

public record ArticleDTO(
        String headline,
        String newspaperName,
        String url,
        String category,
        LocalDate publishedAt ) {
}
