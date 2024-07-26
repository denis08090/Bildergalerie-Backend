package com.example.Bildergalerie.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Configuration class for setting up CORS settings.
 *
 * This class configures cross-origin resource sharing (CORS) to allow
 * the frontend and backend to communicate properly, particularly in a
 * development environment where they might be hosted on different ports.
 *
 * @version 1.0
 * @since 2024-07-26
 * @author Denis Roos
 */
@Configuration
public class WebConfig {

    /**
     * Konfiguriert CORS-Mappings.
     *
     * @return WebMvcConfigurer, um CORS-Konfigurationen festzulegen.
     */
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOrigins("http://localhost:3000") // React App läuft normalerweise auf Port 3000
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                        .allowedHeaders("*")
                        .allowCredentials(true);
            }
        };
    }
}
