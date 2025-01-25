# Bildergalerie Backend Dokumentation

## Overview

Unsere Bildergalerie ist eine Spring-Boot Applikation, welche für die Verwaltung der Datenbank der einzelnen User, Galerien, Fotos, etc. zuständig ist. Zudem liefert die Spring-Boot Applikation RESTful Endpoints für die User Authentifizierung, Bilder und Galerie Verwaltung.

## Verwendete Technologien

- Java 22
- Spring-Boot
- Spring Security mit JWT Authentication
- MySQL
- Maven

## Voraussetzungen

- JDK 22+
- Maven 3.8+
- MySQL 8.0
- Git

## Architecture


## Installationsanleitung

### Repository Klonen

```powershell
git clone https://github.com/denis08090/Bildergalerie-Backend
```

### DB Konfiguration

erstelle einen User mit folgenden credentials:
**username:** "bildergalerie"
**password:** "bildergalerie"

Alternativ kann auch die Konfiguration der **application.properties** angepasst werden.

```properties
spring.datasource.url=jdbc:mysql://${MYSQL_HOST:localhost}:3306
spring.datasource.username=bildergalerie
spring.datasource.password=bildergalerie
```

Die Datenbank wird beim Start der Spring-Boot Applikation selbst erstellt, sofern diese noch nicht vorhanden ist.
Die Tables und Columns werden durch die jeweiligen Entitätsklassen erstellt.

siehe [dbscript.sql](https://github.com/denis08090/Bildergalerie-Backend/blob/main/src/main/resources/dbscript.sql)

#### Übersicht Datenbank


### Build und Start

```powershell
mvn clean install
mvn spring-boot run
```

