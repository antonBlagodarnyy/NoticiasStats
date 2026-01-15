package com.SinAnimoDeLucro.NoticiasScrapper.Steps;

import com.SinAnimoDeLucro.NoticiasScrapper.Entities.Article;
import com.SinAnimoDeLucro.NoticiasScrapper.Entities.Newspaper;
import com.SinAnimoDeLucro.NoticiasScrapper.Services.NewsPaperServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.jspecify.annotations.Nullable;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.StepContribution;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.infrastructure.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

@Component
@Slf4j
public class RtveScrapingStep implements Tasklet {

  @Autowired
  private NewsPaperServiceImpl newspaperService;

  @Override
  public @Nullable RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
    log.info("-----> Leyendo el HTML del noticiero RTVE... <-----");

    Optional<Newspaper> rtveNewspaper = newspaperService.findByName("RTVE");

    String url = rtveNewspaper.map(Newspaper::getUrl).orElseThrow();
    log.info("RTVE URL: " + url);

    List<Article> newsToday = new ArrayList<>();

    try {
      Document doc = Jsoup.connect(url).get();
      Elements newsItems = doc.select("article");
      for (Element newsItem : newsItems) {
        String urlNews = newsItem.select("a").attr("abs:href");
        try {
          Document newsDoc = Jsoup.connect(urlNews).get();
          Element publicationDate = newsDoc.selectFirst("span.datpub");

          if (publicationDate == null) {
            log.debug("Noticia sin fecha, se ignora: {}", urlNews);
            continue;
          }

          String dateText = publicationDate.text().split("-")[0].trim();
          SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
          Calendar now = Calendar.getInstance();

          if (dateFormat.format(now.getTime()).equals(dateText)) {
            String headline = newsDoc.select("span.maintitle").text();
            LocalDate date = LocalDate.now();
            newsToday.add(new Article(headline, urlNews, date, rtveNewspaper.get()));
          }
        } catch (Exception e) {
          log.error("Error al obtener el detalle de la noticia: " + e.getMessage());
        }
      }
      log.info("Newspaper: " + newsToday);
    } catch (Exception e) {
      log.error("Error al procesar la página web: " + e.getMessage());
    }

    log.info("-----> Fin del paso de lectura de news del día <-----");

    chunkContext.getStepContext()
      .getStepExecution()
      .getJobExecution()
      .getExecutionContext()
      .put("newsToday", newsToday);

    return RepeatStatus.FINISHED;
  }
}
