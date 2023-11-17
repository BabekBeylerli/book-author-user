package com.example.ingress_task.service;

import com.example.ingress_task.dao.entity.UserEntity;
import com.example.ingress_task.dao.repository.UserRepository;
import com.example.ingress_task.mapper.UserMapper;
import com.example.ingress_task.model.UserDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public List<UserDto> findStudentsByBookId(Integer bookId) {
        List<UserEntity> students = userRepository.findStudentsByBookId(bookId);
        return userMapper.mapEntityToDtos(students);
    }
    public UserEntity findById(Integer userId) {
        return userRepository.findById(userId).orElse(null);
    }
}
