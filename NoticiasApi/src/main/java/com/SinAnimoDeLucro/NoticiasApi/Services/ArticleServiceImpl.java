package com.SinAnimoDeLucro.NoticiasApi.Services;

import com.SinAnimoDeLucro.NoticiasApi.Dto.*;
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
import java.time.Period;
import java.util.Arrays;
import java.util.stream.Stream;

@Service
public class ArticleServiceImpl implements IArticleService {
    @Autowired
    private ArticleRepository articleRepository;


    public PaginatedArticles getArticlesInRange(
            LocalDate start,
            LocalDate end,
            int page,
            int size,
            Integer newspaperId
    ) {
        Pageable pageable = PageRequest.of(
                page,
                size,
                Sort.by("publishedAt").descending());
        Page<ArticleDTO> pageResult = articleRepository.findByPublishedAtBetweenAndNewspaper_Id(
                        start,
                        end,
                        newspaperId,
                        pageable)
                .map(this::mapToDTO);

        return new PaginatedArticles(
                pageResult.getContent(),
                pageResult.getNumber(),
                pageResult.getSize(),
                pageResult.getTotalElements(),
                pageResult.getTotalPages()
        );
    }

    private ArticleDTO mapToDTO(Article a) {
        return new ArticleDTO(
                a.getHeadline(),
                new NewspaperDTO(a.getNewspaper().getId(), a.getNewspaper().getName()),
                a.getUrl(),
                a.getCategory(),
                a.getPublishedAt()
        );
    }


    //TODO Genera y devuelve el ArticleStatsDTO, la idea seria generarlo en otro método y mutarlo
    @Transactional(readOnly = true)
    @Override
    public ArticleStatsDTO countArticles() {
        LocalDate today = LocalDate.now();

        ArticleCountDTO[] stats = Stream.of(
                        today,
                        today.minusWeeks(1),
                        today.minusMonths(1)
                )
                .map(start -> countPeriod(start, today))
                .toArray(ArticleCountDTO[]::new);

        return new ArticleStatsDTO(stats[0], stats[1], stats[2]);}

    private ArticleCountDTO countPeriod(LocalDate startDate, LocalDate endDate) {
        return new ArticleCountDTO(
                articleRepository.countByPublishedAtBetween(startDate, endDate),
                articleRepository.findMostFrequentNewspaperBetweenDates(startDate, endDate, PageRequest.of(0, 1))
                        .getFirst()
                        .getName()
        );
    }

}
