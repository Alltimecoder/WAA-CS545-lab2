package com.miu.lab2.Util;

import java.util.List;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ListModelMapper<S, T> {

  private final ModelMapper modelMapper;

  @Autowired
  public ListModelMapper(ModelMapper modelMapper) {
    this.modelMapper = modelMapper;
  }

  public List<?> mapList(List<S> listToConvert, T mapTo) {
    return listToConvert.stream()
        .map(s -> modelMapper.map(s, mapTo.getClass()))
        .collect(Collectors.toList());
  }
}
