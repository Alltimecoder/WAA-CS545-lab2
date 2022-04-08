package com.miu.lab2.Util;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.Keys;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
public class JwtUtil {

  private final String secret = "n2r5u8x/A%D*G-KaPdSgVkYp3s6v9y$B&E(H+MbQeThWmZq4t7w!z%C*F-J@NcRf";
  private final long expiration = 2 * 60 * 60 * 60;
  private final long refreshTokenExpiration = 5 * 60 * 60 * 60;
  // private final long expiration = 5;

  public String generateToken(UserDetails userDetails) {
    Map<String, Object> claims = new HashMap<>();
    return doGenerateToken(claims, userDetails.getUsername());
  }


  public String doGenerateToken(Map<String, Object> claims, String subject) {
    return Jwts.builder()
        .setClaims(claims)
        .setSubject(subject)
        .setIssuedAt(new Date())
        .setExpiration(new Date(System.currentTimeMillis() + expiration))
        .signWith(Keys.hmacShaKeyFor(secret.getBytes()), SignatureAlgorithm.HS512)
        .compact();
  }

  // Overridden to accommodate the refresh token
  public String doGenerateToken(String subject) {
    return Jwts.builder()
        .setSubject(subject)
        .setIssuedAt(new Date())
        .setExpiration(new Date(System.currentTimeMillis() + expiration))
        .signWith(Keys.hmacShaKeyFor(secret.getBytes()), SignatureAlgorithm.HS512)
        .compact();
  }

  public String generateRefreshToken(String email) {
    return Jwts.builder()
        .setSubject(email)
        .setIssuedAt(new Date())
        .setExpiration(new Date(System.currentTimeMillis() + refreshTokenExpiration))
        .signWith(Keys.hmacShaKeyFor(secret.getBytes()), SignatureAlgorithm.HS512)
        .compact();
  }

  public String getSubject(String token) {
    return Jwts.parser()
        .setSigningKey(Keys.hmacShaKeyFor(secret.getBytes()))
        .parseClaimsJws(token)
        .getBody()
        .getSubject();
  }

  public boolean validateToken(String token) {
    try {
      Jwts.parser()
          .setSigningKey(Keys.hmacShaKeyFor(secret.getBytes()))
          .parseClaimsJws(token);
      return true;
    } catch (SignatureException e) {
      System.out.println(e.getMessage());
    } catch (MalformedJwtException e) {
      System.out.println(e.getMessage());
    } catch (ExpiredJwtException e) {
      System.out.println(e.getMessage());
    } catch (UnsupportedJwtException e) {
      System.out.println(e.getMessage());
    } catch (IllegalArgumentException e) {
      System.out.println(e.getMessage());
    }
    return false;
  }

  public String getUsernameFromToken(String token) {
    String result = null;
    try {
      result = Jwts.parser()
          .setSigningKey(Keys.hmacShaKeyFor(secret.getBytes()))
          .parseClaimsJws(token)
          .getBody()
          .getSubject();
    } catch (ExpiredJwtException e) {
      System.out.println(e.getMessage());
      throw e;
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
    return result;
  }
}
