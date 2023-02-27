package com.example.apicrudinterceptor.persistence.mapper;

import com.example.apicrudinterceptor.domain.dto.UserDto;
import com.example.apicrudinterceptor.persistence.entity.UserEntity;

public interface UserMapper {
    UserDto toDto(UserEntity entity);
    UserEntity toEntity(UserDto dto);
}
