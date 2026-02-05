package com.SinAnimoDeLucro.NoticiasApi.Controllers;

import com.SinAnimoDeLucro.NoticiasApi.Dto.ArticleDTO;
import com.SinAnimoDeLucro.NoticiasApi.Dto.GetArticlesRes;
import com.SinAnimoDeLucro.NoticiasApi.Dto.PaginatedArticlesRes;
import com.SinAnimoDeLucro.NoticiasApi.Services.ArticleServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
    public ResponseEntity<GetArticlesRes> getArticlesByDate(@RequestParam("date")
                                                                  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
                                                                  LocalDate date) {
        GetArticlesRes res = articleService.getArticlesByDate(date);

        if (res.articles().isEmpty()) {
            return ResponseEntity.notFound().build(); // 404
        }

        return ResponseEntity.ok(res);
    }

    @GetMapping("by-newspaperid-and-date")
    public ResponseEntity<PaginatedArticlesRes> getArticlesByCategoryAndDate(@RequestParam(required = false) Integer newspaperId,
                                                                             @RequestParam("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
                                                                             @RequestParam("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate,
                                                                             @RequestParam(defaultValue = "0") int page,
                                                                             @RequestParam(defaultValue = "15") int size
                                                          ) {

        Page<ArticleDTO> res = articleService.getArticlesByFilters(newspaperId, startDate, endDate, page, size );

        return ResponseEntity.ok(new PaginatedArticlesRes(
          res.getContent(),
          res.getNumber(),
          res.getSize(),
          res.getTotalElements(),
          res.getTotalPages()
        ));
    }
}
