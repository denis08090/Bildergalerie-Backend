package com.example.Bildergalerie.model.user;

import com.example.Bildergalerie.generic.ExtendedService;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * **Service-Interface für die Verwaltung von Benutzern (`User`).**
 *
 * Dieses Interface definiert die **Geschäftslogik** für Benutzerverwaltung und erweitert:
 * - `UserDetailsService`: Wird von Spring Security für die Authentifizierung verwendet.
 * - `ExtendedService<User>`: Stellt allgemeine CRUD-Methoden für Benutzer bereit.
 *
 * **Methoden:**
 * - `register(User user)`: Erstellt und speichert einen neuen Benutzer.
 *
 * @version 1.0
 * @since 2024-07-26
 */
public interface UserService extends UserDetailsService, ExtendedService<User> {

  /**
   * **Registriert einen neuen Benutzer.**
   *
   * - Die Implementierung sollte:
   *   1. **Prüfen, ob die E-Mail bereits existiert** (`existsByEmail()`).
   *   2. **Das Passwort sicher mit BCrypt verschlüsseln**.
   *   3. **Die Standardrolle zuweisen** (z. B. `"USER"`).
   *   4. **Den Benutzer in der Datenbank speichern**.
   *
   * **Beispiel für Implementierung:**
   * ```java
   * @Override
   * public User register(User user) {
   *     if (userRepository.existsByEmail(user.getEmail())) {
   *         throw new IllegalStateException("E-Mail ist bereits registriert!");
   *     }
   *     user.setPassword(passwordEncoder.encode(user.getPassword()));
   *     user.setRoles(Set.of(roleRepository.findByName("USER").orElseThrow()));
   *     return userRepository.save(user);
   * }
   * ```
   *
   * @param user Der zu speichernde Benutzer.
   * @return Der registrierte Benutzer mit verschlüsseltem Passwort und Standardrolle.
   */
  User register(User user);
}
