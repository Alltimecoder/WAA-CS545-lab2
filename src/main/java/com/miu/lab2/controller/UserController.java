package com.miu.lab2.controller;

import com.miu.lab2.domain.dto.UserDTO;
import com.miu.lab2.service.UserService;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

  private final UserService userService;

  @Autowired
  public UserController(UserService userService) {
    this.userService = userService;
  }

  @ResponseStatus(HttpStatus.OK)
  @GetMapping
  public List<UserDTO> getAll() {
    return userService.findAll();
  }

  @ResponseStatus(HttpStatus.CREATED)
  @PostMapping
  public void save(@RequestBody UserDTO p) {
    userService.save(p);
  }

  @GetMapping("/{id}")
  public ResponseEntity<UserDTO> getById(@PathVariable int id) {
    var userDTO = userService.getById(id);
    return ResponseEntity.ok(userDTO);
  }

  @ResponseStatus(HttpStatus.NO_CONTENT)
  @DeleteMapping("/{id}")
  public void delete(@PathVariable int id) {
    userService.delete(id);
  }

  @GetMapping("/filter")
  public ResponseEntity<List<UserDTO>> getUserWithMoreThanOnePosts(@RequestParam int postMoreThan) {
    return ResponseEntity.ok(userService.userWithMoreThanOnePost(postMoreThan));
  }
}
