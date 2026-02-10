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

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Component("20Minutos")
@Slf4j
public class VeinteMinutosScraper implements Scraper {
  @Autowired
  private NewsPaperServiceImpl newsPaperService;

  @Autowired
  private ArticleServiceImpl articleService;

  @Override
  public List<Article> scrape(String url) {
    log.info("-----> [VeinteMinutosScraper] -> Leyendo el HTML del noticiero 20Mintutos... <-----");
    Newspaper veinteMinutos = newsPaperService.findByName("20Minutos");
    DateTimeFormatter dateFormat =
      DateTimeFormatter.ofPattern("dd MMM yyyy", Locale.forLanguageTag("es-ES"));
    List<Article> newsToday = new ArrayList<>();

    try {
      Document doc = Jsoup.connect(url).get();
      Elements newsItems = doc.select("article");
      for (Element newsItem : newsItems) {
        Element urlNewsEl = newsItem.selectFirst("a");
        if(urlNewsEl == null) continue;

        String urlNews = urlNewsEl.attr("abs:href");
        if(urlNews.isBlank()) continue;

        if (articleService.existsByUrl(urlNews)) {
          log.debug("[VeinteMinutosScraper] -> Noticia duplicada, se ignora: {}", urlNews);
          continue;
        }

        try {
          Document newsDoc = Jsoup.connect(urlNews).get();
          Element headline = newsDoc.selectFirst("h1.c-detail__title");
          Element category = newsDoc.selectFirst("div.c-detail__category");
          Element publicationDate = newsDoc.selectFirst("span.c-detail__date");

          if (publicationDate == null ||category == null || headline == null) {
            log.debug("[VeinteMinutosScraper] -> Noticia incompleta, se ignora: {}", urlNews);
            continue;
          }

          String dateText = publicationDate.text().split("-")[0].trim();
          LocalDate newsDate = LocalDate.parse(dateText, dateFormat);
          LocalDate now = LocalDate.now();

          if (newsDate.equals(now)) {
            Article article = new Article(headline.text(), urlNews, category.text(), now, veinteMinutos);

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
          log.error("[VeinteMinutosScraper] -> Error al obtener el detalle de la noticia: {}", url, e);
        }
      }
    } catch (Exception e) {
      log.error("[VeinteMinutosScraper] -> Error al procesar la página web: " + e.getMessage());
    }

    log.info("-----> [VeinteMinutosScraper] -> Fin del paso de lectura de news del día <-----");

    return newsToday;
  }
}
