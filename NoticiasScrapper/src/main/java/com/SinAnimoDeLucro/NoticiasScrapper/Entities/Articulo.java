package com.SinAnimoDeLucro.NoticiasScrapper.Entities;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "articulo")
public class Articulo {
    @Id
    private Integer id;

    @Column(nullable = false, unique = true)
    private String titular;

    @Column(nullable = false, unique = true)
    private String link;

    @Column(nullable = false, unique = true)
    private Date fecha;

    @ManyToOne
    @JoinColumn(name = "periodico", nullable = false)
    private Periodico periodico;

}
