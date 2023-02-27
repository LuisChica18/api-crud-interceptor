package com.example.apicrudinterceptor.domain.service;

import com.example.apicrudinterceptor.domain.dto.UserDto;

import java.util.List;

public interface UserService {

    List<UserDto> findAll();
    UserDto findById(Long id);
    UserDto save(UserDto userDto);
    UserDto update(UserDto userDto);
    void delete(Long id);
}
