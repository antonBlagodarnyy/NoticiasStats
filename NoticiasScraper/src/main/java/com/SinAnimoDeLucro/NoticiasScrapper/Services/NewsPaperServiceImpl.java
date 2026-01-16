package com.SinAnimoDeLucro.NoticiasScrapper.Services;

import com.SinAnimoDeLucro.NoticiasScrapper.Entities.Newspaper;
import com.SinAnimoDeLucro.NoticiasScrapper.Repositories.NewspaperRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class NewsPaperServiceImpl implements INewspaperService {

  @Autowired
  private NewspaperRepository repository;

  @Override
  public Optional<Newspaper> findByName(String name) {
    return repository.findByName(name);
  }
}
