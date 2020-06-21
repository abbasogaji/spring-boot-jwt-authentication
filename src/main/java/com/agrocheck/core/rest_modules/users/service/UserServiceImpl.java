package com.agrocheck.core.rest_modules.users.service;

import com.agrocheck.core.rest_modules.users.dto.CreateUserDto;
import com.agrocheck.core.rest_modules.users.model.User;
import com.agrocheck.core.rest_modules.users.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;



    @Override
    public User save(CreateUserDto createUserDto){
        User userObj = new User();
        userObj.setName(createUserDto.getName());
        userObj.setUsername(createUserDto.getUsername());
        userObj.setPassword(passwordEncoder.encode(createUserDto.getPassword()));
        return userRepository.save(userObj);
    }


    @Override
    public List<User> findAll(){
        return userRepository.findAll();
    }


}
