package com.example.ingress_task.dao.repository;

import com.example.ingress_task.dao.entity.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface BookRepository extends JpaRepository<BookEntity, Integer> {
    @Query("SELECT b FROM UserEntity u JOIN u.roles r JOIN u.books b WHERE u.id = :userId AND r.name = 'STUDENT'")
    List<BookEntity> findBooksByUserId(@Param("userId") Integer userId);


}
