package com.example.ingress_task.dao.repository;

import com.example.ingress_task.dao.entity.RoleEntity;
import com.example.ingress_task.dao.entity.enums.ERole;
import org.springframework.data.jpa.repository.JpaRepository;


public interface  RoleRepository extends JpaRepository<RoleEntity, Integer> {
    RoleEntity findByName(ERole name);

}
