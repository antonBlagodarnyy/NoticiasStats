package com.SinAnimoDeLucro.NoticiasApi.Repositories;

import com.SinAnimoDeLucro.NoticiasApi.Entities.Newspaper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface NewspaperRepository extends JpaRepository<Newspaper, Integer> {
    @Query("SELECT n.name FROM Newspaper n")
    List<String> findAllNames();
}
