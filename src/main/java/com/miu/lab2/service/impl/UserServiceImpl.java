package com.miu.lab2.service.impl;

import com.miu.lab2.Util.ListModelMapper;
import com.miu.lab2.domain.User;
import com.miu.lab2.domain.dto.UserDTO;
import com.miu.lab2.repository.UserRepository;
import com.miu.lab2.service.UserService;
import java.util.List;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

  private final UserRepository userRepository;
  private final ModelMapper modelMapper;
  private final ListModelMapper<User, UserDTO> listModelMapper;

  @Autowired
  public UserServiceImpl(UserRepository userRepository,
      ModelMapper modelMapper, ListModelMapper<User, UserDTO> listModelMapper) {
    this.userRepository = userRepository;
    this.modelMapper = modelMapper;
    this.listModelMapper = listModelMapper;
  }

  @Override
  public List<UserDTO> findAll() {
    return (List<UserDTO>) listModelMapper.mapList((List<User>) userRepository.findAll(), new UserDTO());
  }

  @Override
  public UserDTO getById(long id) {
    return modelMapper.map(userRepository.findById(id), UserDTO.class);
  }

  @Override
  public void save(UserDTO userDTO) {
    userRepository.save(modelMapper.map(userDTO, User.class));
  }

  @Override
  public void delete(long id) {
    userRepository.deleteById(id);
  }

  @Override
  public List<UserDTO> userWithMoreThanOnePost(int postMoreThan) {
    return (List<UserDTO>) listModelMapper.mapList(userRepository.userWithMoreThanOnePost(postMoreThan), new UserDTO());
  }
}
