package com.gymex.mapper;

import com.gymex.dto.inputs.UserInputDto;
import com.gymex.dto.responses.UserResponseDto;
import com.gymex.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mappings({@Mapping(target = "id", ignore = true),
            @Mapping(target = "createdAt", ignore = true)})
    User toEntity(UserInputDto userInputDto);

    UserResponseDto toResponseDto(User user);
}
