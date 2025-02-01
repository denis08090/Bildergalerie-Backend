package com.example.Bildergalerie.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * Konfigurationsklasse für die Passworte-Verschlüsselung.
 * Diese Klasse stellt einen Bean für den BCryptPasswordEncoder bereit,
 * der für die sichere Hashing- und Verifizierungsfunktion von Passwörtern verwendet wird.
 */
@Configuration
public class Encoders {

  /**
   * Erstellt und konfiguriert einen BCryptPasswordEncoder-Bean.
   * Der Encoder wird in der gesamten Anwendung verwendet, um Passwörter sicher zu hashen.
   *
   * @return eine Instanz von BCryptPasswordEncoder.
   */
  @Bean
  public BCryptPasswordEncoder bCryptPasswordEncoder() {
    return new BCryptPasswordEncoder();
  }
}
