package com.SinAnimoDeLucro.NoticiasApi.Entities;


import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "newspapers")
public class Newspaper{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false, unique = true)
    private String url;

    @OneToMany(mappedBy = "newspaper", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Article> articles;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public List<Article> getArticles() {
        return articles;
    }

    public void setArticles(List<Article> articles) {
        this.articles = articles;
    }

    @Override
    public String toString() {
        return "Newspaper{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
