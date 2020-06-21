package com.agrocheck.core.rest_modules.users.service;

import com.agrocheck.core.rest_modules.users.dto.CreateUserDto;
import com.agrocheck.core.rest_modules.users.model.User;

import java.util.List;

public interface UserService {
    public User save(CreateUserDto user);
    public List<User> findAll();
}
