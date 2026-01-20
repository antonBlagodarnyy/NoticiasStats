package com.SinAnimoDeLucro.NoticiasScraper.Steps.ScrapeArticlesStep;

import com.SinAnimoDeLucro.NoticiasScraper.Entities.Article;
import com.SinAnimoDeLucro.NoticiasScraper.Interfaces.Source;
import com.SinAnimoDeLucro.NoticiasScraper.Interfaces.Scraper;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.infrastructure.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@StepScope
public class SourcesProcessor implements ItemProcessor<Source, List<Article>> {

    @Autowired
    private ApplicationContext context; // for fetching Scraper beans

    @Override
    public List<Article> process(Source source) throws Exception {
        Scraper scraper = (Scraper) context.getBean(source.getName());
        return scraper.scrape(source.getUrl());
    }
}
