package com.agrocheck.core.rest_modules.users.controller;

import com.agrocheck.core.rest_modules.users.dto.CreateUserDto;
import com.agrocheck.core.rest_modules.users.dto.UserDto;
import com.agrocheck.core.rest_modules.users.model.User;
import com.agrocheck.core.rest_modules.users.service.UserService;
import com.agrocheck.core.utils.api.dto.SimpleResponseDto;
import com.agrocheck.core.utils.auth.dto.AuthRequestDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserService userService;

    @Autowired
    private ModelMapper modelMapper;


    @GetMapping("/users")
    private ResponseEntity<?> getAllUsers(){
        List<UserDto> responseObj = userService.findAll().stream().map(u ->  modelMapper.map(u, UserDto.class)).collect(Collectors.toList());
        return ResponseEntity.ok(new SimpleResponseDto<List<UserDto>>(true, responseObj));
    }

}
