package com.SinAnimoDeLucro.NoticiasApi.Dto;

import java.util.List;

public record PaginatedArticlesRes (List<ArticleDTO> articles, int page, int size, long totElements, int totPages) {
}
