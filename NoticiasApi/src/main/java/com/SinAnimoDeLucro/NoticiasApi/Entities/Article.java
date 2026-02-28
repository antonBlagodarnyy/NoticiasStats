package com.SinAnimoDeLucro.NoticiasApi.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDate;

@Entity
@Table(name = "articles")
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Getter
    @Column(nullable = false, columnDefinition = "TEXT")
    private String headline;

    @Getter
    @Column(nullable = false, unique = true)
    private String url;

    @Getter
    @Column(nullable = false)
    private String category;

    @Getter
    @Column(name = "published_at", nullable = false)
    private LocalDate publishedAt;

    @Getter
    @ManyToOne
    @JoinColumn(name = "newspaper_id", nullable = false)
    @JsonIgnore
    private Newspaper newspaper;
}
