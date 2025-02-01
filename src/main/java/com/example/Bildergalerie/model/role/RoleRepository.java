package com.example.Bildergalerie.model.role;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
import java.util.UUID;

/**
 * **Repository-Interface für die Verwaltung von {@link Role}-Entitäten.**
 *
 * Dieses Interface erweitert `JpaRepository` und stellt CRUD-Operationen für `Role`-Entitäten bereit.
 * Zusätzlich enthält es eine benutzerdefinierte Methode zur Suche nach einer Rolle anhand des Namens.
 *
 * - `JpaRepository<Role, UUID>`:
 *   - `Role`: Die verwaltete Entitätsklasse.
 *   - `UUID`: Der Typ des Primärschlüssels (die ID der Rolle).
 *
 * Standardmäßig stellt `JpaRepository` Methoden bereit wie:
 * - `findAll()`: Gibt alle Rollen zurück.
 * - `findById(UUID id)`: Sucht eine Rolle anhand ihrer ID.
 * - `save(Role role)`: Speichert oder aktualisiert eine Rolle.
 * - `deleteById(UUID id)`: Löscht eine Rolle anhand ihrer ID.
 * - `count()`: Gibt die Anzahl der gespeicherten Rollen zurück.
 *
 * @version 1.0
 * @since 2024-07-26
 */
public interface RoleRepository extends JpaRepository<Role, UUID> {

    /**
     * **Sucht eine Rolle anhand ihres Namens.**
     *
     * - Diese Methode wird von Spring Data JPA automatisch basierend auf dem Methodennamen generiert.
     * - Die Methode gibt ein `Optional<Role>` zurück, um `null`-Werte zu vermeiden.
     *
     * @param name Der Name der Rolle (z. B. "ADMIN", "USER").
     * @return Ein `Optional<Role>` mit der gefundenen Rolle oder leer, falls keine existiert.
     */
    Optional<Role> findByName(String name);
}
