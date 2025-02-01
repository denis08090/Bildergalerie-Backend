package com.example.Bildergalerie.security;

import com.example.Bildergalerie.security.helpers.JwtProperties;
import com.example.Bildergalerie.model.user.UserService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

/**
 * Konfigurationsklasse für die Sicherheitseinstellungen der Anwendung.
 * Enthält die Konfiguration für HTTP-Sicherheitsfilter, Authentifizierung und Autorisierung.
 */
@Configuration
@EnableMethodSecurity // Aktiviert methodenbasierte Sicherheitskontrollen
@EnableJpaAuditing(auditorAwareRef = "userAware") // Aktiviert JPA-Auditing mit benutzerbezogener Überwachung
public class WebSecurityConfig {

  private final UserService userService; // Service zur Benutzerverwaltung
  private final BCryptPasswordEncoder bCryptPasswordEncoder; // Passwortverschlüsselung
  private final JwtProperties jwtProperties; // JWT-Konfigurationseigenschaften

  /**
   * Konstruktor für die Sicherheitskonfiguration.
   *
   * @param userService           Der Benutzer-Service für Authentifizierung.
   * @param bCryptPasswordEncoder Der Passwort-Encoder zur sicheren Speicherung von Passwörtern.
   * @param jwtProperties         JWT-spezifische Konfigurationseigenschaften.
   */
  @Autowired
  public WebSecurityConfig(UserService userService, BCryptPasswordEncoder bCryptPasswordEncoder,
                           JwtProperties jwtProperties) {
    this.userService = userService;
    this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    this.jwtProperties = jwtProperties;
  }

  /**
   * Konfiguriert die Sicherheitsfilterkette für HTTP-Anfragen.
   *
   * @param http Das HttpSecurity-Objekt zur Konfiguration der Sicherheitsrichtlinien.
   * @return Die konfigurierte Sicherheitsfilterkette.
   * @throws Exception Falls eine Konfigurationsfehler auftritt.
   */
  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    return http
            .authorizeHttpRequests(requests -> requests
                    .antMatchers(HttpMethod.POST, "/users/login").permitAll() // Erlaubt Login für alle
                    .antMatchers(HttpMethod.POST, "/users/register").permitAll() // Erlaubt Registrierung für alle
                    .antMatchers("/albums/**").hasAnyAuthority("CAN_CREATE_ALBUM", "CAN_WATCH_ALBUM") // Autoritätsprüfung
                    .anyRequest().authenticated()) // Alle anderen Anfragen erfordern Authentifizierung
            .addFilterAfter(
                    new CustomAuthenticationFilter(new AntPathRequestMatcher("/users/login", "POST"),
                            authenticationManager(), jwtProperties),
                    UsernamePasswordAuthenticationFilter.class) // Fügt den Authentifizierungsfilter hinzu
            .addFilterAfter(new CustomAuthorizationFilter(userService, jwtProperties),
                    UsernamePasswordAuthenticationFilter.class) // Fügt den Autorisierungsfilter hinzu
            .sessionManagement(mgmt -> mgmt.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) // Setzt Sitzung auf "stateless" (kein Session-Tracking)
            .cors(cors -> cors.configurationSource(corsConfigurationSource())) // Aktiviert CORS-Konfiguration
            .csrf(AbstractHttpConfigurer::disable) // Deaktiviert CSRF-Schutz (da JWT verwendet wird)
            .build();
  }

  /**
   * Konfiguriert die CORS-Regeln für eingehende Anfragen.
   *
   * @return Die konfigurierte CORS-Quelle.
   */
  @Bean
  public CorsConfigurationSource corsConfigurationSource() {
    CorsConfiguration configuration = new CorsConfiguration();
    configuration.setAllowedOrigins(List.of("*")); // Erlaubt Anfragen von allen Ursprüngen
    configuration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE")); // Erlaubte HTTP-Methoden
    configuration.setAllowedHeaders(List.of("Authorization", "Cache-Control", "Content-Type")); // Erlaubte Header
    configuration.setExposedHeaders(List.of("Authorization")); // Header, die im Client sichtbar sind

    UrlBasedCorsConfigurationSource configurationSource = new UrlBasedCorsConfigurationSource();
    configurationSource.registerCorsConfiguration("/**", configuration); // Gilt für alle Endpunkte

    return configurationSource;
  }

  /**
   * Konfiguriert den AuthenticationManager für die Benutzeranmeldung.
   *
   * @return Ein AuthenticationManager für die Authentifizierung der Benutzer.
   */
  @Bean
  public AuthenticationManager authenticationManager() {
    DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
    provider.setPasswordEncoder(bCryptPasswordEncoder); // Setzt den Passwort-Encoder
    provider.setUserDetailsService(userService); // Setzt den Benutzer-Service für Authentifizierung
    return new ProviderManager(provider);
  }
}
