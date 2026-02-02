package com.SinAnimoDeLucro.NoticiasApi.Controllers;

import com.SinAnimoDeLucro.NoticiasApi.Entities.Article;
import com.SinAnimoDeLucro.NoticiasApi.Services.ArticleServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@Controller
@RequestMapping(path = "/article")
@RestController
public class ArticleController {
    @Autowired
    private ArticleServiceImpl articleService;

    @GetMapping("/filter")
    public Page<Article> getArticlesByDateRange(
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
}
