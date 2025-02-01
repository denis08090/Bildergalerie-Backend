package com.example.Bildergalerie.generic;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.UUID;

/**
 * **Generisches Repository-Interface für alle Entitäten mit einer UUID als Primärschlüssel.**
 *
 * Dieses Interface erweitert `JpaRepository` und stellt grundlegende CRUD-Operationen für
 * alle Entitäten bereit, die von `ExtendedEntity` erben.
 *
 * - `@NoRepositoryBean`: Markiert dieses Interface als Basisklasse, sodass es nicht als eigenes
 *   Spring-Repository instanziiert wird.
 * - `JpaRepository<T, UUID>`: Standard-Repository mit Methoden für `findById`, `save`, `delete`, etc.
 *
 * @param <T> Die Entitätsklasse, die von `ExtendedEntity` erbt.
 * @version 1.0
 * @since 2024-07-26
 */
@NoRepositoryBean // Verhindert, dass Spring dieses Interface als eigenständiges Repository behandelt.
public interface ExtendedRepository<T extends ExtendedEntity> extends JpaRepository<T, UUID> {

}
