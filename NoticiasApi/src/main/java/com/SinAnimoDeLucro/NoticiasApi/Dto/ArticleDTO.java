package com.SinAnimoDeLucro.NoticiasApi.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data // Genera getters, setters, toString, equals y hashCode
@AllArgsConstructor // Genera constructores con todos los campos
public class ArticleDTO {
  private String headline;
  private String url;
  private String category;
  private LocalDate publishedAt;
  private String newspaperName;
}
