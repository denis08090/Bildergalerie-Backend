package com.example.Bildergalerie.security;

import com.example.Bildergalerie.model.user.UserDetailsImpl;
import com.example.Bildergalerie.security.helpers.AuthorizationSchemas;
import com.example.Bildergalerie.security.helpers.Credentials;
import com.example.Bildergalerie.security.helpers.JwtProperties;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.security.core.GrantedAuthority;


import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.RequestMatcher;


public class CustomAuthenticationFilter extends AbstractAuthenticationProcessingFilter {

  private final JwtProperties jwtProperties;

  public CustomAuthenticationFilter(RequestMatcher requestMatcher,
      AuthenticationManager authenticationManager, JwtProperties jwtProperties) {
    super(requestMatcher, authenticationManager);
    this.jwtProperties = jwtProperties;
  }

  private String generateToken(Authentication authResult) {
    UserDetailsImpl userDetailsImpl = (UserDetailsImpl) authResult.getPrincipal();

    // Debugging hinzufügen
    String secret = jwtProperties.getSecret();
    if (secret == null || secret.isBlank()) {
      throw new IllegalArgumentException("JWT Secret Key is null or empty!");
    }

    byte[] keyBytes = Decoders.BASE64.decode(secret);

    return Jwts.builder()
            .setClaims(Map.of(
                    "sub", userDetailsImpl.user().getId().toString(),
                    "authorities", userDetailsImpl.getAuthorities().stream()
                            .map(GrantedAuthority::getAuthority) // ✅ Konvertiere die Rollen in Strings
                            .collect(Collectors.toList())
            ))
            .setIssuedAt(new Date())
            .setExpiration(new Date(System.currentTimeMillis() + jwtProperties.getExpirationMillis()))
            .setIssuer(jwtProperties.getIssuer())
            .signWith(Keys.hmacShaKeyFor(keyBytes))
            .compact();

  }

  @Override
  public Authentication attemptAuthentication(HttpServletRequest request,
      HttpServletResponse response)
      throws AuthenticationException, IOException {
    Credentials credentials = new ObjectMapper().readValue(request.getInputStream(),
        Credentials.class);
    return getAuthenticationManager()
        .authenticate(new UsernamePasswordAuthenticationToken(credentials.getEmail(),
            credentials.getPassword()));
  }

  @Override
  protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response,
                                          FilterChain chain, Authentication authResult) {
    String token = generateToken(authResult);

    response.addHeader(HttpHeaders.AUTHORIZATION, "Bearer " + token);
    response.setContentType("application/json");
    response.setCharacterEncoding("UTF-8");

    try {
      response.getWriter().write("{\"token\": \"" + token + "\"}");
    } catch (IOException e) {
      e.printStackTrace();
    }
  }


  @Override
  protected void unsuccessfulAuthentication(HttpServletRequest request,
      HttpServletResponse response, AuthenticationException failed) {
    SecurityContextHolder.clearContext();
    response.setStatus(HttpStatus.UNAUTHORIZED.value());
  }
}
