package com.example.Bildergalerie.security.helpers;

import javax.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "jwt")
public class JwtProperties {

  private static final Logger LOGGER = LoggerFactory.getLogger(JwtProperties.class);

  private long expirationMillis;
  private String issuer;
  private String secret;

  @PostConstruct
  public void logJwtSecret() {
    LOGGER.info("✅ Loaded JWT Secret: {}", secret);
  }

  public long getExpirationMillis() {
    return expirationMillis;
  }

  public void setExpirationMillis(long expirationMillis) {
    this.expirationMillis = expirationMillis;
  }

  public String getIssuer() {
    return issuer;
  }

  public void setIssuer(String issuer) {
    this.issuer = issuer;
  }

  public String getSecret() {
    return secret;
  }

  public void setSecret(String secret) {
    this.secret = secret;
  }
}
