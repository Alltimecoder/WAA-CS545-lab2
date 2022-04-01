package com.miu.lab2.service.impl;

import com.miu.lab2.Util.ListModelMapper;
import com.miu.lab2.domain.Post;
import com.miu.lab2.domain.User;
import com.miu.lab2.domain.dto.PostDTO;
import com.miu.lab2.repository.PostRepository;
import com.miu.lab2.service.PostService;
import com.miu.lab2.service.UserService;
import java.util.List;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PostServiceImpl implements PostService {

  private final PostRepository postRepository;
  private final ModelMapper modelMapper;
  private final UserService userService;
  private final ListModelMapper<Post, PostDTO> listModelMapper;

  @Autowired
  public PostServiceImpl(PostRepository postRepository, ModelMapper modelMapper,
      UserService userService,
      ListModelMapper<Post, PostDTO> listModelMapper) {
    this.postRepository = postRepository;
    this.modelMapper = modelMapper;
    this.userService = userService;
    this.listModelMapper = listModelMapper;
  }

  @Override
  public List<PostDTO> findAll() {
    List<Post> posts = (List<Post>) postRepository.findAll();
    return (List<PostDTO>) listModelMapper.mapList(posts, new PostDTO());
  }

  @Override
  public PostDTO getById(long id) {
    return modelMapper.map(postRepository.findById(id), PostDTO.class);
  }

  @Override
  public void save(PostDTO dto) {
    Post p = new Post();
    p.setTitle(dto.getTitle());
    p.setAuthor(dto.getAuthor());
    p.setContent(dto.getContent());
    var user = userService.getById(dto.getId_user());
    p.setUser(modelMapper.map(user, User.class));
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
    return postRepository.findAllByAuthor(author);
  }
}
