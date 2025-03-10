package com.gymex.service;

import com.gymex.dto.inputs.UserInputDto;
import com.gymex.dto.responses.UserResponseDto;
import com.gymex.entity.User;
import com.gymex.exception.InvalidUserInputException;
import com.gymex.exception.UserAlreadyExistsException;
import com.gymex.exception.UserCreationException;
import com.gymex.mapper.UserMapper;
import com.gymex.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;


import java.time.LocalDateTime;
import java.util.Objects;

@Service

public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final UserMapper userMapper;

    UserServiceImpl(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }


    @Override
    public UserResponseDto addUser(UserInputDto userInputDto) {

        validateUserInput(userInputDto);
        try {

            User user = userMapper.toEntity(userInputDto);
            user.setCreatedAt(LocalDateTime.now());
            return userMapper.toResponseDto(userRepository.save(user));
        } catch (DataIntegrityViolationException e) {
            throw new UserCreationException("Failed to create User " + e.getMessage(), e);
        } catch (Exception e) {
            throw new UserCreationException("An unexpected error occurred while creating user", e);
        }


    }

    private void validateUserInput(UserInputDto userInputDto) {
        if (Objects.isNull(userInputDto)) {
            throw new InvalidUserInputException("User input can not be null");
        }
        if (userRepository.existsByEmail(userInputDto.getEmail())) {
            throw new UserAlreadyExistsException("User already exists");
        }
    }
}
