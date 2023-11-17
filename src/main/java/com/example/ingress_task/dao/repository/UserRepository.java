package com.example.ingress_task.dao.repository;

import com.example.ingress_task.dao.entity.RoleEntity;
import com.example.ingress_task.dao.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Integer> {
    Optional<UserEntity> findUserByEmail(String email);

    @Query("SELECT u FROM BookEntity b JOIN b.users u JOIN u.roles r WHERE b.id = :bookId AND r.name = 'STUDENT'")
    List<UserEntity> findStudentsByBookId(@Param("bookId") Integer bookId);

    List<UserEntity> findByRoles(RoleEntity role);

    Optional<UserEntity> findByEmail(String email);


}
