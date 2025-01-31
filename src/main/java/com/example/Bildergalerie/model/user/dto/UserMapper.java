package com.example.Bildergalerie.model.user.dto;
import com.example.Bildergalerie.generic.ExtendedMapper;
import com.example.Bildergalerie.model.user.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper extends ExtendedMapper<User, UserDTO> {

  // For UserRegisterDTO mapping
  @Mapping(source = "userName", target = "userName")  // WICHTIG! ✅
  User fromUserRegisterDTO(UserRegisterDTO dto);

}
