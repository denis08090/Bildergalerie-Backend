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

## Architektur


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

### Build und Start

```powershell
mvn clean install
mvn spring-boot run
```

## API Endpoints

### Albumverwaltung
- **GET /albums** - Ruft eine Liste aller Alben ab
- **POST /albums** - Fügt ein neues Album hinzu
- **PUT /albums/{albumId}?title={newTitle}** - Aktualisiert den Titel eines bestehenden Albums
- **DELETE /albums/deleteAlbum/{albumId}** - Löscht ein Album anhand der ID

### Fotoverwaltung
- **POST /photos/addPhoto** - Fügt ein neues Foto zu einem Album hinzu
- **GET /photos/album/{albumId}** - Ruft eine Liste aller Fotos eines bestimmten Albums ab
- **PUT /photos/updatePhoto/{photoId}** - Aktualisiert ein vorhandenes Foto
- **DELETE /photos/deletePhoto/{photoId}** - Löscht ein Foto anhand seiner ID

### Benutzerverwaltung
- **POST /users/register** - Fügt einen neuen Benutzer hinzu
- **GET /users/all** - Ruft eine Liste aller Benutzer ab
- **GET /users/{userId}** - Ruft einen Benutzer anhand seiner ID ab
- **PUT /users/updateUser/{userId}** - Aktualisiert die Daten eines bestehenden Benutzers
- **DELETE /users/deleteUser/{userId}** - Löscht einen Benutzer anhand seiner ID

## Authentifizierung

Die API verwendet JWT Tokens zur Authentifizierung.

## JWT Konfiguration


## Code Dokumentation

Alle Klassen, Methoden und Funktionen sind im Code selbst dokumentiert.
Das Klassendiagram und sämtliche anderen Strukturen sind unter dem Punkt **Architektur** zu finden.

## Security Configuration


## Testing

Die Tests werden wie folgt ausgeführt

```powershell
mvn test
```

## Quellen / Hilfestellungen
das Backend setzt sich aus verschiedenen, von uns vorherigen erstellten Projekten und gesammelten Erfahrungen zusammen.
Die Projekte wurden in folgenden Modulen eingereicht: M294, M295, M183
Als Hilfe für unsere Anpassungen, um die verschiedenen Projekte, oder auch von uns neu erstellten Sachen haben wir ChatGPT verwendet. Ausserdem haben wir ChatGPT als Unterstützung für die Kommentare der einzelnen Funktionen verwendet. Zur Erstellung von ganzen Code-Fragmenten haben wir ChatGPT **nicht** verwendet.
Des weiteren haben wir uns an den in M223 abgegebenen Materialien bedient.




