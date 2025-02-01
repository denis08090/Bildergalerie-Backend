package com.example.Bildergalerie.model.user;

import com.example.Bildergalerie.generic.ExtendedServiceImpl;
import com.example.Bildergalerie.model.role.Role;
import com.example.Bildergalerie.model.role.RoleService;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

/**
 * **Implementierung des `UserService` für die Benutzerverwaltung.**
 *
 * Diese Klasse erweitert `ExtendedServiceImpl<User>` und bietet zusätzliche Funktionen für:
 * - **Registrierung eines neuen Benutzers** (`register()`).
 * - **Laden eines Benutzers für die Authentifizierung** (`loadUserByUsername()`).
 *
 * **Genutzte Abhängigkeiten:**
 * - `UserRepository`: Datenbankzugriff für Benutzer.
 * - `RoleService`: Zugriff auf Rollen (z. B. `CLIENT`).
 * - `BCryptPasswordEncoder`: Verschlüsselung des Benutzerpassworts.
 *
 * @version 1.0
 * @since 2024-07-26
 */
@Service
public class UserServiceImpl extends ExtendedServiceImpl<User> implements UserService {

  private final BCryptPasswordEncoder bCryptPasswordEncoder;
  private final RoleService roleService;  // Service für Rollenverwaltung

  /**
   * **Konstruktor mit Dependency Injection.**
   *
   * - `UserRepository repository`: Wird für die Datenbankabfragen genutzt.
   * - `Logger logger`: Protokollierung von Benutzeraktionen.
   * - `BCryptPasswordEncoder bCryptPasswordEncoder`: Sichere Passwortverschlüsselung.
   * - `RoleService roleService`: Ermöglicht Zugriff auf Benutzerrollen.
   *
   * @param repository Das `UserRepository`, das mit der Datenbank interagiert.
   * @param logger Logger für die Überwachung von Benutzeraktionen.
   * @param bCryptPasswordEncoder Verschlüsselt Passwörter vor dem Speichern.
   * @param roleService Bietet Zugriff auf Rollen.
   */
  @Autowired
  public UserServiceImpl(UserRepository repository, Logger logger,
                         BCryptPasswordEncoder bCryptPasswordEncoder,
                         RoleService roleService) {
    super(repository, logger);
    this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    this.roleService = roleService;
  }

  /**
   * **Lädt einen Benutzer anhand der E-Mail für die Authentifizierung.**
   *
   * - Wird von Spring Security genutzt, um Benutzer bei der Anmeldung zu identifizieren.
   * - Konvertiert `User` in `UserDetailsImpl`.
   *
   * @param email Die E-Mail-Adresse des Benutzers.
   * @return `UserDetails` für Spring Security.
   * @throws UsernameNotFoundException Falls kein Benutzer mit der E-Mail existiert.
   */
  @Override
  public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
    return ((UserRepository) repository).findByEmail(email)
            .map(UserDetailsImpl::new)
            .orElseThrow(() -> new UsernameNotFoundException("User not found: " + email));
  }

  /**
   * **Registriert einen neuen Benutzer mit Standardrolle `CLIENT`.**
   *
   * - Prüft, ob der Benutzer `null` ist.
   * - Prüft, ob das Passwort `null` oder leer ist.
   * - Verschlüsselt das Passwort mit `BCryptPasswordEncoder`.
   * - Weist die Rolle `CLIENT` zu.
   * - Speichert den Benutzer in der Datenbank.
   *
   * **Fehlerszenarien:**
   * - Falls der `User` `null` ist → `IllegalStateException`
   * - Falls das Passwort leer oder `null` ist → `IllegalArgumentException`
   * - Falls die Rolle `CLIENT` nicht existiert → `IllegalStateException`
   *
   * @param user Der Benutzer, der registriert werden soll.
   * @return Der registrierte Benutzer.
   * @throws IllegalStateException Falls Benutzer `null` ist oder die `CLIENT`-Rolle nicht gefunden wird.
   * @throws IllegalArgumentException Falls das Passwort leer ist.
   */
  @Override
  public User register(User user) {
    if (user == null) {
      throw new IllegalStateException("User object is null before saving!");
    }

    if (user.getPassword() == null || user.getPassword().isEmpty()) {
      throw new IllegalArgumentException("Password must not be null or empty");
    }

    // Verschlüsselung des Passworts
    user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));

    // Hole die `CLIENT`-Rolle aus `RoleService`
    Role clientRole = roleService.findByName("CLIENT");

    if (clientRole == null) {
      throw new IllegalStateException("Role CLIENT not found!");
    }

    // Weise die `CLIENT`-Rolle zu
    user.setRoles(Collections.singleton(clientRole));

    return save(user);
  }
}
