package com.miu.lab2.service.impl;

import com.miu.lab2.Util.JwtUtil;
import com.miu.lab2.domain.dto.request.LoginRequest;
import com.miu.lab2.domain.dto.request.RefreshTokenRequest;
import com.miu.lab2.domain.dto.response.LoginResponse;
import com.miu.lab2.service.AuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthServiceImpl implements AuthService {

  private final AuthenticationManager authenticationManager;
  private final UserDetailsService userDetailsService;
  private final JwtUtil jwtUtil;

  @Override
  public LoginResponse login(LoginRequest loginRequest) {
    try {
      var result = authenticationManager.authenticate(
          new UsernamePasswordAuthenticationToken(loginRequest.getEmail(),
              loginRequest.getPassword())
      );
    } catch (BadCredentialsException e) {
      log.info("Bad Credentials");
    }

    final UserDetails userDetails = userDetailsService
        .loadUserByUsername(loginRequest.getEmail());

    final String accessToken = jwtUtil.generateToken(userDetails);
    final String refreshToken = jwtUtil.generateRefreshToken(loginRequest.getEmail());
    return new LoginResponse(accessToken, refreshToken);
  }

  @Override
  public LoginResponse refreshToken(RefreshTokenRequest refreshTokenRequest) {
    boolean isRefreshTokenValid = jwtUtil.validateToken(refreshTokenRequest.getRefreshToken());
    if (isRefreshTokenValid) {
      final String accessToken = jwtUtil.doGenerateToken(jwtUtil.getSubject(refreshTokenRequest.getRefreshToken()));
      return new LoginResponse(accessToken, refreshTokenRequest.getRefreshToken());
    }
    return new LoginResponse();
  }
}
