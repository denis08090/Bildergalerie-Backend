package com.example.Bildergalerie.security;

import com.example.Bildergalerie.model.user.UserDetailsImpl;
import com.example.Bildergalerie.security.helpers.AuthorizationSchemas;
import com.example.Bildergalerie.security.helpers.JwtProperties;
import com.example.Bildergalerie.model.user.User;
import com.example.Bildergalerie.model.user.UserService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.filter.OncePerRequestFilter;







public class CustomAuthorizationFilter extends OncePerRequestFilter {

  private final UserService userService;
  private final JwtProperties jwtProperties;

  public CustomAuthorizationFilter(UserService userService, JwtProperties jwtProperties) {
    this.userService = userService;
    this.jwtProperties = jwtProperties;
  }

  private String resolveToken(String token) {
    if (token != null && token.startsWith(AuthorizationSchemas.BEARER.toString())) {
      byte[] keyBytes = Decoders.BASE64.decode(jwtProperties.getSecret());
      return Jwts.parserBuilder() // Verwende parserBuilder anstelle von parser
              .setSigningKey(Keys.hmacShaKeyFor(keyBytes)) // Schlüssel setzen
              .build()
              .parseClaimsJws(token.replace(AuthorizationSchemas.BEARER + " ", "")) // Token validieren
              .getBody()
              .getSubject(); // 'sub' Claim auslesen
    } else {
      return null;
    }
  }


  @Override
  protected void doFilterInternal(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response, javax.servlet.FilterChain filterChain) throws javax.servlet.ServletException, IOException {
      try {
        String authToken = request.getHeader(HttpHeaders.AUTHORIZATION);
        String resolvedToken = resolveToken(authToken);

        if (resolvedToken != null) {
          UserDetails userDetails = new UserDetailsImpl(
                  userService.findById(UUID.fromString(resolvedToken)));
          SecurityContextHolder.getContext().setAuthentication(
                  new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities()));
        }
      } catch (Exception e) {
        SecurityContextHolder.clearContext();
      }
      filterChain.doFilter(request, response);
    }
  }

