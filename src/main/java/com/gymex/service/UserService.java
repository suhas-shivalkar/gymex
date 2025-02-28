package com.gymex.service;

import com.gymex.dto.inputs.UserInputDto;
import com.gymex.dto.responses.UserResponseDto;

public interface UserService {
UserResponseDto addUser(UserInputDto userInputDto);
}
