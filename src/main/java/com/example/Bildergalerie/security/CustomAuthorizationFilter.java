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

/**
 * Custom Authorization Filter für die JWT-Validierung.
 * Dieser Filter überprüft jede eingehende Anfrage auf ein gültiges JWT-Token.
 * Falls das Token gültig ist, wird der Benutzer im SecurityContext gespeichert.
 */
public class CustomAuthorizationFilter extends OncePerRequestFilter {

  private final UserService userService; // Service für die Benutzerverwaltung
  private final JwtProperties jwtProperties; // Konfigurationseigenschaften für JWT

  /**
   * Konstruktor für den Authorization Filter.
   *
   * @param userService   Service zur Benutzerverwaltung.
   * @param jwtProperties JWT-Konfigurationswerte.
   */
  public CustomAuthorizationFilter(UserService userService, JwtProperties jwtProperties) {
    this.userService = userService;
    this.jwtProperties = jwtProperties;
  }

  /**
   * Extrahiert und validiert das JWT-Token aus dem Authorization-Header.
   *
   * @param token Der übergebene Authorization-Header.
   * @return Die Benutzer-ID (Subject) aus dem Token oder null, falls das Token ungültig ist.
   */
  private String resolveToken(String token) {
    if (token != null && token.startsWith(AuthorizationSchemas.BEARER.toString())) {
      byte[] keyBytes = Decoders.BASE64.decode(jwtProperties.getSecret());

      return Jwts.parserBuilder() // Verwende parserBuilder anstelle von parser (veraltet)
              .setSigningKey(Keys.hmacShaKeyFor(keyBytes)) // Schlüssel setzen
              .build()
              .parseClaimsJws(token.replace(AuthorizationSchemas.BEARER + " ", "")) // Token validieren
              .getBody()
              .getSubject(); // Extrahiere den 'sub'-Claim (Benutzer-ID)
    } else {
      return null;
    }
  }

  /**
   * Filtert eingehende HTTP-Anfragen, um die JWT-Authentifizierung zu prüfen.
   *
   * @param request     Die HTTP-Anfrage.
   * @param response    Die HTTP-Antwort.
   * @param filterChain Die Filterkette für die Weiterverarbeitung der Anfrage.
   * @throws ServletException Falls eine Servlet-Fehlermeldung auftritt.
   * @throws IOException Falls ein Fehler beim Verarbeiten der Anfrage auftritt.
   */
  @Override
  protected void doFilterInternal(javax.servlet.http.HttpServletRequest request,
                                  javax.servlet.http.HttpServletResponse response,
                                  javax.servlet.FilterChain filterChain)
          throws javax.servlet.ServletException, IOException {
    try {
      // Extrahiere das Token aus dem Authorization-Header
      String authToken = request.getHeader(HttpHeaders.AUTHORIZATION);
      String resolvedToken = resolveToken(authToken);

      // Falls ein gültiges Token vorhanden ist, authentifiziere den Benutzer
      if (resolvedToken != null) {
        UserDetails userDetails = new UserDetailsImpl(
                userService.findById(UUID.fromString(resolvedToken)));

        // Setze die Authentifizierung im SecurityContext
        SecurityContextHolder.getContext().setAuthentication(
                new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities()));
      }
    } catch (Exception e) {
      // Falls ein Fehler auftritt (z. B. ungültiges Token), leere den SecurityContext
      SecurityContextHolder.clearContext();
    }

    // Weitergabe der Anfrage an die nächste Filterstufe
    filterChain.doFilter(request, response);
  }
}
