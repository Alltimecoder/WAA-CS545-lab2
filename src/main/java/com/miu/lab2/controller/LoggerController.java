package com.miu.lab2.controller;

import com.miu.lab2.domain.Log;
import com.miu.lab2.service.LoggerService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/logs")
public class LoggerController {

  private final LoggerService loggerService;

  @Autowired
  public LoggerController(LoggerService loggerService) {
    this.loggerService = loggerService;
  }

  @ResponseStatus(HttpStatus.OK)
  @GetMapping
  public List<Log> getAll() {
    return loggerService.findAll();
  }

  @GetMapping("/{id}")
  public ResponseEntity<Log> getById(@PathVariable int id) {
    var product = loggerService.getById(id);
    return ResponseEntity.ok(product);
  }

  @ResponseStatus(HttpStatus.NO_CONTENT)
  @DeleteMapping("/{id}")
  public void delete(@PathVariable int id) {
    loggerService.delete(id);
  }

}
