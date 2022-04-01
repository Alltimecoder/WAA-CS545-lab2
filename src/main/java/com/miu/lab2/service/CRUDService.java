package com.miu.lab2.service;

import com.miu.lab2.domain.dto.PostDTO;
import java.util.List;

public interface CRUDService<T> {

  List<T> findAll();

  T getById(long id);

  void save(T t);

  void delete(long id);

}
