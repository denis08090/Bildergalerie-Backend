package com.example.Bildergalerie.model.role;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

/**
 * **Implementierung des RoleService-Interfaces.**
 *
 * Diese Klasse enthält die Geschäftslogik zur Verwaltung von Rollen in der Anwendung.
 * Sie nutzt das `RoleRepository`, um auf die Datenbank zuzugreifen, und sorgt dafür,
 * dass bestimmte Rollen bei Initialisierung vorhanden sind.
 *
 * @version 1.0
 * @since 2024-07-26
 */
@Service // Markiert diese Klasse als Spring Service-Komponente.
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    /**
     * **Konstruktor mit Dependency Injection.**
     *
     * @param roleRepository Repository für den Zugriff auf die Role-Datenbanktabelle.
     */
    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    /**
     * **Sucht eine Rolle anhand ihres Namens.**
     *
     * - Verwendet das RoleRepository, um eine Rolle zu finden.
     * - Wenn die Rolle nicht gefunden wird, wird eine `RuntimeException` geworfen.
     *
     * @param name Der Name der gesuchten Rolle (z. B. "ADMIN", "USER").
     * @return Die gefundene `Role`-Entität.
     * @throws RuntimeException Wenn die Rolle nicht existiert.
     */
    @Override
    public Role findByName(String name) {
        return roleRepository.findByName(name)
                .orElseThrow(() -> new RuntimeException("Error: Role " + name + " not found."));
    }

    /**
     * **Initialisiert Standardrollen nach Anwendungstart.**
     *
     * - Diese Methode wird nach der Initialisierung der Bean ausgeführt.
     * - Hier wird geprüft, ob die Rolle "CLIENT" bereits existiert.
     * - Wenn nicht, wird sie neu angelegt und gespeichert.
     *
     * - `@PostConstruct`: Markiert eine Methode, die nach der Initialisierung der Bean ausgeführt wird.
     */
    @PostConstruct
    public void initRoles() {
        if (!roleRepository.findByName("CLIENT").isPresent()) {
            Role clientRole = new Role();
            clientRole.setName("CLIENT");
            roleRepository.save(clientRole);
        }
    }

}

