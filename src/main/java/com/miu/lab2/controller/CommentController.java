package com.miu.lab2.controller;

import com.miu.lab2.domain.dto.CommentDTO;
import com.miu.lab2.service.CommentService;
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
@RequestMapping("/api/v1/comments")
public class CommentController {

  private final CommentService commentService;

  @Autowired
  public CommentController(CommentService commentService) {
    this.commentService = commentService;
  }

  @ResponseStatus(HttpStatus.OK)
  @GetMapping
  public List<CommentDTO> getAll() {
    return commentService.findAll();
  }

  @ResponseStatus(HttpStatus.CREATED)
  @PostMapping
  public void save(@RequestBody CommentDTO p) {
    commentService.save(p);
  }

  @GetMapping("/{id}")
  public ResponseEntity<CommentDTO> getById(@PathVariable int id) {
    var commentDTO = commentService.getById(id);
    return ResponseEntity.ok(commentDTO);
  }

  @ResponseStatus(HttpStatus.NO_CONTENT)
  @DeleteMapping("/{id}")
  public void delete(@PathVariable int id) {
    commentService.delete(id);
  }
}

