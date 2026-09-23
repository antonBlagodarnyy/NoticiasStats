package com.SinAnimoDeLucro.NoticiasScraper.Steps;

import com.SinAnimoDeLucro.NoticiasScraper.Services.Implementacion.ArticleServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.StepContribution;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.infrastructure.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Component
@Slf4j
public class CleanOldArticlesTasklet implements Tasklet {

    @Autowired
    private ArticleServiceImpl articleService;

    @Override
    public RepeatStatus execute(
            StepContribution contribution,
            ChunkContext chunkContext) {

        LocalDate limit = LocalDate.now().minusDays(30);

        log.info("-----> [CleanOldArticlesStep] -> Eliminando noticias anteriores a {} <-----", limit);

        articleService.deleteArticlesOlderThan(limit);

        log.info("-----> [CleanOldArticlesStep] -> Limpieza finalizada correctamente. <-----");

        return RepeatStatus.FINISHED;
    }
}