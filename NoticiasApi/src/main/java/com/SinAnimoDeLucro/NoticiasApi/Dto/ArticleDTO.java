package com.SinAnimoDeLucro.NoticiasApi.Dto;

import java.time.LocalDate;

public record ArticleDTO(
        String headline,
        NewspaperDTO newspaper,
        String url,
        String category,
        LocalDate publishedAt ) {
}
