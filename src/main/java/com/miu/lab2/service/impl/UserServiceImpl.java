package com.miu.lab2.service.impl;

import com.miu.lab2.Util.ListModelMapper;
import com.miu.lab2.domain.Comment;
import com.miu.lab2.domain.Role;
import com.miu.lab2.domain.User;
import com.miu.lab2.domain.dto.CommentDTO;
import com.miu.lab2.domain.dto.UserDTO;
import com.miu.lab2.repository.CommentRepository;
import com.miu.lab2.repository.RoleRepository;
import com.miu.lab2.repository.UserRepository;
import com.miu.lab2.service.UserService;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService, UserDetailsService {

  private final UserRepository userRepository;
  private final RoleRepository roleRepository;
  private final ModelMapper modelMapper;
  private final ListModelMapper<User, UserDTO> listModelMapper;
  private final CommentRepository commentRepository;
  private final PasswordEncoder passwordEncoder;

  @Override
  public List<UserDTO> findAll() {
    var users = (List<User>) userRepository.findAll();
    return (List<UserDTO>) listModelMapper.mapList(users, new UserDTO());
  }

  @Override
  public UserDTO getById(long id) {
    return modelMapper.map(userRepository.findById(id).get(), UserDTO.class);
  }

  @Override
  public void save(UserDTO userDTO) {
    var user = modelMapper.map(userDTO, User.class);
    List<Role> roleList = (List<Role>) roleRepository.findAll();
    user.setRoles(roleList);
    user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
    userRepository.save(user);
  }

  @Override
  public void delete(long id) {
    userRepository.deleteById(id);
  }

  @Override
  public List<UserDTO> userWithMoreThanOnePost(int postMoreThan) {
    return (List<UserDTO>) listModelMapper.mapList(userRepository.userWithMoreThanOnePost(postMoreThan), new UserDTO());
  }

  @Override
  public CommentDTO getCommentBySpecificUserAndPostId(long userId, long postId, long commentId) {
    Optional<Comment> comment = commentRepository.getCommentBySpecificUserAndPostId(userId, postId, commentId);
    return comment.map(value -> modelMapper.map(value, CommentDTO.class)).orElse(null);
  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    var user = userRepository.findByEmail(username);
    return new AppUserDetails(user);
  }
}
