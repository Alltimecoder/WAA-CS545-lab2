package com.miu.lab2.service.impl;

import com.miu.lab2.Util.ListModelMapper;
import com.miu.lab2.domain.Comment;
import com.miu.lab2.domain.Post;
import com.miu.lab2.domain.dto.CommentDTO;
import com.miu.lab2.repository.CommentRepository;
import com.miu.lab2.repository.PostRepository;
import com.miu.lab2.service.CommentService;
import java.util.List;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements CommentService {

  private final CommentRepository commentRepository;
  private final ListModelMapper<Comment, CommentDTO> listModelMapper;
  private final ModelMapper modelMapper;
  private final PostRepository postRepository;

  @Autowired
  public CommentServiceImpl(CommentRepository commentRepository,
      ListModelMapper<Comment, CommentDTO> listModelMapper, ModelMapper modelMapper,
      PostRepository postRepository) {
    this.commentRepository = commentRepository;
    this.listModelMapper = listModelMapper;
    this.modelMapper = modelMapper;
    this.postRepository = postRepository;
  }

  @Override
  public List<CommentDTO> findAll() {
    List<Comment> comments = (List<Comment>) commentRepository.findAll();
    return (List<CommentDTO>) listModelMapper.mapList(comments, new CommentDTO());
  }

  @Override
  public CommentDTO getById(long id) {
    Comment comment = commentRepository.findById(id).get();
    return modelMapper.map(comment, CommentDTO.class);
  }

  @Override
  public void save(CommentDTO dto) {
    Comment comment = new Comment();
    comment.setName(dto.getName());
    Post post = postRepository.findById(dto.getId_post()).get();
    comment.setPost(post);
    commentRepository.save(comment);
  }

  @Override
  public void delete(long id) {
    commentRepository.deleteById(id);
  }
}
