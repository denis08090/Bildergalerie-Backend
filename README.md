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

### Allgemein

Das Zusammenspiel zwischen der Datenbankstruktur und der Springboot-Implementierung zeigt eine gut durchdachte Architektur für die Bildergalerie-Anwendung. Die Trennung von Daten, Geschäftslogik und API-Schicht sowie die Implementierung einer sicheren Benutzerverwaltung sind zentrale Aspekte des Systems. Durch den modularen Aufbau mit DTOs, Services und Controllern ist die Anwendung flexibel und gut erweiterbar.

### Datenbank ERM

![ERM](https://github.com/user-attachments/assets/6057d2e2-9abd-49f3-ad1d-e9db4c4581d4)

- User: Enthält Benutzerdaten wie email, first_name, last_name, password und username. Jeder Benutzer kann mehrere Alben besitzen.
- Album: Repräsentiert eine Sammlung von Fotos. Jedes Album gehört genau einem Benutzer (user_id).
- Photo: Enthält Bilder und zugehörige Metadaten wie photo_date, photo_location, photo_picture und photo_title. Jedes Foto ist einem Album zugeordnet (album_id).
- Role und Authority: Diese Tabellen dienen der Benutzerverwaltung und Rechtevergabe. Ein Benutzer kann mehrere Rollen haben (User_Role), und eine Rolle kann mehrere Berechtigungen (Role_Authority) besitzen.

Dieses Modell ermöglicht eine flexible Verwaltung von Nutzern, Alben und Fotos, mit einer klaren Abgrenzung der Rechte über die Rollen- und Berechtigungsstruktur.

### Klassendiagramm

![BildergalerieKlassendiagram](https://github.com/user-attachments/assets/91ed6337-24a9-49ad-b7ea-a904bea47d91)

#### Entitätsklassen:

- User: Repräsentiert einen Benutzer mit den Attributen userName, firstName, lastName, email, password und einer Liste von roles.
- Album: Jedes Album gehört einem bestimmten Benutzer (userId) und enthält eine Liste von photos. Es besitzt außerdem das Attribut albumTitle.
- Photo: Enthält Bildinformationen (photoPicture, photoLocation, photoTitle, photoDate) und ist mit einem Album (albumId) verknüpft.
- Role: Enthält eine Liste von authorities, welche die Berechtigungen definieren.
- Authority: Definiert individuelle Berechtigungen für Rollen.

#### DTOklassen

- UserDTO, UserRegisterDTO: Data Transfer Objects (DTOs), um Benutzerdaten zwischen API und Backend zu transportieren.
- RoleDTO, AuthorityDTO: DTOs für die Übertragung von Rollen und Berechtigungen.

#### Repositoryklassen

- UserRepository, AlbumRepository, PhotoRepository, RoleRepository: JPA-Repository-Interfaces für den Zugriff auf die Datenbank.

#### Serviceklassen

- UserService, RoleServiceImpl: Enthält die Geschäftslogik für Benutzer- und Rollenverwaltung.
- ExtendedService, ExtendedRepository: Generische Implementierungen für wiederverwendbare Geschäftslogik.

#### Controllerklassen

- UserController: Stellt Endpunkte zur Benutzerverwaltung bereit. Ermöglicht das Registrieren, Abrufen, Aktualisieren und Löschen von Benutzern.
- PhotoController, AlbumController: Stellen REST-Endpoints zur Verwaltung von Fotos und Alben bereit.

#### Sicherheits- und JWT-Konfiguration

- WebSecurityConfig: Konfiguriert Spring Security mit JWT-Authentifizierung.
- JwtProperties: Beinhaltet Einstellungen zur Token-Verwaltung.
- CustomAuthenticationFilter: Implementiert einen benutzerdefinierten Authentifizierungsfilter.
- UserDetailsImpl: Anpassung der Benutzeridentifikation innerhalb von Spring Security.

#### Sonstige Klassen

- Encoders: Verwaltet Passwörter mit einem BCryptPasswordEncoder.
- LoggerConfig: Konfiguriert das Logging innerhalb der Anwendung.

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

## Code Dokumentation

Alle Klassen, Methoden und Funktionen sind im Code selbst dokumentiert.
Das Klassendiagram und sämtliche anderen Strukturen sind unter dem Punkt **Architektur** zu finden.

## Testing

Die Tests werden wie folgt ausgeführt

```powershell
mvn test
```

## Quellen / Hilfestellungen
das Backend setzt sich aus verschiedenen, von uns vorherigen erstellten Projekten und gesammelten Erfahrungen zusammen.

Die Projekte wurden in folgenden Modulen eingereicht: M294, M295, M183

Als Hilfe für unsere Anpassungen, um die verschiedenen Projekte, oder auch von uns neu erstellten Sachen haben wir ChatGPT verwendet. Ausserdem haben wir ChatGPT als Unterstützung für die Kommentare der einzelnen Funktionen und zur recherche für die richtigen Dependencies verwendet. Zur Erstellung von ganzen Code-Fragmenten haben wir ChatGPT **nicht** verwendet.

Des weiteren haben wir uns an den in M223 abgegebenen Materialien bedient.




