package com.example.Bildergalerie.model.user;

import com.example.Bildergalerie.generic.ExtendedRepository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

/**
 * **Repository-Interface für die Verwaltung von {@link User}-Entitäten.**
 *
 * Dieses Interface erweitert `ExtendedRepository<User>` (das `JpaRepository` erweitert)
 * und bietet CRUD-Operationen für `User`-Entitäten in der Datenbank.
 *
 * **Besondere Methoden:**
 * - `findById(UUID userId)`: Sucht einen Benutzer anhand der UUID.
 * - `findByEmail(String email)`: Sucht einen Benutzer anhand der E-Mail-Adresse.
 *
 * **Standard-Methoden von `JpaRepository`:**
 * - `findAll()`: Gibt alle Benutzer zurück.
 * - `findById(UUID id)`: Findet einen Benutzer anhand seiner UUID.
 * - `save(User user)`: Speichert oder aktualisiert einen Benutzer.
 * - `deleteById(UUID id)`: Löscht einen Benutzer anhand seiner UUID.
 *
 * @version 1.0
 * @since 2024-07-26
 * @author
 */
public interface UserRepository extends ExtendedRepository<User> {

    /**
     * **Findet einen Benutzer anhand seiner UUID.**
     *
     * - Gibt ein `Optional<User>` zurück, um `null`-Werte zu vermeiden.
     * - Wird automatisch von Spring Data JPA implementiert.
     *
     * @param userId Die UUID des Benutzers.
     * @return Ein `Optional<User>`, das den gefundenen Benutzer enthält oder leer ist.
     */
    Optional<User> findById(UUID userId);

    boolean existsByEmail(String email);
    /**
     * **Findet einen Benutzer anhand seiner E-Mail-Adresse.**
     *
     * - Wird von Spring Security für die Authentifizierung genutzt.
     * - Falls keine Übereinstimmung gefunden wird, gibt die Methode `Optional.empty()` zurück.
     *
     * **Beispiel:**
     * ```java
     * Optional<User> user = userRepository.findByEmail("john@example.com");
     * ```
     *
     * @param email Die E-Mail-Adresse des Benutzers.
     * @return Ein `Optional<User>`, das den gefundenen Benutzer enthält oder leer ist.
     */
    Optional<User> findByEmail(String email);
}
