package com.example.Bildergalerie.model.role.DTO;

import com.example.Bildergalerie.generic.ExtendedMapper;
import com.example.Bildergalerie.model.role.Role;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface RoleMapper extends ExtendedMapper<Role, RoleDTO> {

}
