package com.SinAnimoDeLucro.NoticiasScraper.Config;

import com.SinAnimoDeLucro.NoticiasScraper.Entities.Article;
import com.SinAnimoDeLucro.NoticiasScraper.Model.Source;
import com.SinAnimoDeLucro.NoticiasScraper.Steps.CleanOldArticlesTasklet;
import com.SinAnimoDeLucro.NoticiasScraper.Steps.ScrapeArticlesStep.ArticleWriter;
import com.SinAnimoDeLucro.NoticiasScraper.Steps.ScrapeArticlesStep.SourcesProcessor;
import com.SinAnimoDeLucro.NoticiasScraper.Steps.ScrapeArticlesStep.SourcesReader;
import org.springframework.batch.core.job.Job;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.Step;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

import java.util.List;
@Configuration
public class BatchConfiguration {


  private final SourcesReader sourcesReader;
  private final SourcesProcessor sourcesProcessor;
  private final ArticleWriter articleWriter;

  private final JobRepository jobRepository;
  private final PlatformTransactionManager transactionManager;

  public BatchConfiguration(
          JobRepository jobRepository,
          PlatformTransactionManager transactionManager,

          SourcesReader sourcesReader,
          SourcesProcessor sourcesProcessor,
          ArticleWriter articleWriter) {

    this.jobRepository = jobRepository;
    this.transactionManager = transactionManager;

    this.sourcesReader = sourcesReader;
    this.sourcesProcessor = sourcesProcessor;
    this.articleWriter = articleWriter;
  }


  @Bean
  public Step cleanOldArticlesStep(CleanOldArticlesTasklet tasklet) {
    return new StepBuilder("cleanOldArticlesStep", jobRepository)
            .tasklet(tasklet, transactionManager)
            .build();
  }

  @Bean
  public Step scrapeArticlesStep() {
    return new StepBuilder("scrapeArticlesStep", jobRepository)
            .<Source, List<Article>>chunk(1)
            .reader(sourcesReader)
            .processor(sourcesProcessor)
            .writer(articleWriter)
            .build();
  }

  @Bean
  public Job runScraperJob() {
    return new JobBuilder("runScraperJob", jobRepository)
            .start(cleanOldArticlesStep(null))
            .next(scrapeArticlesStep())
            .build();
  }
}