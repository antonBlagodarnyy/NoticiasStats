package com.SinAnimoDeLucro.NoticiasApi.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "articles")
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String headline;

    @Column(nullable = false, unique = true)
    private String url;

    @Column(nullable = false)
    private String category;

    @Column(name = "published_at", nullable = false)
    private LocalDate publishedAt;

    @ManyToOne
    @JoinColumn(name = "newspaper_id", nullable = false)
    @JsonIgnore
    private Newspaper newspaper;

    public Article() {
    }

    public Article(String headline, String url, LocalDate publishedAt, Newspaper newspaper) {
        this.headline = headline;
        this.url = url;
        this.publishedAt = publishedAt;
        this.newspaper = newspaper;
    }

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }

    public String getHeadline() {
        return headline;
    }
    public void setHeadline(String headline) {
        this.headline = headline;
    }

    public String getUrl() {
        return url;
    }
    public void setUrl(String url) {
        this.url = url;
    }

    public String getCategory() {return category;}
    public void setCategory(String category) {}

    public LocalDate getPublishedAt() {
        return publishedAt;
    }
    public void setPublishedAt(LocalDate publishedAt) {
        this.publishedAt = publishedAt;
    }

    public Newspaper getNewspaper() {
        return newspaper;
    }

    public void setNewspaper(Newspaper newspaper) {
        this.newspaper = newspaper;
    }

    @Override
    public String toString() {
        return "Article{" +
                "id=" + id +
                ", headline='" + headline + '\'' +
                ", url='" + url + '\'' +
                ", publishedAt=" + publishedAt +
                '}';
    }
}
