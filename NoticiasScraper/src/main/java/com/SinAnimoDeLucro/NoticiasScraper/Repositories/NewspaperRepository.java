package com.SinAnimoDeLucro.NoticiasScraper.Repositories;

import com.SinAnimoDeLucro.NoticiasScraper.Entities.Newspaper;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface NewspaperRepository extends CrudRepository<Newspaper, Long> {

  @Query("select n from Newspaper n where n.name = ?1")
  Optional<Newspaper> findByName(String name);
}
