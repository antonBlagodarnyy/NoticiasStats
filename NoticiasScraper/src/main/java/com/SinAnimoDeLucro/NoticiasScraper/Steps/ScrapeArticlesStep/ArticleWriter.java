package com.SinAnimoDeLucro.NoticiasScraper.Steps.ScrapeArticlesStep;

import com.SinAnimoDeLucro.NoticiasScraper.Entities.Article;
import com.SinAnimoDeLucro.NoticiasScraper.Services.Implementacion.ArticleServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.infrastructure.item.Chunk;
import org.springframework.batch.infrastructure.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@StepScope
@Slf4j
public class ArticleWriter implements ItemWriter<List<Article>> {
    @Autowired
    private ArticleServiceImpl articleService;

    @Override
    public void write(Chunk<? extends List<Article>> chunk) throws Exception {
        log.info(" -----> [ArticleWriter] -> Guardando las noticias en la base de datos.. <-----");
        for (List<Article> articles : chunk) {
            articleService.saveAll(articles);
        }
        log.info(" -----> [ArticleWriter] -> Proceso Batch finalizado correctamente. <-----");
    }
}
