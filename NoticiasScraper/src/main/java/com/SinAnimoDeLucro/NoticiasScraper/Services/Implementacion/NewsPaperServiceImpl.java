package com.SinAnimoDeLucro.NoticiasScraper.Services.Implementacion;

import com.SinAnimoDeLucro.NoticiasScraper.Entities.Newspaper;
import com.SinAnimoDeLucro.NoticiasScraper.Interfaces.Source;
import com.SinAnimoDeLucro.NoticiasScraper.Repositories.NewspaperRepository;
import com.SinAnimoDeLucro.NoticiasScraper.Services.Interfaces.INewspaperService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
