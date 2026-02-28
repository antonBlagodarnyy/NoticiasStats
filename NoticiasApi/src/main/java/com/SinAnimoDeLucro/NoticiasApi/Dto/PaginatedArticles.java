package com.SinAnimoDeLucro.NoticiasApi.Dto;

import java.util.List;

public record PaginatedArticles(
        List<ArticleDTO> articles,
        int page,
        int size,
        long totalElements,
        int totalPages) {
}
