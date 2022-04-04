package com.miu.lab2.service.impl;

import com.miu.lab2.domain.Log;
import com.miu.lab2.repository.LoggerRepository;
import com.miu.lab2.service.LoggerService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoggerServiceImpl implements LoggerService {

  private final LoggerRepository loggerRepository;

  @Autowired
  public LoggerServiceImpl(LoggerRepository loggerRepository) {
    this.loggerRepository = loggerRepository;
  }

  @Override
  public List<Log> findAll() {
    return (List<Log>) loggerRepository.findAll();
  }

  @Override
  public Log getById(long id) {
    return loggerRepository.findById(id).get();
  }

  @Override
  public void save(Log log) {
    loggerRepository.save(log);
  }

  @Override
  public void delete(long id) {
    loggerRepository.deleteById(id);
  }
}
