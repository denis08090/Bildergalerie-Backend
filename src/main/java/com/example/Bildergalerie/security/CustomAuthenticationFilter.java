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

/**
 * Custom Authentication Filter für JWT-Authentifizierung.
 * Dieser Filter verarbeitet Authentifizierungsanfragen, validiert Benutzeranmeldeinformationen
 * und generiert bei erfolgreicher Anmeldung ein JWT-Token.
 */
public class CustomAuthenticationFilter extends AbstractAuthenticationProcessingFilter {

  private final JwtProperties jwtProperties; // Konfigurationswerte für JWT

  /**
   * Konstruktor für den Authentifizierungsfilter.
   *
   * @param requestMatcher         Der Request-Matcher zur Identifikation von Authentifizierungsanfragen.
   * @param authenticationManager  Der AuthenticationManager zur Benutzerüberprüfung.
   * @param jwtProperties          Die JWT-Eigenschaften für Token-Erstellung und Validierung.
   */
  public CustomAuthenticationFilter(RequestMatcher requestMatcher,
                                    AuthenticationManager authenticationManager, JwtProperties jwtProperties) {
    super(requestMatcher, authenticationManager);
    this.jwtProperties = jwtProperties;
  }

  /**
   * Erstellt ein JWT-Token für den authentifizierten Benutzer.
   *
   * @param authResult Das Authentifizierungsergebnis.
   * @return Ein signiertes JWT-Token.
   */
  private String generateToken(Authentication authResult) {
    UserDetailsImpl userDetailsImpl = (UserDetailsImpl) authResult.getPrincipal();

    // Debugging: Überprüfung, ob der Secret-Key vorhanden ist
    String secret = jwtProperties.getSecret();
    if (secret == null || secret.isBlank()) {
      throw new IllegalArgumentException("JWT Secret Key is null or empty!");
    }

    byte[] keyBytes = Decoders.BASE64.decode(secret);

    return Jwts.builder()
            .setClaims(Map.of(
                    "sub", userDetailsImpl.user().getId().toString(), // Benutzer-ID als "sub" (Subject)
                    "authorities", userDetailsImpl.getAuthorities().stream()
                            .map(GrantedAuthority::getAuthority) // Rollen in Strings konvertieren
                            .collect(Collectors.toList())
            ))
            .setIssuedAt(new Date()) // Token-Erstellungszeitpunkt
            .setExpiration(new Date(System.currentTimeMillis() + jwtProperties.getExpirationMillis())) // Ablaufzeit
            .setIssuer(jwtProperties.getIssuer()) // Herausgeber des Tokens
            .signWith(Keys.hmacShaKeyFor(keyBytes)) // Signierung mit dem geheimen Schlüssel
            .compact();
  }

  /**
   * Versucht, eine Authentifizierung basierend auf den übergebenen Anmeldeinformationen durchzuführen.
   *
   * @param request  Die HTTP-Anfrage, die die Anmeldeinformationen enthält.
   * @param response Die HTTP-Antwort.
   * @return Ein Authentication-Objekt, falls die Anmeldung erfolgreich war.
   * @throws AuthenticationException Falls die Authentifizierung fehlschlägt.
   * @throws IOException Falls ein Fehler beim Lesen der Anfrage auftritt.
   */
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

  /**
   * Verarbeitung einer erfolgreichen Authentifizierung.
   * Fügt das JWT-Token zur Antwort hinzu.
   *
   * @param request      Die HTTP-Anfrage.
   * @param response     Die HTTP-Antwort, in die das JWT-Token eingefügt wird.
   * @param chain        Die Filterkette.
   * @param authResult   Das Authentifizierungsergebnis.
   */
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

  /**
   * Verarbeitung einer fehlgeschlagenen Authentifizierung.
   * Setzt den HTTP-Status auf 401 (Unauthorized) und löscht den SecurityContext.
   *
   * @param request  Die HTTP-Anfrage.
   * @param response Die HTTP-Antwort.
   * @param failed   Die Exception, die den Fehler beschreibt.
   */
  @Override
  protected void unsuccessfulAuthentication(HttpServletRequest request,
                                            HttpServletResponse response, AuthenticationException failed) {
    SecurityContextHolder.clearContext();
    response.setStatus(HttpStatus.UNAUTHORIZED.value());
  }
}
