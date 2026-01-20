package com.SinAnimoDeLucro.NoticiasScraper.Config;

import com.SinAnimoDeLucro.NoticiasScraper.Entities.Article;
import com.SinAnimoDeLucro.NoticiasScraper.Interfaces.Source;
import com.SinAnimoDeLucro.NoticiasScraper.Steps.FetchSourcesStep;
import com.SinAnimoDeLucro.NoticiasScraper.Steps.ScrapeArticlesStep.ArticleWriter;
import com.SinAnimoDeLucro.NoticiasScraper.Steps.ScrapeArticlesStep.SourcesProcessor;
import com.SinAnimoDeLucro.NoticiasScraper.Steps.ScrapeArticlesStep.SourcesReader;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.job.Job;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.Step;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

import java.util.List;

@Configuration
@EnableBatchProcessing
public class BatchConfiguration {

  @Autowired
  private FetchSourcesStep fetchSourcesStep;

  @Autowired
  private SourcesReader sourcesReader;

  @Autowired
  private SourcesProcessor sourcesProcessor;

  @Autowired
  private ArticleWriter articleWriter;

  private JobRepository jobRepository;
  private PlatformTransactionManager transactionManager;

  public BatchConfiguration(JobRepository jobRepository,
                            PlatformTransactionManager transactionManager) {
    this.jobRepository = jobRepository;
    this.transactionManager = transactionManager;
  }

  @Bean
  public Step fetchNewsSourcesStep() {
    return new StepBuilder("fetchNewsSourcesStep", jobRepository)
            .tasklet(fetchSourcesStep, transactionManager)
            .build();
  }


  @Bean
  public Step scrapeArticlesStep() {
    return new StepBuilder("scrapeArticlesStep", jobRepository)
            .<Source, List<Article>>chunk(5)
            .reader(sourcesReader)
            .processor(sourcesProcessor)
            .writer(articleWriter)
            .build();
  }

  @Bean
  public Job runScraperJob(){
    return new JobBuilder("runScraperJob", jobRepository)
      .start(fetchNewsSourcesStep())
      .next(scrapeArticlesStep())
      .build();
  }
}
