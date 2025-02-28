package com.gymex.service;

import com.gymex.dto.inputs.UserInputDto;
import com.gymex.dto.responses.UserResponseDto;
import com.gymex.entity.User;
import com.gymex.mapper.UserMapper;
import com.gymex.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Objects;

@Service

public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final UserMapper userMapper;

    UserServiceImpl(UserRepository userRepository, UserMapper userMapper){
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }


    @Override
    public UserResponseDto addUser(UserInputDto userInputDto) {

        if(Objects.isNull(userInputDto)){

        }
        User user = userMapper.toEntity(userInputDto);
        user.setCreatedAt(LocalDateTime.now());

        return userMapper.toResponseDto(userRepository.save(user));
    }
}
