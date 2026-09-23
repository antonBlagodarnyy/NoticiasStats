package com.SinAnimoDeLucro.NoticiasScraper.Services.Interfaces;

import com.SinAnimoDeLucro.NoticiasScraper.Entities.Newspaper;
import com.SinAnimoDeLucro.NoticiasScraper.Model.Source;

import java.util.List;

public interface INewspaperService {
  Newspaper findByName(String name);

}
