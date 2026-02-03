package com.SinAnimoDeLucro.NoticiasApi.Controllers;

import com.SinAnimoDeLucro.NoticiasApi.Dto.GetArticlesByDateRes;
import com.SinAnimoDeLucro.NoticiasApi.Services.ArticleServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@Controller
@RequestMapping(path = "/api/article")
@RestController
public class ArticleController {

    @Autowired
    private ArticleServiceImpl articleService;

    @GetMapping("/by-date")
    public ResponseEntity<GetArticlesByDateRes> getArticlesByDate(@RequestParam("date")
                                                                  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
                                                                  LocalDate date) {
        GetArticlesByDateRes res = articleService.getArticlesByDate(date);

        if (res.articles().isEmpty()) {
            return ResponseEntity.notFound().build(); // 404
        }

        return ResponseEntity.ok(res);
    }
}
