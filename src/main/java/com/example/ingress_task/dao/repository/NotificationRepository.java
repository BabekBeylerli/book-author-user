package com.example.ingress_task.dao.repository;

import com.example.ingress_task.dao.entity.NotificationEntity;
import com.example.ingress_task.dao.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotificationRepository extends JpaRepository<NotificationEntity, Integer> {
    List<NotificationEntity> findByRecipient(UserEntity recipient);

}

