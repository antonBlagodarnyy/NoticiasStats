package com.SinAnimoDeLucro.NoticiasScrapper.Services;

import com.SinAnimoDeLucro.NoticiasScrapper.Entities.Newspaper;

import java.util.List;
import java.util.Optional;

public interface INewspaperService {
  Optional<Newspaper> findByName(String name);
}
