package com.SinAnimoDeLucro.NoticiasScraper.Steps.ScrapeArticlesStep;

import com.SinAnimoDeLucro.NoticiasScraper.Config.NewsProperties;
import com.SinAnimoDeLucro.NoticiasScraper.Model.Source;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.infrastructure.item.ItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@StepScope
public class SourcesReader implements ItemReader<Source> {


    private int nextIndex = 0;

    @Override
    public Source read() {

        if (nextIndex < Source.values().length) {
            Source src = Source.values()[nextIndex];
            nextIndex ++;
            return src;
        }

        return null;
    }
}