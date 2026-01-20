package com.SinAnimoDeLucro.NoticiasScraper.Steps.ScrapeArticlesStep;

import com.SinAnimoDeLucro.NoticiasScraper.Interfaces.Source;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.infrastructure.item.ItemReader;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@StepScope
public class SourcesReader implements ItemReader<Source> {

    @Value("#{jobExecutionContext['newsSources']}")
    private List<Source> sources;

    private int nextIndex = 0;

    @Override
    public Source read() {
        if (nextIndex < sources.size()) {
            return sources.get(nextIndex++);
        }
        return null;
    }
}