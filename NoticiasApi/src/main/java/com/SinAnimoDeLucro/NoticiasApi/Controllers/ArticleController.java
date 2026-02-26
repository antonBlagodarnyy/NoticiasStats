package com.SinAnimoDeLucro.NoticiasApi.Controllers;

import com.SinAnimoDeLucro.NoticiasApi.Dto.ArticleDTO;

import com.SinAnimoDeLucro.NoticiasApi.Services.ArticleServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import com.SinAnimoDeLucro.NoticiasApi.Enums.Period;
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

    @GetMapping("/filter")
    public Page<ArticleDTO> getArticlesByDateRange(
            @RequestParam String start,
            @RequestParam String end,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam String newspaperName
    ) {
        return articleService.getArticlesInRange(
                LocalDate.parse(start),
                LocalDate.parse(end),
                page,
                size,
                newspaperName
        );
    }

    @GetMapping("/count-news")
    public ResponseEntity<Long> getCountedArticles(@RequestParam Period period) {
        LocalDate today = LocalDate.now();
        LocalDate startDate;
        switch (period) {
            case TODAY:
                //return ResponseEntity.ok(articleService.countArticlesByDate(today));
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
