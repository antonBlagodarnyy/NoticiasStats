package com.SinAnimoDeLucro.NoticiasApi.Controllers;

import com.SinAnimoDeLucro.NoticiasApi.Dto.ArticleDTO;
import com.SinAnimoDeLucro.NoticiasApi.Dto.PaginatedArticlesRes;
import com.SinAnimoDeLucro.NoticiasApi.Enums.Period;
import com.SinAnimoDeLucro.NoticiasApi.Services.ArticleServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.data.domain.Page;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@Controller
@RequestMapping(path = "/api/articles")
@RestController
public class ArticleController {

  @Autowired
  private ArticleServiceImpl articleService;

  @GetMapping("/by-date")
  public ResponseEntity<PaginatedArticlesRes> getArticlesByDate(@RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date,
                                                                @RequestParam(defaultValue = "0") int page,
                                                                @RequestParam(defaultValue = "15") int size) {
    Page<ArticleDTO> res = articleService.getArticlesByDate(date, page, size);

    return ResponseEntity.ok(new PaginatedArticlesRes(
      res.getContent(),
      res.getNumber(),
      res.getSize(),
      res.getTotalElements(),
      res.getTotalPages()
    ));
  }

  @GetMapping("by-newspaperid-and-date")
  public ResponseEntity<PaginatedArticlesRes> getArticlesByCategoryAndDate(@RequestParam(required = false) Integer newspaperId,
                                                                           @RequestParam("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
                                                                           @RequestParam("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate,
                                                                           @RequestParam(defaultValue = "0") int page,
                                                                           @RequestParam(defaultValue = "15") int size
  ) {

    Page<ArticleDTO> res = articleService.getArticlesByFilters(newspaperId, startDate, endDate, page, size);

    return ResponseEntity.ok(new PaginatedArticlesRes(
      res.getContent(),
      res.getNumber(),
      res.getSize(),
      res.getTotalElements(),
      res.getTotalPages()
    ));
  }

  @GetMapping("/total-news")
  public ResponseEntity<Long> getTotalNewsLastWeek(@RequestParam Period period) {
    LocalDate today = LocalDate.now();
    LocalDate startDate;

    switch (period) {
      case TODAY:
        return ResponseEntity.ok(articleService.countArticlesByDate(today));
      case LAST_WEEK:
        startDate = today.minusDays(7);
        break;
      case LAST_MONTH:
        startDate = today.minusMonths(1);
        break;
      default:
        return ResponseEntity.notFound().build();
    }
    long totNews = articleService.countArticlesByDateRange(startDate, today);

    return ResponseEntity.ok(totNews);
  }
}
