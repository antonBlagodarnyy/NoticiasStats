package com.SinAnimoDeLucro.NoticiasScraper.Services;

import com.SinAnimoDeLucro.NoticiasScraper.Entities.Newspaper;
import com.SinAnimoDeLucro.NoticiasScraper.Repositories.NewspaperRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class NewsPaperServiceImpl implements INewspaperService {

  @Autowired
  private NewspaperRepository repository;

  @Transactional(readOnly = true)
  @Override
  public Optional<Newspaper> findByName(String name) {
    return repository.findByName(name);
  }
}
