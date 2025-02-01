package com.example.Bildergalerie.controller;

import com.example.Bildergalerie.model.user.User;
import com.example.Bildergalerie.model.user.UserService;
import com.example.Bildergalerie.model.user.dto.UserDTO;
import com.example.Bildergalerie.model.user.dto.UserMapper;
import com.example.Bildergalerie.model.user.dto.UserRegisterDTO;
import jakarta.validation.Valid;
import java.util.List;
import java.util.UUID;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * **REST-Controller zur Verwaltung von Benutzern.**
 *
 * Diese Klasse stellt Endpunkte zur Verfügung, um Benutzer zu registrieren, abzurufen,
 * zu aktualisieren und zu löschen.
 *
 * @version 1.0
 * @since 2024-07-26
 */
@Validated // Aktiviert die Validierung von @Valid in Request-Bodys.
@RestController // Markiert die Klasse als REST-Controller für HTTP-Anfragen.
@RequestMapping("/users") // Definiert die Basis-URL für alle Endpunkte dieser Klasse.
@Slf4j // Aktiviert Logging mit Lombok.
public class UserController {

    private final UserService userService;
    private final UserMapper userMapper;

    /**
     * **Konstruktor zur Injektion von UserService und UserMapper.**
     *
     * @param userService Service für Benutzerverwaltung.
     * @param userMapper Mapper für die Umwandlung zwischen User und UserDTO.
     */
    @Autowired
    public UserController(UserService userService, UserMapper userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
    }

    /**
     * **Gibt einen Benutzer anhand seiner ID zurück.**
     *
     * @param id Die UUID des gesuchten Benutzers.
     * @return ResponseEntity mit UserDTO oder Fehlerstatus.
     */
    @GetMapping("/{id}") // Definiert einen HTTP-GET-Endpunkt zur Benutzerabfrage anhand der ID.
    public ResponseEntity<UserDTO> retrieveById(@PathVariable UUID id) {
        User user = userService.findById(id); // Sucht den Benutzer in der Datenbank.
        return new ResponseEntity<>(userMapper.toDTO(user), HttpStatus.OK); // Gibt den Benutzer als DTO zurück.
    }

    /**
     * **Gibt eine Liste aller Benutzer zurück.**
     *
     * @return ResponseEntity mit einer Liste von UserDTOs.
     */
    @GetMapping({"", "/"}) // Definiert einen HTTP-GET-Endpunkt zur Abfrage aller Benutzer.
    public ResponseEntity<List<UserDTO>> retrieveAll() {
        List<User> users = userService.findAll(); // Holt alle Benutzer aus der Datenbank.
        return new ResponseEntity<>(userMapper.toDTOs(users), HttpStatus.OK);
    }

    /**
     * **Registriert einen neuen Benutzer.**
     *
     * @param userRegisterDTO DTO mit Registrierungsinformationen.
     * @return ResponseEntity mit dem erstellten Benutzer als DTO.
     */
    @PostMapping("/register") // Definiert einen HTTP-POST-Endpunkt zur Registrierung eines Benutzers.
    public ResponseEntity<UserDTO> register(@Valid @RequestBody UserRegisterDTO userRegisterDTO) {
        log.info("User mit E-Mail {} versucht sich zu registrieren", userRegisterDTO.getEmail()); // Loggt die Registrierung.
        User user = userService.register(userMapper.fromUserRegisterDTO(userRegisterDTO)); // Erstellt den Benutzer.
        return new ResponseEntity<>(userMapper.toDTO(user), HttpStatus.CREATED);
    }

    /**
     * **Aktualisiert einen Benutzer anhand seiner ID.**
     *
     * @param id Die UUID des zu aktualisierenden Benutzers.
     * @param userDTO Die aktualisierten Benutzerinformationen.
     * @return ResponseEntity mit dem aktualisierten Benutzer als DTO.
     */
    @PutMapping("/{id}") // Definiert einen HTTP-PUT-Endpunkt zur Aktualisierung eines Benutzers.
    @PreAuthorize("hasAuthority('USER_MODIFY')") // Erlaubt Zugriff nur für Benutzer mit `USER_MODIFY`-Berechtigung.
    public ResponseEntity<UserDTO> updateById(@PathVariable UUID id,
                                              @Valid @RequestBody UserDTO userDTO) {
        User user = userService.updateById(id, userMapper.fromDTO(userDTO)); // Aktualisiert den Benutzer.
        return new ResponseEntity<>(userMapper.toDTO(user), HttpStatus.OK);
    }

    /**
     * **Löscht einen Benutzer anhand seiner ID.**
     *
     * @param id Die UUID des zu löschenden Benutzers.
     * @return ResponseEntity mit Status NO_CONTENT (204), wenn erfolgreich.
     */
    @DeleteMapping("/{id}") // Definiert einen HTTP-DELETE-Endpunkt zum Löschen eines Benutzers.
    @PreAuthorize("hasAuthority('USER_DELETE')") // Erlaubt Zugriff nur für Benutzer mit `USER_DELETE`-Berechtigung.
    public ResponseEntity<Void> deleteById(@PathVariable UUID id) {
        userService.deleteById(id); // Löscht den Benutzer.
        return new ResponseEntity<>(HttpStatus.NO_CONTENT); // Gibt Status 204 (kein Inhalt) zurück.
    }
}
