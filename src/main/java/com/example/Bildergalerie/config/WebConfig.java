package com.example.Bildergalerie.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * **Konfigurationsklasse für CORS-Einstellungen.**
 *
 * Diese Klasse ermöglicht Cross-Origin Resource Sharing (CORS), sodass das Frontend
 * (z. B. eine React-Anwendung auf `http://localhost:3000`) mit dem Backend kommunizieren kann.
 * Besonders in der Entwicklungsumgebung sind Frontend und Backend oft auf unterschiedlichen Ports gehostet.
 *
 * @version 1.0
 * @since 2024-07-26
 * @author Denis Roos
 */
@Configuration
public class WebConfig {

    /**
     * Erstellt eine Spring-Bean, die CORS-Mappings konfiguriert.
     *
     * @return Ein `WebMvcConfigurer`, um CORS-Konfigurationen festzulegen.
     */
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {

            /**
             * Konfiguriert globale CORS-Regeln für das Backend.
             *
             * @param registry Die CORS-Registry, die die Mappings speichert.
             */
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**") // Gilt für alle Endpunkte des Backends
                        .allowedOrigins("http://localhost:3000") // Erlaubt Anfragen von diesem Ursprung (React-App)
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // Erlaubt diese HTTP-Methoden
                        .allowedHeaders("*") // Alle Header-Typen sind erlaubt
                        .allowCredentials(true); // Ermöglicht das Senden von Cookies & Authentifizierungsdaten
            }
        };
    }
}
