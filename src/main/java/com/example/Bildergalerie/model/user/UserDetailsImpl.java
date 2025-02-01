package com.example.Bildergalerie.model.user;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.stream.Collectors;

/**
 * **Implementierung von `UserDetails` für Spring Security**
 *
 * - Diese Klasse dient als Adapter zwischen `User`-Entitäten und Spring Securitys `UserDetails`.
 * - Sie wandelt Rollen (`Role`) in Berechtigungen (`GrantedAuthority`) um.
 * - Die Klasse wird als `record` implementiert, da sie nur Daten enthält und keine Zustandsänderungen benötigt.
 *
 * @version 1.0
 * @since 2024-07-26
 */
public record UserDetailsImpl(User user) implements UserDetails {

  /**
   * **Gibt die Berechtigungen (Authorities) des Benutzers zurück.**
   *
   * - Jeder Benutzer hat eine oder mehrere Rollen (`Role`).
   * - Jede Rolle kann eine oder mehrere Berechtigungen (`Authority`) besitzen.
   * - Diese Methode konvertiert Rollen in `GrantedAuthority`, das Spring Security benötigt.
   *
   * @return Eine `Collection` von `GrantedAuthority`-Objekten für den Benutzer.
   * @throws IllegalStateException Falls der Benutzer keine Rollen oder eine Rolle keine Berechtigungen hat.
   */
  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    if (user.getRoles() == null) {
      throw new IllegalStateException("❌ Fehler: Der Benutzer hat keine Rollen zugewiesen!");
    }
    return user.getRoles().stream()
            .flatMap(role -> {
              if (role.getAuthorities() == null) {
                throw new IllegalStateException("❌ Fehler: Die Rolle '" + role.getName() + "' hat keine Berechtigungen!");
              }
              return role.getAuthorities().stream();
            })
            .map(authority -> new SimpleGrantedAuthority(authority.getName()))
            .collect(Collectors.toList());
  }

  /**
   * **Gibt das verschlüsselte Passwort des Benutzers zurück.**
   *
   * - Spring Security verwendet diese Methode, um das gespeicherte Passwort zu vergleichen.
   *
   * @return Das verschlüsselte Passwort.
   * @throws IllegalStateException Falls das Passwort `null` ist.
   */
  @Override
  public String getPassword() {
    if (user.getPassword() == null) {
      throw new IllegalStateException("❌ Fehler: Das Passwort des Benutzers ist null!");
    }
    return user.getPassword();
  }

  /**
   * **Gibt den Benutzernamen (E-Mail) zurück.**
   *
   * - In dieser Implementierung dient die E-Mail als eindeutige Benutzerkennung.
   *
   * @return Die E-Mail-Adresse des Benutzers.
   * @throws IllegalStateException Falls die E-Mail `null` ist.
   */
  @Override
  public String getUsername() {
    if (user.getEmail() == null) {
      throw new IllegalStateException("❌ Fehler: Die E-Mail des Benutzers ist null!");
    }
    return user.getEmail();
  }

  /**
   * **Bestimmt, ob das Benutzerkonto abgelaufen ist.**
   *
   * - Falls `false`, kann sich der Benutzer nicht anmelden.
   * - Standardmäßig ist diese Methode auf `true`, um alle Konten als nicht abgelaufen zu betrachten.
   *
   * @return `true`, da Konten standardmäßig nicht ablaufen.
   */
  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  /**
   * **Bestimmt, ob das Benutzerkonto gesperrt ist.**
   *
   * - Falls `false`, kann sich der Benutzer nicht anmelden.
   * - Standardmäßig ist diese Methode auf `true`, da keine Sperrlogik implementiert wurde.
   *
   * @return `true`, da Konten standardmäßig nicht gesperrt sind.
   */
  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  /**
   * **Bestimmt, ob die Anmeldeinformationen (Passwort) abgelaufen sind.**
   *
   * - Falls `false`, kann sich der Benutzer nicht anmelden.
   * - Standardmäßig ist diese Methode auf `true`, da keine Ablauf-Logik implementiert wurde.
   *
   * @return `true`, da Passwörter standardmäßig nicht ablaufen.
   */
  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  /**
   * **Bestimmt, ob das Benutzerkonto aktiviert ist.**
   *
   * - Falls `false`, kann sich der Benutzer nicht anmelden.
   * - Standardmäßig ist diese Methode auf `true`, d.h. alle Benutzer sind aktiviert.
   *
   * @return `true`, da Benutzer standardmäßig aktiviert sind.
   */
  @Override
  public boolean isEnabled() {
    return true;
  }
}
