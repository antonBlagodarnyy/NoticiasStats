package com.SinAnimoDeLucro.NoticiasApi.Controllers;

import com.SinAnimoDeLucro.NoticiasApi.Dto.GetAllNewspapersRes;
import com.SinAnimoDeLucro.NoticiasApi.Services.INewsPaperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/newspapers")
public class NewspaperController {

  @Autowired
  private INewsPaperService newspaperService;

  @GetMapping("/get-all")
  public ResponseEntity<GetAllNewspapersRes> getAll() {
    return ResponseEntity.ok(new GetAllNewspapersRes(newspaperService.findAll()));
  }
}
