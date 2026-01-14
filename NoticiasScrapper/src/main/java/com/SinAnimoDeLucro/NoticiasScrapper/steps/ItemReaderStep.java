package com.SinAnimoDeLucro.NoticiasScrapper.steps;

import lombok.extern.slf4j.Slf4j;
import org.jspecify.annotations.Nullable;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.StepContribution;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.infrastructure.repeat.RepeatStatus;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class ItemReaderStep implements Tasklet {

  @Override
  public @Nullable RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
    log.info("-----> Leyendo el HTML de la página web... <-----");

    return null;
  }
}
