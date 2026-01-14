package com.SinAnimoDeLucro.NoticiasScrapper.entities;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name="articulos")
public class Articulo {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String titular;
  private String link;
  private String fecha;
}
