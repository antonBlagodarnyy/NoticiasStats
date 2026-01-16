package com.SinAnimoDeLucro.NoticiasScraper.Services;

import com.SinAnimoDeLucro.NoticiasScraper.Entities.Newspaper;

import java.util.Optional;

public interface INewspaperService {
  Optional<Newspaper> findByName(String name);
}
