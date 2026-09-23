package com.SinAnimoDeLucro.NoticiasScraper.Steps.ScrapeArticlesStep;

import com.SinAnimoDeLucro.NoticiasScraper.Entities.Article;
import com.SinAnimoDeLucro.NoticiasScraper.Model.Source;
import com.SinAnimoDeLucro.NoticiasScraper.Scrapers.GeneralScrapper;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.infrastructure.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@StepScope
public class SourcesProcessor implements ItemProcessor<Source, List<Article>> {

    @Autowired
    private GeneralScrapper scrapper;

    @Override
    public List<Article> process(Source source) throws Exception {
        return    scrapper.scrape(source);
    }
}
