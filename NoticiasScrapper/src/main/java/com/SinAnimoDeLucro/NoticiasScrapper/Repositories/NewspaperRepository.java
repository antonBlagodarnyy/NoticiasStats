package com.SinAnimoDeLucro.NoticiasScrapper.Repositories;

import com.SinAnimoDeLucro.NoticiasScrapper.Entities.Newspaper;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NewspaperRepository extends JpaRepository<Newspaper, Integer> {
}
