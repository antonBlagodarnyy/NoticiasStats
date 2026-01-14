package com.SinAnimoDeLucro.NoticiasScrapper.Entities;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
@Table(name = "articles")
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String headline;

    @Column(nullable = false, unique = true)
    private String url;

    @Column(nullable = false)
    private LocalDate publishedAt;

    @ManyToOne
    @JoinColumn(name = "newspaper_id", nullable = false)
    private Newspaper newspaper;
}
