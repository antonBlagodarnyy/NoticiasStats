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

    @Column(name = "published_at", nullable = false)
    private LocalDate publishedAt;

    @ManyToOne
    @JoinColumn(name = "newspaper_id", nullable = false)
    private Newspaper newspaper;

    public Article(String headline, String url, LocalDate publishedAt, Newspaper newspaper) {
        this.headline = headline;
        this.url = url;
        this.publishedAt = publishedAt;
        this.newspaper = newspaper;
    }
}
