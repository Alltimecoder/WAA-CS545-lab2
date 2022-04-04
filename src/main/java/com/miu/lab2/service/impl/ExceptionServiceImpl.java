package com.miu.lab2.service.impl;

import com.miu.lab2.domain.Exception;
import com.miu.lab2.repository.ExceptionRepository;
import com.miu.lab2.service.ExceptionService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExceptionServiceImpl implements ExceptionService {

  private final ExceptionRepository exceptionRepository;

  @Autowired
  public ExceptionServiceImpl(ExceptionRepository exceptionRepository) {
    this.exceptionRepository = exceptionRepository;
  }

  @Override
  public List<Exception> findAll() {
    return (List<Exception>) exceptionRepository.findAll();
  }

  @Override
  public Exception getById(long id) {
    return exceptionRepository.findById(id).get();
  }

  @Override
  public void save(Exception e) {
    exceptionRepository.save(e);
  }

  @Override
  public void delete(long id) {
    exceptionRepository.deleteById(id);
  }
}
