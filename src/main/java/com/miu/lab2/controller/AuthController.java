package com.miu.lab2.controller;

import com.miu.lab2.domain.dto.request.LoginRequest;
import com.miu.lab2.domain.dto.request.RefreshTokenRequest;
import com.miu.lab2.domain.dto.response.LoginResponse;
import com.miu.lab2.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/authenticate")
@CrossOrigin
public class AuthController {

  private final AuthService authService;

  public AuthController(AuthService authService) {
    this.authService = authService;
  }

  @PostMapping
  public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
    var loginResponse = authService.login(loginRequest);
    return ResponseEntity.ok().body(loginResponse);
  }

  @PostMapping("/refreshToken")
  public LoginResponse refreshToken(@RequestBody RefreshTokenRequest refreshTokenRequest) {
    return authService.refreshToken(refreshTokenRequest);
  }

}
