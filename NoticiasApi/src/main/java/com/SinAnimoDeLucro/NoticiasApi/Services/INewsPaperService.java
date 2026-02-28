package com.SinAnimoDeLucro.NoticiasApi.Services;

import com.SinAnimoDeLucro.NoticiasApi.Dto.NewspaperDTO;

import java.util.List;

public interface INewsPaperService {
  List<NewspaperDTO> findAll();
}
