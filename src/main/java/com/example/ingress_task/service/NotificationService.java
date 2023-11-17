package com.example.ingress_task.service;

import com.example.ingress_task.dao.entity.NotificationEntity;
import com.example.ingress_task.dao.entity.UserEntity;
import com.example.ingress_task.dao.repository.NotificationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class NotificationService {
    private final NotificationRepository notificationRepository;
    private final EmailService emailService;


    public void saveNotification(NotificationEntity notification) {
        notificationRepository.save(notification);
    }
    public List<NotificationEntity> getUserNotifications(UserEntity user) {
        return notificationRepository.findByRecipient(user);
    }


    public void notifySubscribersAboutNewBook(UserEntity author, String bookTitle) {
        List<UserEntity> subscribers = author.getSubscribers();
        for (UserEntity subscriber : subscribers) {
            String message = "Yeni bir kitap yayınlandı: " + bookTitle;
            NotificationEntity notification = new NotificationEntity();
            notification.setSender(author);
            notification.setRecipient(subscriber);
            notification.setMessage(message);
            saveNotification(notification);
        }
    }

}

