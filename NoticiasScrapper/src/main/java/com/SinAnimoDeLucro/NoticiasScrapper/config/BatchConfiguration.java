package com.SinAnimoDeLucro.NoticiasScrapper.Config;

import com.SinAnimoDeLucro.NoticiasScrapper.Steps.ItemWriterStep;
import com.SinAnimoDeLucro.NoticiasScrapper.Steps.RtveScrapingStep;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobScope;
import org.springframework.batch.core.job.Job;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.Step;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@EnableBatchProcessing
public class BatchConfiguration {

  private JobRepository jobRepository;
  private PlatformTransactionManager transactionManager;
  private RtveScrapingStep rtveScrapingStep;
  private ItemWriterStep itemWriterStep;

  public BatchConfiguration(JobRepository jobRepository, PlatformTransactionManager transactionManager, RtveScrapingStep rtveScrapingStep, ItemWriterStep itemWriterStep) {
    this.jobRepository = jobRepository;
    this.transactionManager = transactionManager;
    this.rtveScrapingStep = rtveScrapingStep;
    this.itemWriterStep = itemWriterStep;
  }

  @Bean
  public Step scrapingStep() {
    return new StepBuilder("rtveScrapingStep", jobRepository)
      .tasklet(rtveScrapingStep, transactionManager)
      .build();
  }

  @Bean
  public Step writingStep() {
    return new StepBuilder("itemWriterStep", jobRepository)
      .tasklet(itemWriterStep, transactionManager)
      .build();
  }

  @Bean
  public Job runScraperJob(){
    return new JobBuilder("runScraperJob", jobRepository)
      .start(scrapingStep())
      .next(writingStep())
      .build();
  }
}
