package com.example.ingress_task.service;

import com.example.ingress_task.dao.entity.UserEntity;
import com.example.ingress_task.dao.repository.UserRepository;
import com.example.ingress_task.dao.entity.enums.ERole;
import com.example.ingress_task.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SubscriptionService {
    private final UserRepository userRepository;

    public void subscribeToAuthor(Integer authorId) {
        // Aktif kullanıcının kim olduğunu al
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        // Eğer kimlik doğrulama gerçekleşmişse, aktif kullanıcıyı al
        if (authentication != null && authentication.isAuthenticated()) {
            String currentUsername = authentication.getName();

            // Aktif kullanıcıyı veritabanından al
            UserEntity subscriber = userRepository.findByEmail(currentUsername)
                    .orElseThrow(() -> new NotFoundException("Aktif kullanıcı bulunamadı."));

            // Veritabanından belirtilen `authorId` ile ilgili yazarı bul
            UserEntity author = userRepository.findById(authorId)
                    .orElseThrow(() -> new NotFoundException("Yazar bulunamadı."));

            // Eğer aktif kullanıcı STUDENT rolüne sahipse, AUTHOR rolüne sahip kullanıcıya abone olabilir
            if (subscriber.getRoles().stream().anyMatch(role -> role.getName() == ERole.STUDENT)
                    && author.getRoles().stream().anyMatch(role -> role.getName() == ERole.AUTHOR)) {
                author.getSubscribers().add(subscriber);
                userRepository.save(author);
            } else {
                throw new NotFoundException("Kullanıcılar belirtilen rolleri taşımıyor.");
            }
        } else {
            throw new NotFoundException("Kimlik doğrulama gerçekleştirilmemiş.");
        }
    }
}
