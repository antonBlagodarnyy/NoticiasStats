package com.SinAnimoDeLucro.NoticiasScraper.Scrapers;

import com.SinAnimoDeLucro.NoticiasScraper.Config.NewsProperties;
import com.SinAnimoDeLucro.NoticiasScraper.Entities.Article;
import com.SinAnimoDeLucro.NoticiasScraper.Interfaces.Scraper;
import com.SinAnimoDeLucro.NoticiasScraper.Model.Source;
import com.SinAnimoDeLucro.NoticiasScraper.Services.Implementacion.ArticleServiceImpl;
import com.SinAnimoDeLucro.NoticiasScraper.Services.Implementacion.NewsPaperServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

import static com.SinAnimoDeLucro.NoticiasScraper.Model.Source.*;

@Component()
@Slf4j
public class GeneralScrapper implements Scraper {
    @Autowired
    private NewsProperties newsProperties;

    @Autowired
    private NewsPaperServiceImpl newsPaperService;

    @Autowired
    private ArticleServiceImpl articleService;

    @Override
    public List<Article> scrape(Source src) {
        log.info("-----> [Scraper] -> Leyendo el HTML del noticiero {}... <-----", src.getName());
        List<Article> newsToday = new ArrayList<>();
        final boolean rtve = src.getName().equals(RTVE.getName());
        final boolean elDiario = src.getName().equals(EL_DIARIO.getName());
        final boolean abc = src.getName().equals(ABC.getName());
        final boolean veinteMinutos = src.getName().equals(VEINTE_MINUTOS.getName());

        try {
            String url = newsProperties.getSources().get(
                    src.getName()).getUrl();

            Document doc = Jsoup.connect(url).get();

            Elements newsItems = doc.select("article");

            for (Element newsItem : newsItems) {
                Element urlNewsEl = newsItem.selectFirst("a");
                if (urlNewsEl == null) continue;

                String urlNews = urlNewsEl.attr("href");
                if (urlNews.isEmpty()) continue;

                if (veinteMinutos && !urlNews.contains("http")) urlNews = url + urlNews;

                if (articleService.existsByUrl(urlNews)) {
                    log.debug("[Scraper] -> Noticia duplicada, se ignora: {}", urlNews);
                    continue;
                }

                try {

                    Document newsDoc = Jsoup.connect(urlNews).get();
                    Elements data = newsDoc.select("meta");

                    String headline = null;
                    LocalDate date = null;
                    String category = null;


                    for (Element e : data) {

                        String property = e.attr("property");
                        String name = e.attr("name");
                        String content = e.attr("content");

                        //Title
                        if ("og:title".equals(property)) {
                            headline = content;
                        }

                        //Date

                        if (rtve || elDiario || abc) {
                            if ("article:published_time".equals(property)) {
                                date = parseDate(content);
                            }
                        } else {
                            if ("date".equals(name)) {
                                date = parseDate(content);
                            }
                        }

                        //Category
                        if (rtve) {
                            if (category == null && "article:tag".equals(property)) {
                                category = content;
                            }
                        } else {
                            if ("article:section".equals(property)) {
                                category = content;
                            }
                        }
                        if (headline != null && date != null && category != null) {
                            break;
                        }
                    }

                    if (date == null || category == null || headline == null) {
                        log.debug("[Scraper] -> Noticia incompleta, se ignora: {}", urlNews);
                        continue;
                    }
                    LocalDate now = LocalDate.now();

                    if (date.equals(now)) {
                        Article article = new Article(headline, urlNews, category, now, newsPaperService.findByName(src.getName()));

                        //Check the article is not duplicated
                        boolean exists = newsToday.stream()
                                .map(Article::getUrl)
                                .anyMatch(urlNews::equals);

                        if (!exists) {
                            newsToday.add(article);
                        } else {
                            log.warn("[Scraper] -> Duplicated article in source");
                        }
                    }


                } catch (Exception e) {
                    log.error("[Scraper] -> Error al obtener el detalle de la noticia: {}", urlNews, e);
                }
            }
        } catch (Exception e) {
            log.error("[Scraper] -> Error al procesar la página web: " + e.getMessage());
        }
        log.info("-----> [Scraper] -> Fin del paso de lectura de news del día <-----");

        return newsToday;
    }

    private LocalDate parseDate(String date) {
        return OffsetDateTime.parse(date).toLocalDate();
    }
}
