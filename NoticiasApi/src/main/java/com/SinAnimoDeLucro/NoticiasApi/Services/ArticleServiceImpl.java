package com.SinAnimoDeLucro.NoticiasApi.Services;

import com.SinAnimoDeLucro.NoticiasApi.Dto.ArticleDTO;
import com.SinAnimoDeLucro.NoticiasApi.Dto.GetArticlesRes;
import com.SinAnimoDeLucro.NoticiasApi.Entities.Article;
import com.SinAnimoDeLucro.NoticiasApi.Repositories.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Service
public class ArticleServiceImpl implements IArticleService {
  @Autowired
  private ArticleRepository articleRepository;

  @Transactional(readOnly = true)
  @Override
  public GetArticlesRes getArticlesByDate(LocalDate date) {
    return new GetArticlesRes(
      articleRepository.findArticleByPublishedAt(date)
        .stream()
        .map(this::mapToDto)
        .toList());
  }

  @Transactional(readOnly = true)
  @Override
  public Page<ArticleDTO> getArticlesByFilters(Integer newspaperId, LocalDate startDate, LocalDate endDate, int page, int size) {
    Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "publishedAt"));
    return articleRepository.findByNewspaperIdAndDate(newspaperId, startDate, endDate, pageable);
  }

  @Override
  public ArticleDTO mapToDto(Article a) {
    return new ArticleDTO(a.getHeadline(), a.getUrl(), a.getCategory(), a.getPublishedAt(), a.getNewspaper().getName());
  }
}
