package com.SinAnimoDeLucro.NoticiasScraper.Scrapers;

import com.SinAnimoDeLucro.NoticiasScraper.Entities.Article;
import com.SinAnimoDeLucro.NoticiasScraper.Interfaces.Scraper;
import com.SinAnimoDeLucro.NoticiasScraper.Services.ArticleServiceImpl;
import com.SinAnimoDeLucro.NoticiasScraper.Services.NewsPaperServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
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
    log.info("-----> Leyendo el HTML del noticiero 20Mintutos... <-----");
    List<Article> newsToday = new ArrayList<>();

    try {
      Document doc = Jsoup.connect(url).get();
      Elements newsItems = doc.select("article");
      for (Element newsItem : newsItems) {
        String urlNews = newsItem.select("a").attr("abs:href");

        if (articleService.existsByUrl(urlNews)) {
          log.debug("Noticia duplicada, se ignora: {}", urlNews);
          continue;
        }

        try {
          Document newsDoc = Jsoup.connect(urlNews).get();
          Element publicationDate = newsDoc.selectFirst("span.c-detail__date");

          if (publicationDate == null) {
            log.debug("Noticia sin fecha, se ignora: {}", urlNews);
            continue;
          }

          String dateText = publicationDate.text().split("-")[0].trim();

          DateTimeFormatter dateFormat =
            DateTimeFormatter.ofPattern("dd MMM yyyy", Locale.forLanguageTag("es-ES"));


          LocalDate newsDate = LocalDate.parse(dateText, dateFormat);
          LocalDate today = LocalDate.now();

          if (newsDate.equals(today)) {
            String headline = newsDoc.select("h1.c-detail__title").text();
            LocalDate date = LocalDate.now();
            Article article = new Article(headline, urlNews, date, newsPaperService.findByName("20Minutos"));
            //Check the article is not duplicated
            boolean exists = newsToday.stream()
              .map(Article::getUrl)
              .anyMatch(urlNews::equals);

            if (!exists) {
              newsToday.add(article);
            } else {
              log.warn("Duplicated article in source");
            }
          }
        } catch (Exception e) {
          log.error("Error al obtener el detalle de la noticia: " + e.getMessage());
        }
      }
//      log.info("Newspaper: " + newsToday);
    } catch (Exception e) {
      log.error("Error al procesar la página web: " + e.getMessage());
    }

    log.info("-----> Fin del paso de lectura de news del día <-----");


    return newsToday;
  }
}
