package com.gymex.mapper;

import com.gymex.dto.inputs.UserInputDto;
import com.gymex.dto.responses.UserResponseDto;
import com.gymex.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {


    User toEntity(UserInputDto userInputDto);

    UserResponseDto toResponseDto(User user);
}
