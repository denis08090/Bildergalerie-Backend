package com.example.Bildergalerie.model.user.dto;

import com.example.Bildergalerie.generic.ExtendedMapper;
import com.example.Bildergalerie.model.user.User;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
 * **Mapper-Interface für die Konvertierung zwischen `User` und `UserDTO`.**
 *
 * Dieses Interface nutzt **MapStruct**, um die automatische Umwandlung zwischen der Entitätsklasse `User`
 * und den entsprechenden DTOs (`UserDTO`, `UserRegisterDTO`) zu ermöglichen.
 *
 * - `@Mapper(componentModel = "spring")`: Registriert das Interface als Spring-Bean für automatische Injektion.
 * - `unmappedTargetPolicy = ReportingPolicy.IGNORE`: Ignoriert Felder, die nicht explizit gemappt wurden.
 * - Erbt von `ExtendedMapper<User, UserDTO>`, um Standard-CRUD-Mappings bereitzustellen.
 *
 * @version 1.0
 * @since 2024-07-26
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper extends ExtendedMapper<User, UserDTO> {

  /**
   * **Konvertiert ein `UserRegisterDTO` in eine `User`-Entität.**
   *
   * - Diese Methode wird benötigt, wenn ein neuer Benutzer registriert wird.
   * - MapStruct erstellt die Implementierung automatisch zur Laufzeit.
   *
   * @param dto Das DTO für die Benutzerregistrierung.
   * @return Die konvertierte `User`-Entität.
   */
  User fromUserRegisterDTO(UserRegisterDTO dto);
}
