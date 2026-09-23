package com.SinAnimoDeLucro.NoticiasScraper.Config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@ConfigurationProperties(prefix = "news")
@Component
@Getter
@Setter
public class NewsProperties {

    private Map<String, SourceConfig> sources = new HashMap<>();

    @Getter
    @Setter
    public static class SourceConfig {
        private String url;
    }
}