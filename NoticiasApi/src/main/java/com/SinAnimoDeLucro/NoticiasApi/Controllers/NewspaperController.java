package com.SinAnimoDeLucro.NoticiasApi.Controllers;

import com.SinAnimoDeLucro.NoticiasApi.Dto.GetAllNewspapers;
import com.SinAnimoDeLucro.NoticiasApi.Dto.NewspaperDTO;
import com.SinAnimoDeLucro.NoticiasApi.Services.INewsPaperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/api/newspapers")
public class NewspaperController {

  @Autowired
  private INewsPaperService newspaperService;

  @GetMapping("/get-all")
  public ResponseEntity<GetAllNewspapers> getAll() {
    return ResponseEntity.ok(new GetAllNewspapers(newspaperService.findAll()));
  }
}
