package com.miu.lab2.controller;

import com.miu.lab2.domain.dto.PostDTO;
import com.miu.lab2.service.PostService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/posts")
public class PostController {

  private final PostService postService;

  @Autowired
  public PostController(PostService postService) {
    this.postService = postService;
  }

  @ResponseStatus(HttpStatus.OK)
  @GetMapping
  public List<PostDTO> getAll() {
    return postService.findAll();
  }

  @ResponseStatus(HttpStatus.CREATED)
  @PostMapping
  public void save(@RequestBody PostDTO p) {
    postService.save(p);
  }

  @GetMapping("/{id}")
  public ResponseEntity<PostDTO> getById(@PathVariable int id) {
    var product = postService.getById(id);
    return ResponseEntity.ok(product);
  }

  @ResponseStatus(HttpStatus.NO_CONTENT)
  @DeleteMapping("/{id}")
  public void delete(@PathVariable int id) {
    postService.delete(id);
  }

  @PutMapping("/{id}")
  public void update(@PathVariable("id") long postId, @RequestBody PostDTO p) {
    postService.update(p, postId);
  }

  @GetMapping("/author/{authorName}")
  public ResponseEntity<List<PostDTO>> getPostByAuthors(@PathVariable String authorName) {
    return ResponseEntity.ok(postService.filterByAuthor(authorName));
  }
}
