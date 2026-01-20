package com.SinAnimoDeLucro.NoticiasScraper.Interfaces;

import com.SinAnimoDeLucro.NoticiasScraper.Entities.Article;

import java.util.List;

public interface Scraper {
    List<Article> scrape(String url);
}
