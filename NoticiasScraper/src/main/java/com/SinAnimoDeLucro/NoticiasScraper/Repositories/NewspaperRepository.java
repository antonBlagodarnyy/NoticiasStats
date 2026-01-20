package com.SinAnimoDeLucro.NoticiasScraper.Repositories;

import com.SinAnimoDeLucro.NoticiasScraper.Entities.Newspaper;
import com.SinAnimoDeLucro.NoticiasScraper.Interfaces.Source;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface NewspaperRepository extends JpaRepository<Newspaper, Long> {

  @Query("select n from Newspaper n where n.name = ?1")
  Optional<Newspaper> findByName(String name);

  @Query("SELECT n.name AS name, n.url AS url from Newspaper n")
  List<Source> findSources();
}
