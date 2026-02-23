package com.SinAnimoDeLucro.NoticiasApi.Services;

import com.SinAnimoDeLucro.NoticiasApi.Repositories.NewspaperRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NewsPaperServiceImpl implements INewsPaperService{

  @Autowired
  private NewspaperRepository newspaperRepository;

  @Override
  public List<String> findAll() {
    return newspaperRepository.findAllNames();
  }



}
