package com.SinAnimoDeLucro.NoticiasScraper.Services;

import com.SinAnimoDeLucro.NoticiasScraper.Entities.Newspaper;
import com.SinAnimoDeLucro.NoticiasScraper.Interfaces.Source;
import com.SinAnimoDeLucro.NoticiasScraper.Repositories.NewspaperRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class NewsPaperServiceImpl implements INewspaperService {

  @Autowired
  private NewspaperRepository repository;

  @Transactional(readOnly = true)
  @Override
  public Newspaper findByName(String name) {
    return repository.findByName(name)
            .orElseThrow(() -> new EntityNotFoundException(
                    "Newspaper not found with name: " + name));
  }
  @Override
  public List<Source> findSources() {
    return repository.findSources();
  }
}
