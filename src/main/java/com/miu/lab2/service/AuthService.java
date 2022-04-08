package com.miu.lab2.service;

import com.miu.lab2.domain.dto.request.LoginRequest;
import com.miu.lab2.domain.dto.request.RefreshTokenRequest;
import com.miu.lab2.domain.dto.response.LoginResponse;

public interface AuthService {

  LoginResponse login(LoginRequest loginRequest);

  LoginResponse refreshToken(RefreshTokenRequest refreshTokenRequest);
}
