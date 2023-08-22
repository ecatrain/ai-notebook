package com.ainotebook.core.controller;

import com.ainotebook.core.service.NotebookService;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@Slf4j
@AllArgsConstructor
@RestController
public class NotebookController {

  private final NotebookService notebookService;
  
  @GetMapping("/quote")
  public String quote() {
    log.info("Request received : Get Quote");
    String quote = notebookService.obtainQuote();
    return quote;
  }

}
