package com.SinAnimoDeLucro.NoticiasScrapper.Steps;

import com.SinAnimoDeLucro.NoticiasScrapper.Entities.Article;
import com.SinAnimoDeLucro.NoticiasScrapper.Services.ArticleServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.jspecify.annotations.Nullable;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.StepContribution;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.infrastructure.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
public class ItemWriterStep implements Tasklet {

  @Autowired
  private ArticleServiceImpl articleService;

  @Override
  public @Nullable RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
    log.info("-----> Executando paso escritura <-----");

    @SuppressWarnings("unchecked")
    List<Article> newsToday = (List<Article>) chunkContext.getStepContext()
      .getStepExecution()
      .getJobExecution()
      .getExecutionContext().get("newsToday");

    articleService.saveAll(newsToday);

    log.info("---- > Noticias guardadas en la base de datos. <-----");

    return RepeatStatus.FINISHED;
  }
}
