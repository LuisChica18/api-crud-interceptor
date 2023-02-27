package com.example.apicrudinterceptor.domain.service.impl;

import com.example.apicrudinterceptor.domain.dto.UserDto;
import com.example.apicrudinterceptor.domain.service.UserService;
import com.example.apicrudinterceptor.exception.ApiRestException;
import com.example.apicrudinterceptor.persistence.entity.UserEntity;
import com.example.apicrudinterceptor.persistence.mapper.UserMapper;
import com.example.apicrudinterceptor.persistence.repository.UserRepository;
import com.example.apicrudinterceptor.util.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository repository;

    @Autowired
    private UserMapper mapper;

    /**
     * List all users
     *
     * @return List<UserDto>
     * @author Joan Nieto
     */
    @Override
    public List<UserDto> findAll() {
        List<UserEntity> users = repository.findAll();
        return new ArrayList<>(users).stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

    /**
     * Find user by id
     *
     * @param id Long
     * @return UserDto
     * @author Joan Nieto
     */
    @Override
    public UserDto findById(Long id) {
        return repository.findById(id)
                .map(mapper::toDto)
                .orElseThrow(() -> new ApiRestException(
                        Constant.ERROR_NOT_FOUND,
                        HttpStatus.NOT_FOUND,
                        Constant.USER_NOT_FOUND));
    }

    /**
     * Save user
     *
     * @param userDto UserDto
     * @return UserDto
     * @author Joan Nieto
     */
    @Override
    public UserDto save(UserDto userDto) {
        return mapper.toDto(repository.save(mapper.toEntity(userDto)));
    }

    /**
     * Update user
     *
     * @param userDto UserDto
     * @return UserDto
     * @author Joan Nieto
     */
    @Override
    public UserDto update(UserDto userDto) {
        return repository.findById(userDto.getUserId())
                .map(user -> {
                    user.setName(userDto.getName() != null
                            ? userDto.getName()
                            : user.getName());
                    user.setLastName(userDto.getLastName() != null
                            ? userDto.getLastName()
                            : user.getLastName());
                    user.setEmail(userDto.getEmail() != null
                            ? userDto.getEmail()
                            : user.getEmail());
                    user.setPhone(userDto.getPhone() != null
                            ? userDto.getPhone()
                            : user.getPhone());
                    user.setUpdatedAt(new Date());
                    return mapper.toDto(repository.save(user));
                })
                .orElseThrow(() -> new ApiRestException(
                        Constant.ERROR_NOT_FOUND,
                        HttpStatus.NOT_FOUND,
                        Constant.USER_NOT_FOUND));
    }

    /**
     * Delete user
     *
     * @param id Long
     * @author Joan Nieto
     */
    @Override
    public void delete(Long id) {
        if (repository.findById(id).isEmpty()) throw new ApiRestException(
                Constant.ERROR_NOT_FOUND,
                HttpStatus.NOT_FOUND,
                Constant.USER_NOT_FOUND);
        repository.deleteById(id);
    }
}
