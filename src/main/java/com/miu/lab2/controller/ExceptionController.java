package com.miu.lab2.controller;

import com.miu.lab2.domain.Exception;
import com.miu.lab2.service.ExceptionService;
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
@RequestMapping("/api/v1/exceptions")
public class ExceptionController {

  private final ExceptionService exceptionService;

  @Autowired
  public ExceptionController(ExceptionService exceptionService) {
    this.exceptionService = exceptionService;
  }


  @ResponseStatus(HttpStatus.OK)
  @GetMapping
  public List<Exception> getAll() {
    return exceptionService.findAll();
  }

  @GetMapping("/{id}")
  public ResponseEntity<Exception> getById(@PathVariable int id) {
    var product = exceptionService.getById(id);
    return ResponseEntity.ok(product);
  }

  @ResponseStatus(HttpStatus.NO_CONTENT)
  @DeleteMapping("/{id}")
  public void delete(@PathVariable int id) {
    exceptionService.delete(id);
  }
}
