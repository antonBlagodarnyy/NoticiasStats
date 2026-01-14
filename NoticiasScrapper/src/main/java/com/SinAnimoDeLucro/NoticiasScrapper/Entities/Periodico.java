package com.SinAnimoDeLucro.NoticiasScrapper.Entities;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "periodico")
public class Periodico{
    @Id
    private Integer id;

    @Column(nullable = false, unique = true)
    private String nombre;

    @Column(nullable = false, unique = true)
    private String link;

    @OneToMany(mappedBy = "periodico", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Articulo> articulos;

    public Periodico(String link, String nombre) {
        this.link = link;
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public String getLink() {
        return link;
    }

}
