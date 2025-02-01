package com.example.Bildergalerie.model.user;

import com.example.Bildergalerie.model.role.Role;
import com.example.Bildergalerie.model.role.RoleRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

/**
 * **Implementierung des `UserService` für die Benutzerverwaltung**
 *
 * - Verwaltet Benutzer-Registrierung, Speicherung und Authentifizierung.
 * - Verwendet Spring Security zur Authentifizierung und Berechtigungsverwaltung.
 *
 * @version 1.0
 * @since 2024-07-26
 */
@Service // ✅ Markiert diese Klasse als Spring Service-Komponente
public class UserServiceImpl implements UserService {

  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;
  private final RoleRepository roleRepository;

  /**
   * **Konstruktor für Dependency Injection**
   *
   * - `UserRepository`: Datenzugriff auf Benutzer.
   * - `PasswordEncoder`: Hashing von Passwörtern.
   * - `RoleRepository`: Zugriff auf Rollen.
   *
   * @param userRepository   Repository für Benutzer-Daten.
   * @param passwordEncoder  BCrypt für Passwortverschlüsselung.
   * @param roleRepository   Repository für Rollen.
   */
  public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, RoleRepository roleRepository) {
    this.userRepository = userRepository;
    this.passwordEncoder = passwordEncoder;
    this.roleRepository = roleRepository;
  }

  /**
   * **Registriert einen neuen Benutzer.**
   *
   * **Ablauf:**
   * 1. **Prüfung:** E-Mail darf nicht bereits existieren.
   * 2. **Passwort:** Wird sicher mit BCrypt gehasht.
   * 3. **Standardrolle:** `"CLIENT"` wird dem Benutzer zugewiesen.
   * 4. **Speicherung:** Benutzer wird in der Datenbank gespeichert.
   *
   * @param user Der neue Benutzer, der gespeichert werden soll.
   * @return Der gespeicherte Benutzer mit ID und verschlüsseltem Passwort.
   * @throws IllegalStateException Falls die E-Mail bereits registriert ist.
   */
  @Override
  public User register(User user) {
    // ✅ Überprüfe, ob die E-Mail bereits registriert ist
    if (userRepository.existsByEmail(user.getEmail())) {
      throw new IllegalStateException("❌ E-Mail ist bereits registriert!");
    }

    // ✅ Passwort sicher mit BCrypt verschlüsseln
    user.setPassword(passwordEncoder.encode(user.getPassword()));

    // ✅ Standardrolle setzen ("CLIENT" statt "USER")
    Role defaultRole = roleRepository.findByName("CLIENT")
            .orElseThrow(() -> new RuntimeException("❌ Fehler: Standardrolle CLIENT nicht gefunden!"));

    user.setRoles(Set.of(defaultRole));

    // ✅ Benutzer speichern
    return userRepository.save(user);
  }

  /**
   * **Speichert einen Benutzer (noch nicht implementiert).**
   *
   * @param entity Der zu speichernde Benutzer.
   * @return Gespeicherter Benutzer.
   */
  @Override
  public User save(User entity) {
    return null; // 🚧 Noch nicht implementiert!
  }

  /**
   * **Löscht einen Benutzer anhand der ID (noch nicht implementiert).**
   *
   * @param id Die ID des zu löschenden Benutzers.
   * @return `null` (aktuell nicht implementiert).
   * @throws NoSuchElementException Falls der Benutzer nicht existiert.
   */
  @Override
  public Void deleteById(UUID id) throws NoSuchElementException {
    return null;
  }

  /**
   * **Aktualisiert einen Benutzer anhand der ID (noch nicht implementiert).**
   *
   * @param id      Die ID des zu aktualisierenden Benutzers.
   * @param entity  Die neuen Benutzerdaten.
   * @return `null` (aktuell nicht implementiert).
   * @throws NoSuchElementException Falls der Benutzer nicht existiert.
   */
  @Override
  public User updateById(UUID id, User entity) throws NoSuchElementException {
    return null;
  }

  /**
   * **Gibt alle Benutzer zurück (noch nicht implementiert).**
   *
   * @return Eine leere Liste.
   */
  @Override
  public List<User> findAll() {
    return List.of();
  }

  /**
   * **Gibt alle Benutzer mit Paging zurück (noch nicht implementiert).**
   *
   * @param pageable Das Paging-Objekt.
   * @return Eine leere Liste.
   */
  @Override
  public List<User> findAll(Pageable pageable) {
    return List.of();
  }

  /**
   * **Sucht einen Benutzer anhand der ID (noch nicht implementiert).**
   *
   * @param id Die Benutzer-ID.
   * @return `null`, wenn nicht gefunden.
   */
  @Override
  public User findById(UUID id) {
    return null;
  }

  /**
   * **Prüft, ob ein Benutzer mit der ID existiert (noch nicht implementiert).**
   *
   * @param id Die Benutzer-ID.
   * @return `false`, da nicht implementiert.
   */
  @Override
  public boolean existsById(UUID id) {
    return false;
  }

  /**
   * **Findet einen Benutzer oder wirft eine Exception (noch nicht implementiert).**
   *
   * @param optional Der optionale Benutzer.
   * @return `null`, wenn nicht gefunden.
   * @throws NoSuchElementException Falls der Benutzer nicht existiert.
   */
  @Override
  public User findOrThrow(Optional<User> optional) throws NoSuchElementException {
    return null;
  }

  /**
   * **Lädt einen Benutzer für die Authentifizierung durch Spring Security.**
   *
   * **Ablauf:**
   * - Sucht den Benutzer anhand der E-Mail.
   * - Konvertiert Rollen (`Role`) in `GrantedAuthority` für Spring Security.
   * - Erstellt ein `UserDetails`-Objekt mit verschlüsseltem Passwort und Rollen.
   *
   * @param email Die E-Mail-Adresse des Benutzers.
   * @return `UserDetails`-Objekt für Spring Security.
   * @throws UsernameNotFoundException Falls der Benutzer nicht gefunden wird.
   */
  @Override
  public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
    // ✅ Benutzer aus der Datenbank abrufen
    User user = userRepository.findByEmail(email)
            .orElseThrow(() -> new UsernameNotFoundException("❌ Fehler: Benutzer mit E-Mail '" + email + "' nicht gefunden!"));

    // ✅ Benutzer in UserDetails-Objekt für Spring Security umwandeln
    return new UserDetailsImpl(user);
  }
}
