package com.SinAnimoDeLucro.NoticiasScraper.Steps.ScrapeArticlesStep;

import com.SinAnimoDeLucro.NoticiasScraper.Entities.Article;
import com.SinAnimoDeLucro.NoticiasScraper.Interfaces.Source;
import com.SinAnimoDeLucro.NoticiasScraper.Services.ArticleServiceImpl;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.infrastructure.item.Chunk;
import org.springframework.batch.infrastructure.item.ItemProcessor;
import org.springframework.batch.infrastructure.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@StepScope
public class ArticleWriter implements ItemWriter<List<Article>> {
    @Autowired
    private ArticleServiceImpl articleService;

    @Override
    public void write(Chunk<? extends List<Article>> chunk) throws Exception {
        for (List<Article> articles : chunk) {
            articleService.saveAll(articles);
        }
    }
}
