package com.agrocheck.core.utils.auth.controller;

import com.agrocheck.core.rest_modules.users.dto.CreateUserDto;
import com.agrocheck.core.rest_modules.users.dto.UserDto;
import com.agrocheck.core.rest_modules.users.model.User;
import com.agrocheck.core.utils.auth.service.AuthUserServiceImpl;
import com.agrocheck.core.rest_modules.users.service.UserService;
import com.agrocheck.core.utils.api.dto.SimpleResponseDto;
import com.agrocheck.core.utils.auth.dto.AuthRequestDto;
import com.agrocheck.core.utils.auth.dto.AuthResponseDto;
import com.agrocheck.core.utils.auth.jwt_config.JwtTokenUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class AuthController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserService userService;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private AuthUserServiceImpl authUserService;

    @Autowired
    private JwtTokenUtil jwtUtil;

    @PostMapping("/signup")
    private ResponseEntity<?> createUser(@RequestBody CreateUserDto createUserDto){
        User newUser = userService.save(createUserDto);
        UserDto responseObj = modelMapper.map(newUser, UserDto.class);
        return ResponseEntity.ok(new SimpleResponseDto<UserDto>(true, responseObj));
    }

    @PostMapping("/authenticate")
    private ResponseEntity<?> authenticateUser(@RequestBody AuthRequestDto authRequestDto){
        try{
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequestDto.getUsername(), authRequestDto.getPassword()));
        }catch(BadCredentialsException e){
            throw e;
        }
        final UserDetails userDetails = authUserService.loadUserByUsername(authRequestDto.getUsername());
        final String jwt = jwtUtil.generateToken(userDetails);
        return ResponseEntity.ok(new AuthResponseDto(jwt));
    }
}
