package com.gymex.service;

import com.gymex.dto.inputs.UserInputDto;
import com.gymex.dto.responses.UserResponseDto;

import java.util.List;

public interface UserService {
UserResponseDto addUser(UserInputDto userInputDto);

    List<UserResponseDto> getAll();
}
