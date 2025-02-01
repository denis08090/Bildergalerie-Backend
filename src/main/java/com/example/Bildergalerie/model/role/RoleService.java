package com.example.Bildergalerie.model.role;

/**
 * **Service-Interface für die Verwaltung von Rollen (`Role`).**
 *
 * Dieses Interface definiert die grundlegenden Methoden für die Geschäftslogik
 * zur Verwaltung von Benutzerrollen. Die Implementierung (`RoleServiceImpl`)
 * verwendet `RoleRepository`, um auf die Datenbank zuzugreifen.
 *
 * @version 1.0
 * @since 2024-07-26
 */
public interface RoleService {

    /**
     * **Findet eine Rolle anhand ihres Namens.**
     *
     * - Diese Methode sucht eine Rolle (`Role`) mit dem angegebenen Namen.
     * - Falls keine Rolle gefunden wird, kann eine Exception geworfen werden.
     *
     * @param name Der Name der gesuchten Rolle (z. B. "ADMIN", "USER").
     * @return Die `Role`-Entität, falls sie gefunden wird.
     * @throws RuntimeException Falls keine Rolle mit dem angegebenen Namen existiert.
     */
    Role findByName(String name);
}
