package com.SinAnimoDeLucro.NoticiasScrapper.Entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
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
}
