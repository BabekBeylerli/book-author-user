package com.example.ingress_task.mapper;

import com.example.ingress_task.dao.entity.RoleEntity;
import com.example.ingress_task.model.auth.RoleDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface RoleMapper  {
   RoleMapper mapper = Mappers.getMapper(RoleMapper.class);
     RoleEntity mapDtoToEntity(RoleDto roleDto);
     RoleDto mapEntityToDto(RoleEntity roleEntity);


}

