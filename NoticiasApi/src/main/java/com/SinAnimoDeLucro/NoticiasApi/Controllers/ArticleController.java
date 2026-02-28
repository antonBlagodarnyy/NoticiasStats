package com.SinAnimoDeLucro.NoticiasApi.Controllers;

import com.SinAnimoDeLucro.NoticiasApi.Dto.ArticleStatsDTO;
import com.SinAnimoDeLucro.NoticiasApi.Dto.PaginatedArticles;
import com.SinAnimoDeLucro.NoticiasApi.Services.ArticleServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
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
    public PaginatedArticles getArticlesByDateRange(
            @RequestParam String start,
            @RequestParam String end,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam Integer newspaperId
    ) {
        return articleService.getArticlesInRange(
                LocalDate.parse(start),
                LocalDate.parse(end),
                page,
                size,
                newspaperId
        );
    }

    @GetMapping("/count")
    public ResponseEntity<ArticleStatsDTO> getCountArticles() {
        return ResponseEntity.ok(articleService.countArticles());
    }


}
