package com.SinAnimoDeLucro.NoticiasScraper.Interfaces;

import com.SinAnimoDeLucro.NoticiasScraper.Entities.Article;
import com.SinAnimoDeLucro.NoticiasScraper.Model.Source;

import java.util.List;

public interface Scraper {
    List<Article> scrape(Source src);
}
