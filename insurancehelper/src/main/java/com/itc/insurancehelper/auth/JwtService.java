package com.itc.insurancehelper.auth;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;

// Service class for generating and validating JWT tokens
// using the jjwt library
// The secret key and token expiration time are injected from application properties
// The generateToken method creates a JWT for a given username
// The extractUsername method retrieves the username from a given JWT
// The tokens are signed using the HS256 algorithm

@Service
public class JwtService {
  private final Key key;
  private final long expirationMillis;

  public JwtService(@Value("${jwt.secret}") String secret,
                    @Value("${jwt.expirationMillis}") long expirationMillis) {
    this.key = Keys.hmacShaKeyFor(secret.getBytes());
    this.expirationMillis = expirationMillis;
  }

  public String generateToken(String username) {
    long now = System.currentTimeMillis();
    return Jwts.builder()
        .setSubject(username)
        .setIssuedAt(new Date(now))
        .setExpiration(new Date(now + expirationMillis))
        .signWith(key, SignatureAlgorithm.HS256)
        .compact();
  }

  public String extractUsername(String token) {
    return Jwts.parserBuilder().setSigningKey(key).build()
        .parseClaimsJws(token).getBody().getSubject();
  }
}
