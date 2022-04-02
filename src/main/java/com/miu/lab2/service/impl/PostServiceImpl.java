package com.miu.lab2.service.impl;

import com.miu.lab2.Util.ListModelMapper;
import com.miu.lab2.domain.Post;
import com.miu.lab2.domain.User;
import com.miu.lab2.domain.dto.PostDTO;
import com.miu.lab2.domain.dto.UserDTO;
import com.miu.lab2.repository.PostRepository;
import com.miu.lab2.repository.UserRepository;
import com.miu.lab2.service.PostService;
import java.util.List;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PostServiceImpl implements PostService {

  private final PostRepository postRepository;
  private final ModelMapper modelMapper;
  private final ListModelMapper<Post, PostDTO> listModelMapper;
  private final UserRepository userRepository;

  @Autowired
  public PostServiceImpl(PostRepository postRepository, ModelMapper modelMapper,
      ListModelMapper<Post, PostDTO> listModelMapper, UserRepository userRepository) {
    this.postRepository = postRepository;
    this.modelMapper = modelMapper;
    this.listModelMapper = listModelMapper;
    this.userRepository = userRepository;
  }

  @Override
  public List<PostDTO> findAll() {
    List<Post> posts = (List<Post>) postRepository.findAll();
    return (List<PostDTO>) listModelMapper.mapList(posts, new PostDTO());
  }

  @Override
  public PostDTO getById(long id) {
    return modelMapper.map(postRepository.findById(id).get(), PostDTO.class);
  }

  @Override
  public void save(PostDTO dto) {
    Post p = new Post();
    p.setTitle(dto.getTitle());
    p.setAuthor(dto.getAuthor());
    p.setContent(dto.getContent());
    var user = userRepository.findById(dto.getId_user()).get();
    p.setUser(user);
    postRepository.save(p);
  }

  @Override
  public void delete(long id) {
    postRepository.deleteById(id);
  }

  @Override
  public void update(PostDTO p, long id) {
    Post post = postRepository.findById(id).get();
    post.setAuthor(p.getAuthor());
    post.setContent(p.getContent());
    postRepository.save(post);
  }

  @Override
  public List<PostDTO> filterByAuthor(String author) {
    var posts = postRepository.findAllByAuthor(author);
    return (List<PostDTO>) listModelMapper.mapList(posts, new PostDTO());

  }

  @Override
  public List<PostDTO> filterByTitle(String title) {
    List<Post> posts = postRepository.findAllByTitle(title);
    return (List<PostDTO>) listModelMapper.mapList(posts, new PostDTO());
  }

  @Override
  public List<UserDTO> findUserWithPostOfSpecificTitle(String title) {
    List<User> users = postRepository.findUserWithPostOfSpecificTitle(title);
    return users.stream()
        .map(user -> modelMapper.map(user, UserDTO.class))
        .collect(Collectors.toList());
  }
}
