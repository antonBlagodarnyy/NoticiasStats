package com.SinAnimoDeLucro.NoticiasScraper.Scrapers;

import com.SinAnimoDeLucro.NoticiasScraper.Entities.Article;
import com.SinAnimoDeLucro.NoticiasScraper.Entities.Newspaper;
import com.SinAnimoDeLucro.NoticiasScraper.Interfaces.Scraper;
import com.SinAnimoDeLucro.NoticiasScraper.Services.Implementacion.ArticleServiceImpl;
import com.SinAnimoDeLucro.NoticiasScraper.Services.Implementacion.NewsPaperServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


@Component("RTVE")
@Slf4j
public class RtveScraper implements Scraper {
  @Autowired
  private NewsPaperServiceImpl newsPaperService;

  @Autowired
  private ArticleServiceImpl articleService;

  @Override
  public List<Article> scrape(String url) {
    log.info("-----> [RtveScraper] -> Leyendo el HTML del noticiero RTVE... <-----");
    Newspaper rtve = newsPaperService.findByName("RTVE");
    SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
    Calendar now = Calendar.getInstance();
    List<Article> newsToday = new ArrayList<>();

    try {
      Document doc = Jsoup.connect(url).get();
      Elements newsItems = doc.select("article");
      for (Element newsItem : newsItems) {
        Element urlNewsEl = newsItem.selectFirst("a");

        if(urlNewsEl == null) continue;
        String urlNews = urlNewsEl.attr("abs:href");

        if (articleService.existsByUrl(urlNews)) {
          log.debug("[RtveScraper] -> Noticia duplicada, se ignora: {}", urlNews);
          continue;
        }

        try {
          Document newsDoc = Jsoup.connect(urlNews).get();
          Element headline = newsDoc.selectFirst("span.maintitle");
          Element category = newsDoc.selectFirst("span.pretitle");
          Element publicationDate = newsDoc.selectFirst("span.datpub");

          if (publicationDate == null || category == null || headline == null) {
            log.debug("[RtveScraper] -> Noticia incompleta, se ignora: {}", urlNews);
            continue;
          }

          String dateText = publicationDate.text().split("-")[0].trim();

          if (dateFormat.format(now.getTime()).equals(dateText)) {
            LocalDate date = LocalDate.now();
            Article article = new Article(headline.text(), urlNews, category.text(), date, rtve);

            //Check the article is not duplicated
            boolean exists = newsToday.stream()
              .map(Article::getUrl)
              .anyMatch(urlNews::equals);

            if (!exists) {
              newsToday.add(article);
            } else {
              log.warn("[VeinteMinutosScraper] -> Duplicated article in source");
            }
          }
        } catch (Exception e) {
          log.error("[RtveScraper] -> Error al obtener el detalle de la noticia: {}", url, e);
        }
      }
    } catch (Exception e) {
      log.error("[RtveScraper] -> Error al procesar la página web: " + e.getMessage());
    }

    log.info("-----> [RtveScraper] -> Fin del paso de lectura de news del día <-----");

    return newsToday;
  }
}

