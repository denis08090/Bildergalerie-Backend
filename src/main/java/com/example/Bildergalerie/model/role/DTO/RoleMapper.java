package com.example.Bildergalerie.model.role.DTO;

import com.example.Bildergalerie.generic.ExtendedMapper;
import com.example.Bildergalerie.model.role.Role;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
 * **Mapper-Interface für die Konvertierung zwischen `Role` und `RoleDTO`.**
 *
 * Dieses Interface nutzt MapStruct, um die Umwandlung zwischen der Entitätsklasse `Role`
 * und dem DTO `RoleDTO` zu automatisieren. Dadurch müssen keine manuellen Mapping-Methoden
 * geschrieben werden.
 *
 * - `@Mapper(componentModel = "spring")`: Markiert dieses Interface als Spring-Bean für MapStruct.
 * - `unmappedTargetPolicy = ReportingPolicy.IGNORE`: Ignoriert nicht gemappte Felder, um Warnungen zu vermeiden.
 * - Erweitert `ExtendedMapper<Role, RoleDTO>`, das generische Mapping-Methoden bereitstellt.
 *
 * @version 1.0
 * @since 2024-07-26
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface RoleMapper extends ExtendedMapper<Role, RoleDTO> {

}
