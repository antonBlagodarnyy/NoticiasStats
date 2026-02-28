package com.SinAnimoDeLucro.NoticiasApi.Dto;

public record ArticleStatsDTO(
        ArticleCountDTO today,
        ArticleCountDTO week,
        ArticleCountDTO month) {
}
