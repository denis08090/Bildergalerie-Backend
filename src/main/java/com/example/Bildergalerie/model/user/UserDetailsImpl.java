package com.example.Bildergalerie.model.user;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.stream.Collectors;

/**
 * **Implementierung von `UserDetails` für die Spring Security Authentifizierung.**
 *
 * Diese Klasse stellt sicher, dass die `User`-Entität mit Spring Security kompatibel ist.
 * Sie wird von Spring Security verwendet, um Benutzerinformationen während der Authentifizierung zu verarbeiten.
 *
 * **Wichtige Eigenschaften:**
 * - **`UserDetailsImpl` ist ein `record`**: `record`-Klassen sind unveränderlich und enthalten standardmäßig `equals()`, `hashCode()` und `toString()`.
 * - **Spring Security Schnittstelle `UserDetails`**: Wird benötigt, um einen Benutzer in Spring Security zu authentifizieren.
 * - **Konvertierung von `Role` und `Authority` in `GrantedAuthority`**.
 *
 * @version 1.0
 * @since 2024-07-26
 */
public record UserDetailsImpl(User user) implements UserDetails {

  /**
   * **Gibt die Rollen und Berechtigungen (`Authorities`) des Benutzers zurück.**
   *
   * - Jede **Rolle (`Role`)** enthält eine Liste von **Berechtigungen (`Authority`)**.
   * - Spring Security benötigt eine Liste von `GrantedAuthority`, daher wird jede `Authority` in `SimpleGrantedAuthority` umgewandelt.
   *
   * @return Eine `Collection` von `GrantedAuthority`-Objekten für Spring Security.
   * @throws IllegalStateException Falls der Benutzer keine Rollen oder eine Rolle keine Berechtigungen hat.
   */
  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    if (user.getRoles() == null) {
      throw new IllegalStateException("User has no roles assigned!");
    }
    return user.getRoles().stream()
            .flatMap(r -> {
              if (r.getAuthorities() == null) {
                throw new IllegalStateException("Role has no authorities!");
              }
              return r.getAuthorities().stream();
            })
            .map(a -> new SimpleGrantedAuthority(a.getName())) // Konvertiert `Authority` in `SimpleGrantedAuthority`
            .collect(Collectors.toList());
  }

  /**
   * **Gibt das Passwort des Benutzers zurück.**
   *
   * @return Das verschlüsselte Passwort des Benutzers.
   * @throws IllegalStateException Falls das Passwort `null` ist.
   */
  @Override
  public String getPassword() {
    if (user.getPassword() == null) {
      throw new IllegalStateException("User password is null!");
    }
    return user.getPassword();
  }

  /**
   * **Gibt den Benutzernamen zurück, der für die Authentifizierung verwendet wird.**
   *
   * - In diesem Fall wird die **E-Mail-Adresse** als Benutzername verwendet.
   *
   * @return Die E-Mail-Adresse des Benutzers.
   * @throws IllegalStateException Falls die E-Mail `null` ist.
   */
  @Override
  public String getUsername() {
    if (user.getEmail() == null) {
      throw new IllegalStateException("User email is null!");
    }
    return user.getEmail();
  }

  /**
   * **Gibt zurück, ob das Konto nicht abgelaufen ist.**
   *
   * - `true` bedeutet, dass das Konto **nicht abgelaufen** ist.
   * - Falls du eine Ablauf-Logik benötigst, kann hier eine Bedingung implementiert werden.
   *
   * @return `true`, da Benutzerkonten nicht ablaufen.
   */
  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  /**
   * **Gibt zurück, ob das Konto nicht gesperrt ist.**
   *
   * - `true` bedeutet, dass das Konto **nicht gesperrt** ist.
   * - Falls du eine Sperrmechanik implementierst, sollte dies hier überprüft werden.
   *
   * @return `true`, da Benutzer standardmäßig nicht gesperrt sind.
   */
  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  /**
   * **Gibt zurück, ob die Anmeldeinformationen nicht abgelaufen sind.**
   *
   * - `true` bedeutet, dass die Anmeldeinformationen **nicht abgelaufen** sind.
   * - Falls du eine Passwortablauf-Logik implementierst, kann das hier überprüft werden.
   *
   * @return `true`, da Passwörter standardmäßig nicht ablaufen.
   */
  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  /**
   * **Gibt zurück, ob das Konto aktiviert ist.**
   *
   * - `true` bedeutet, dass das Konto **aktiv** ist.
   * - Falls du eine Aktivierungslogik (z. B. E-Mail-Verifizierung) hast, sollte das hier überprüft werden.
   *
   * @return `true`, da Benutzer standardmäßig aktiviert sind.
   */
  @Override
  public boolean isEnabled() {
    return true;
  }
}
