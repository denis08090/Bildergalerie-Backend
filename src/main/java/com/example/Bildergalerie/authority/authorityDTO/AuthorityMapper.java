package com.example.Bildergalerie.authority.authorityDTO;


import com.example.Bildergalerie.authority.Authority;
import com.example.Bildergalerie.generic.ExtendedMapper;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;


@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AuthorityMapper extends ExtendedMapper<Authority, AuthorityDTO> {

}
