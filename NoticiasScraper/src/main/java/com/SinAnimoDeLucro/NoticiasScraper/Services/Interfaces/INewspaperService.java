package com.SinAnimoDeLucro.NoticiasScraper.Services.Interfaces;

import com.SinAnimoDeLucro.NoticiasScraper.Entities.Newspaper;
import com.SinAnimoDeLucro.NoticiasScraper.Interfaces.Source;

import java.util.List;

public interface INewspaperService {
  Newspaper findByName(String name);

  List<Source> findSources();
}
