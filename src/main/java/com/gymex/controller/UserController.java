package com.gymex.controller;

import com.gymex.dto.inputs.UserInputDto;
import com.gymex.dto.responses.UserResponseDto;
import com.gymex.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")

public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<UserResponseDto> addUser(@RequestBody UserInputDto userInputDto){
    return ResponseEntity.ok(userService.addUser(userInputDto));
    }
}
