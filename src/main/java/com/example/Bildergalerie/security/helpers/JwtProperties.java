package com.example.Bildergalerie.security.helpers;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.util.Base64;

@Component
@ConfigurationProperties(prefix = "jwt")
public class JwtProperties {

  private long expirationMillis = 86400000; // Standard: 1 Tag (24h)
  private String issuer = "your_application";
  private String secret;

  public long getExpirationMillis() {
    return expirationMillis;
  }

  public JwtProperties setExpirationMillis(long expirationMillis) {
    this.expirationMillis = expirationMillis;
    return this;
  }

  public String getIssuer() {
    return issuer;
  }

  public JwtProperties setIssuer(String issuer) {
    this.issuer = issuer;
    return this;
  }

  public String getSecret() {
    if (secret == null || secret.isBlank()) {
      secret = generateSecretKey();
      System.out.println("⚠️ Kein JWT Secret gefunden! Neuer Secret Key wurde generiert: " + secret);
    }
    return secret;
  }

  public JwtProperties setSecret(String secret) {
    this.secret = secret;
    return this;
  }

  private String generateSecretKey() {
    try {
      KeyGenerator keyGen = KeyGenerator.getInstance("HmacSHA256");
      keyGen.init(256);
      SecretKey key = keyGen.generateKey();
      return Base64.getEncoder().encodeToString(key.getEncoded());
    } catch (Exception e) {
      throw new RuntimeException("Fehler beim Generieren des Secret Keys", e);
    }
  }
}
