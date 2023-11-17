package com.example.ingress_task.mapper;

import com.example.ingress_task.dao.entity.UserEntity;
import com.example.ingress_task.model.UserDto;
import com.example.ingress_task.model.auth.UserRegisterRequestDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserMapper mapper = Mappers.getMapper(UserMapper.class);

    UserDto mapEntityToDto(UserEntity studentEntity);

    UserEntity mapRegisterRequestDtoToEntity(UserRegisterRequestDto userRegisterRequestDto);

    UserEntity mapDtoToEntity(UserDto studentDto);

    UserEntity mapDtoToEntity(UserDto studentDto, Integer studentId);

    List<UserDto> mapEntityToDtos(List<UserEntity> studentEntities);

}
