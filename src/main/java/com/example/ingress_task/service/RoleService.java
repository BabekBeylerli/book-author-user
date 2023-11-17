package com.example.ingress_task.service;

import com.example.ingress_task.dao.repository.RoleRepository;
import com.example.ingress_task.mapper.RoleMapper;
import com.example.ingress_task.model.auth.RoleDto;
import org.springframework.stereotype.Service;

@Service
public class RoleService {
    private final RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public void saveRole(RoleDto roleDto) {
        roleRepository.save(RoleMapper.mapper.mapDtoToEntity(roleDto));
    }

}
