package com.SinAnimoDeLucro.NoticiasApi.Services;

import com.SinAnimoDeLucro.NoticiasApi.Dto.ArticleDTO;
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


    public Page<ArticleDTO> getArticlesInRange(
            LocalDate start,
            LocalDate end,
            int page,
            int size,
            String newspaperName
    ) {
        Pageable pageable = PageRequest.of(
                page,
                size,
                Sort.by("publishedAt").descending());
        return articleRepository.findByPublishedAtBetweenAndNewspaper_Name(
                start,
                end,
                newspaperName,
                pageable)
                .map(this::mapToDTO);
    }

    private ArticleDTO mapToDTO(Article a) {
        return new ArticleDTO(
                a.getHeadline(),
                a.getNewspaper().getName(),
                a.getUrl(),
                a.getCategory(),
                a.getPublishedAt()
        );
    }

  @Transactional(readOnly = true)
  @Override
  public Page<ArticleDTO> getArticlesByDate(LocalDate date, int page, int size) {
    Pageable pageable = PageRequest.of(page, size);
    return articleRepository.findArticleByPublishedAt(date, pageable);
  }

  @Transactional(readOnly = true)
  @Override
  public Page<ArticleDTO> getArticlesByFilters(Integer newspaperId, LocalDate startDate, LocalDate endDate, int page, int size) {
    Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "publishedAt"));
    return articleRepository.findByNewspaperIdAndDate(newspaperId, startDate, endDate, pageable);
  }

  @Override
  public long countArticlesByDate(LocalDate date) {
    return articleRepository.countByPublishedAt(date);
  }

  @Override
  public long countArticlesByDateRange(LocalDate startDate, LocalDate endDate) {
    return articleRepository.countByPublishedAtBetween(startDate, endDate);
  }

}
