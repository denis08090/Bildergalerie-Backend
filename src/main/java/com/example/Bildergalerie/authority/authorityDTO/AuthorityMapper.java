package com.example.Bildergalerie.authority.authorityDTO;

import com.example.Bildergalerie.authority.Authority;
import com.example.Bildergalerie.generic.ExtendedMapper;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
 * Die Schnittstelle `AuthorityMapper` dient der automatischen Umwandlung (Mapping) zwischen
 * `Authority`-Entitäten und `AuthorityDTO`-Objekten mithilfe von MapStruct.
 *
 * - @Mapper: Markiert diese Schnittstelle als MapStruct-Mapper.
 * - componentModel = "spring": Integriert den Mapper mit Spring, sodass er als Spring-Bean verwaltet wird.
 * - unmappedTargetPolicy = ReportingPolicy.IGNORE: Verhindert Fehler oder Warnungen, wenn nicht alle Felder gemappt sind.
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AuthorityMapper extends ExtendedMapper<Authority, AuthorityDTO> {

}
