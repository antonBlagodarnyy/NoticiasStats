package com.SinAnimoDeLucro.NoticiasApi.Repositories;

import com.SinAnimoDeLucro.NoticiasApi.Entities.Newspaper;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NewspaperRepository extends JpaRepository<Newspaper, Integer> { }
