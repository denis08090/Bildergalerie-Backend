package com.example.Bildergalerie.generic;

import com.example.Bildergalerie.model.user.User;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * **Abstrakte Klasse für erweiterte Auditing-Funktionalität in Entities.**
 *
 * Diese Klasse erweitert `ExtendedEntity` und fügt Audit-Felder hinzu, um zu verfolgen,
 * wann eine Entität erstellt oder geändert wurde und durch welchen Benutzer.
 *
 * - `@MappedSuperclass` ermöglicht die Wiederverwendung dieser Felder in anderen Entitäten.
 * - `@EntityListeners(AuditingEntityListener.class)` aktiviert Spring Data JPA Auditing.
 *
 * @version 1.0
 * @since 2024-07-26
 */
@MappedSuperclass // Markiert diese Klasse als Basisklasse für andere Entitäten.
@EntityListeners(AuditingEntityListener.class) // Aktiviert automatisches Auditing durch Spring Data.
public abstract class ExtendedAuditEntity extends ExtendedEntity {

    /**
     * **Zeitstempel für das Erstellungsdatum der Entität.**
     *
     * - `@CreatedDate`: Wird automatisch gesetzt, wenn die Entität erstellt wird.
     * - `@Column(name = "created_at")`: Gibt den Spaltennamen in der Datenbank an.
     */
    @CreatedDate
    @Column(name = "created_at", updatable = false) // Dieses Feld sollte nicht nachträglich aktualisiert werden.
    private LocalDateTime createdAt;

    /**
     * **Referenz auf den Benutzer, der die Entität erstellt hat.**
     *
     * - `@ManyToOne`: Beziehung zu einer anderen Entität (User).
     * - `@JoinColumn(name = "created_by")`: Gibt die Fremdschlüssel-Spalte in der Datenbank an.
     * - `@CreatedBy`: Wird automatisch durch Spring Security gesetzt, wenn Auditing aktiviert ist.
     */
    @ManyToOne
    @JoinColumn(name = "created_by")
    @CreatedBy
    private User createdBy;

    /**
     * **Zeitstempel für die letzte Änderung der Entität.**
     *
     * - `@LastModifiedDate`: Wird automatisch aktualisiert, wenn die Entität geändert wird.
     * - `@Column(name = "modified_at")`: Gibt den Spaltennamen in der Datenbank an.
     */
    @LastModifiedDate
    @Column(name = "modified_at")
    private LocalDateTime modifiedAt;

    /**
     * **Referenz auf den Benutzer, der die Entität zuletzt geändert hat.**
     *
     * - `@ManyToOne`: Beziehung zu einer anderen Entität (User).
     * - `@JoinColumn(name = "last_modified_by")`: Gibt die Fremdschlüssel-Spalte in der Datenbank an.
     * - `@LastModifiedBy`: Wird automatisch durch Spring Security gesetzt, wenn Auditing aktiviert ist.
     */
    @ManyToOne
    @JoinColumn(name = "last_modified_by")
    @LastModifiedBy
    private User lastModifiedBy;

    /**
     * **Standard-Konstruktor (geschützt, da abstrakte Klasse).**
     */
    protected ExtendedAuditEntity() {
    }

    /**
     * **Konstruktor mit ID.**
     *
     * @param id Eindeutige UUID der Entität.
     */
    protected ExtendedAuditEntity(UUID id) {
        super(id);
    }

    /**
     * **Getter für `createdAt`.**
     *
     * @return Zeitstempel der Erstellung.
     */
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    /**
     * **Setter für `createdAt`.**
     *
     * @param createdAt Neuer Erstellungszeitpunkt.
     * @return Die aktuelle Instanz von `ExtendedAuditEntity`.
     */
    public ExtendedAuditEntity setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    /**
     * **Getter für `createdBy`.**
     *
     * @return Benutzer, der die Entität erstellt hat.
     */
    public User getCreatedBy() {
        return createdBy;
    }

    /**
     * **Setter für `createdBy`.**
     *
     * @param createdBy Neuer Ersteller der Entität.
     * @return Die aktuelle Instanz von `ExtendedAuditEntity`.
     */
    public ExtendedAuditEntity setCreatedBy(User createdBy) {
        this.createdBy = createdBy;
        return this;
    }

    /**
     * **Getter für `modifiedAt`.**
     *
     * @return Zeitstempel der letzten Änderung.
     */
    public LocalDateTime getModifiedAt() {
        return modifiedAt;
    }

    /**
     * **Setter für `modifiedAt`.**
     *
     * @param modifiedAt Neuer Änderungszeitpunkt.
     * @return Die aktuelle Instanz von `ExtendedAuditEntity`.
     */
    public ExtendedAuditEntity setModifiedAt(LocalDateTime modifiedAt) {
        this.modifiedAt = modifiedAt;
        return this;
    }

    /**
     * **Getter für `lastModifiedBy`.**
     *
     * @return Benutzer, der die Entität zuletzt geändert hat.
     */
    public User getLastModifiedBy() {
        return lastModifiedBy;
    }

    /**
     * **Setter für `lastModifiedBy`.**
     *
     * @param lastModifiedBy Neuer letzter Bearbeiter.
     * @return Die aktuelle Instanz von `ExtendedAuditEntity`.
     */
    public ExtendedAuditEntity setLastModifiedBy(User lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
        return this;
    }
}
