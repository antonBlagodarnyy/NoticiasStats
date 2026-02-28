package com.SinAnimoDeLucro.NoticiasApi.Entities;


import jakarta.persistence.*;
import lombok.Getter;

import java.util.List;

@Entity
@Table(name = "newspapers")
public class Newspaper{
    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Getter
    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false, unique = true)
    private String url;

    @OneToMany(mappedBy = "newspaper", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Article> articles;
}
