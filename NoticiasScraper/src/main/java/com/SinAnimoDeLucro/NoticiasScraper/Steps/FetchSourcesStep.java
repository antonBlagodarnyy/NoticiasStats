package com.SinAnimoDeLucro.NoticiasScraper.Steps;

import com.SinAnimoDeLucro.NoticiasScraper.Services.Implementacion.NewsPaperServiceImpl;
import org.jspecify.annotations.Nullable;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.StepContribution;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.infrastructure.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FetchSourcesStep implements Tasklet {

    @Autowired
    private NewsPaperServiceImpl newsPaperService;

    @Override
    public @Nullable RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
        chunkContext.getStepContext()
                .getStepExecution()
                .getJobExecution()
                .getExecutionContext()
                .put("newsSources", newsPaperService.findSources());
        return RepeatStatus.FINISHED;
    }
}
