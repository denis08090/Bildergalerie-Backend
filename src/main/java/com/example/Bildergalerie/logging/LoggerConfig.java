package com.example.Bildergalerie.logging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InjectionPoint;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

/**
 * **Konfigurationsklasse für die Logger-Initialisierung mit SLF4J.**
 *
 * Diese Klasse stellt eine zentrale Bean für Logger bereit, sodass jeder Spring-Bean
 * dynamisch ein passendes `Logger`-Objekt injiziert bekommt.
 *
 * - `@Configuration`: Markiert diese Klasse als Spring-Konfigurationsklasse.
 * - `@Bean`: Erstellt eine Bean, die an anderen Stellen automatisch injiziert werden kann.
 * - `@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)`: Stellt sicher, dass für jede Bean,
 *   die einen Logger benötigt, eine eigene Instanz erstellt wird.
 *
 * @version 1.0
 * @since 2024-07-26
 */
@Configuration // Markiert die Klasse als Spring-Konfiguration.
public class LoggerConfig {

    /**
     * **Erstellt und injiziert einen Logger für jede Klasse, die ihn benötigt.**
     *
     * - `@Bean`: Diese Methode registriert eine Spring-Bean vom Typ `Logger`.
     * - `@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)`: Dadurch erhält jede Klasse eine eigene Logger-Instanz.
     * - `InjectionPoint`: Spring ermittelt automatisch die Klasse, in die der Logger injiziert wird.
     *
     * @param injectionPoint Der Punkt, an dem der Logger in eine Klasse injiziert wird.
     * @return Eine Logger-Instanz, die an die Klasse gebunden ist, in die sie injiziert wird.
     */
    @Bean
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public Logger logger(InjectionPoint injectionPoint) {
        return LoggerFactory.getLogger(
                injectionPoint.getMethodParameter().getContainingClass() // Ermittelt die Klasse, in die der Logger injiziert wird.
        );
    }
}
