package com.SinAnimoDeLucro.NoticiasScrapper.entities;


import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Periodico {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String name;
  private String url;
}
