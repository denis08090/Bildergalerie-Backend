package com.example.Bildergalerie.model.role;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

/**
 * **Implementierung des `RoleService`-Interfaces.**
 *
 * Diese Klasse enthält die **Geschäftslogik zur Verwaltung von Rollen** in der Anwendung.
 * Sie nutzt das `RoleRepository`, um auf die Datenbank zuzugreifen und stellt sicher,
 * dass bestimmte Standardrollen bei der Initialisierung vorhanden sind.
 *
 * **Funktionen:**
 * - **`findByName(String name)`** → Sucht eine Rolle anhand ihres Namens.
 * - **`initRoles()`** → Erstellt `"CLIENT"` und `"ADMINISTRATOR"`, falls sie fehlen.
 *
 * @version 1.0
 * @since 2024-07-26
 */
@Service // ✅ Markiert diese Klasse als Spring Service-Komponente.
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    /**
     * **Konstruktor mit Dependency Injection.**
     *
     * Der Konstruktor erhält eine Instanz von `RoleRepository` und wird von Spring automatisch verwaltet.
     *
     * @param roleRepository Das Repository für den Zugriff auf die `Role`-Tabelle.
     */
    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    /**
     * **Sucht eine Rolle anhand ihres Namens.**
     *
     * - Verwendet das `RoleRepository`, um eine Rolle in der Datenbank zu finden.
     * - Falls die Rolle nicht existiert, wird eine `RuntimeException` geworfen.
     *
     * **Beispielaufruf:**
     * ```java
     * Role adminRole = roleService.findByName("ADMINISTRATOR");
     * ```
     *
     * @param name Der Name der gesuchten Rolle (z. B. `"ADMINISTRATOR"`, `"CLIENT"`).
     * @return Die gefundene `Role`-Entität.
     * @throws RuntimeException Falls die Rolle nicht existiert.
     */
    @Override
    public Role findByName(String name) {
        return roleRepository.findByName(name)
                .orElseThrow(() -> new RuntimeException("❌ Fehler: Rolle '" + name + "' nicht gefunden."));
    }

    /**
     * **Initialisiert Standardrollen nach Anwendungstart.**
     *
     * - Diese Methode wird **automatisch nach der Initialisierung von Spring Boot** ausgeführt.
     * - Hier wird geprüft, ob die Rollen **"CLIENT"** und **"ADMINISTRATOR"** bereits existieren.
     * - Falls eine Rolle nicht existiert, wird sie erstellt und gespeichert.
     *
     * - `@PostConstruct` → Diese Methode wird einmalig nach dem Start der Anwendung ausgeführt.
     */
    @PostConstruct
    public void initRoles() {
        createRoleIfNotExists("CLIENT");         // 🔹 Erstellt "CLIENT", falls sie fehlt
        createRoleIfNotExists("ADMINISTRATOR");  // 🔹 Erstellt "ADMINISTRATOR", falls sie fehlt
    }

    /**
     * **Hilfsmethode zum Erstellen einer Rolle, falls sie nicht existiert.**
     *
     * @param roleName Der Name der Rolle, die überprüft und ggf. erstellt wird.
     */
    private void createRoleIfNotExists(String roleName) {
        if (!roleRepository.findByName(roleName).isPresent()) {
            Role role = new Role();
            role.setName(roleName);
            roleRepository.save(role);
            System.out.println("✅ Rolle erstellt: " + roleName);
        }
    }
}
