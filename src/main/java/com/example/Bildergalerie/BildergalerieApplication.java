package com.example.Bildergalerie;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.example.Bildergalerie")  // ✅ Füge das Paket hinzu
public class BildergalerieApplication {
	public static void main(String[] args) {
		SpringApplication.run(BildergalerieApplication.class, args);
	}
}
