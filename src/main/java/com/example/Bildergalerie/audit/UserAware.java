package com.example.Bildergalerie.audit;

import com.example.Bildergalerie.model.user.User;
import com.example.Bildergalerie.model.user.UserDetailsImpl;
import java.util.Optional;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

/**
 * Diese Klasse dient dazu, den aktuellen Benutzer als Auditor für die Auditing-Funktionalität
 * in Spring Data JPA bereitzustellen.
 *
 * @Component - Diese Annotation macht die Klasse zu einer Spring-Komponente,
 * sodass sie automatisch von Spring erkannt und verwaltet wird.
 */
@Component
public class UserAware implements AuditorAware<User> {

    /**
     * Standard-Konstruktor. Dieser ist hier nicht notwendig, aber vorhanden.
     */
    public UserAware() {
    }

    /**
     * Diese Methode bestimmt den aktuell authentifizierten Benutzer und gibt ihn als Auditor zurück.
     *
     * @return Ein Optional, das entweder den aktuell authentifizierten Benutzer oder ein leeres Optional enthält.
     */
    public Optional<User> getCurrentAuditor() {
        // Holt die aktuelle Authentifizierungsinformation aus dem SecurityContext
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        // Prüft, ob eine Authentifizierung existiert und ob der Benutzer angemeldet ist
        if (authentication != null && authentication.isAuthenticated()) {
            // Holt das Principal-Objekt, das die Benutzerdetails enthält
            Object principal = authentication.getPrincipal();

            // Prüft, ob das Principal-Objekt eine Instanz von UserDetailsImpl ist
            if (principal instanceof UserDetailsImpl) {
                // Gibt den Benutzer aus den UserDetails zurück
                return Optional.of(((UserDetailsImpl) principal).user());
            }
        }

        // Falls keine Authentifizierung existiert oder der Benutzer nicht korrekt ermittelt werden kann,
        // wird ein leeres Optional zurückgegeben
        return Optional.empty();
    }
}
