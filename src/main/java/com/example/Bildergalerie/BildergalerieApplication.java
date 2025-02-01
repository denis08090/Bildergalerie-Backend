package com.example.Bildergalerie;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * **Hauptklasse der Bildergalerie-Anwendung.**
 *
 * Diese Klasse ist der Einstiegspunkt für die Spring Boot-Anwendung.
 * Sie startet die Anwendung und initialisiert alle Spring-Komponenten.
 *
 * **Wichtige Annotationen:**
 * - `@SpringBootApplication`: Markiert diese Klasse als **Spring Boot Hauptklasse**.
 * - `scanBasePackages = "com.example.Bildergalerie"`:
 *   - Stellt sicher, dass Spring Boot **alle Beans** (z. B. `@Service`, `@Repository`, `@Controller`) im Paket `com.example.Bildergalerie` findet.
 *
 *
 *
 * @version 1.0
 * @since 2024-07-26
 */
@SpringBootApplication(scanBasePackages = "com.example.Bildergalerie")  // ✅ Stellt sicher, dass alle Beans gefunden werden
public class BildergalerieApplication {

	/**
	 * **Startet die Spring Boot-Anwendung.**
	 *
	 * - `SpringApplication.run()` initialisiert Spring Boot.
	 * - Lädt alle Beans, Konfigurationen und startet den eingebetteten Webserver (Tomcat).
	 *
	 * @param args Kommandozeilenargumente (optional).
	 */
	public static void main(String[] args) {
		SpringApplication.run(BildergalerieApplication.class, args);
	}
}
