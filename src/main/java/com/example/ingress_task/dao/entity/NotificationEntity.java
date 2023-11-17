package com.example.ingress_task.dao.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "notifications")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class NotificationEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "sender_id", referencedColumnName = "id")
    private UserEntity sender;

    @ManyToOne
    @JoinColumn(name = "recipient_id", referencedColumnName = "id")
    private UserEntity recipient;

    private String message;

    @Column(name = "created_at")
    private LocalDateTime createdAt;
}
