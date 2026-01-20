package com.SinAnimoDeLucro.NoticiasScraper.Services;

import com.SinAnimoDeLucro.NoticiasScraper.Entities.Newspaper;
import com.SinAnimoDeLucro.NoticiasScraper.Interfaces.Source;

import java.util.List;
import java.util.Optional;

public interface INewspaperService {
  Newspaper findByName(String name);

  List<Source> findSources();
}
