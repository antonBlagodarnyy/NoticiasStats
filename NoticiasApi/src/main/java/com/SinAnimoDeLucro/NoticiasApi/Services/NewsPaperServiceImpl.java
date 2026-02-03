package com.SinAnimoDeLucro.NoticiasApi.Services;

import com.SinAnimoDeLucro.NoticiasApi.Dto.ArticleDTO;
import com.SinAnimoDeLucro.NoticiasApi.Dto.NewspaperDTO;
import com.SinAnimoDeLucro.NoticiasApi.Entities.Newspaper;
import com.SinAnimoDeLucro.NoticiasApi.Repositories.NewspaperRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NewsPaperServiceImpl implements INewsPaperService{

  @Autowired
  private NewspaperRepository newspaperRepository;

  @Override
  public List<NewspaperDTO> findAll() {
    return newspaperRepository.findAll()
      .stream()
      .map(n -> new NewspaperDTO(n.getId(), n.getName()))
      .toList();
  }



}
