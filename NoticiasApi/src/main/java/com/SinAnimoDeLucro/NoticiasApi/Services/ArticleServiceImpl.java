package com.SinAnimoDeLucro.NoticiasApi.Services;

import com.SinAnimoDeLucro.NoticiasApi.Dto.ArticleDTO;
import com.SinAnimoDeLucro.NoticiasApi.Dto.GetArticlesByDateRes;
import com.SinAnimoDeLucro.NoticiasApi.Entities.Article;
import com.SinAnimoDeLucro.NoticiasApi.Repositories.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class ArticleServiceImpl implements IArticleService {
    @Autowired
    private ArticleRepository articleRepository;

    @Override
    public GetArticlesByDateRes getArticlesByDate(LocalDate date) {
        return new GetArticlesByDateRes(
                articleRepository.findArticleByPublishedAt(date)
                        .stream()
                        .map(this::mapToDto)
                        .toList());
    }

    @Override
    public ArticleDTO mapToDto(Article a) {
        return new ArticleDTO(a.getHeadline(), a.getUrl(), a.getPublishedAt());
    }
}
