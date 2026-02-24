package com.SinAnimoDeLucro.NoticiasApi.Services;

import com.SinAnimoDeLucro.NoticiasApi.Dto.ArticleDTO;
import com.SinAnimoDeLucro.NoticiasApi.Enums.Period;
import com.SinAnimoDeLucro.NoticiasApi.Repositories.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
public class ArticleServiceImpl implements IArticleService {
  @Autowired
  private ArticleRepository articleRepository;

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

  @Transactional(readOnly = true)
  @Override
  public long countArticlesByPeriod(Period period) {
    LocalDate today = LocalDate.now();
    LocalDate startDate = period.getStart(today);

    if (period == Period.TODAY) {
      return articleRepository.countByPublishedAt(today);
    }

    return articleRepository.countByPublishedAtBetween(startDate, today);
  }

  @Transactional(readOnly = true)
  @Override
  public String getTopNewspaperByPeriod(Period period) {
    LocalDate today = LocalDate.now();
    LocalDate startDate = period.getStart(today);

    if (period == Period.TODAY) {
      return articleRepository.findTopNewspaperByDate(today);
    }

    return articleRepository.findTopNewspaperByDateRange(startDate, today);
  }
}
