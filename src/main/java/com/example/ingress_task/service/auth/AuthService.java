package com.example.ingress_task.service.auth;

import com.example.ingress_task.dao.entity.UserEntity;
import com.example.ingress_task.dao.repository.UserRepository;
import com.example.ingress_task.mapper.UserMapper;
import com.example.ingress_task.model.auth.AuthRequestDto;
import com.example.ingress_task.model.auth.AuthenticationDto;
import com.example.ingress_task.model.auth.UserRegisterRequestDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authManager;
    public void register(UserRegisterRequestDto requestDto) {
        var user = UserRegisterRequestDto.builder()
                .email(requestDto.getEmail())
                .name(requestDto.getName())
                .age(requestDto.getAge())
                .password(passwordEncoder.encode(requestDto.getPassword()))
                .roles(requestDto.getRoles())
                .build();

        userRepository.save(UserMapper.mapper.mapRegisterRequestDtoToEntity(user));
    }

    public AuthenticationDto authenticate(AuthRequestDto authRequestDto) {
        try {
            authManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            authRequestDto.getEmail(),
                            authRequestDto.getPassword()
                    )
            );
        } catch (AuthenticationException ex) {
            ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Authentication failed.");
        }

        UserEntity user = userRepository.findUserByEmail(authRequestDto.getEmail()).orElseThrow();
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationDto.builder()
                .token(jwtToken)
                .build();
    }


    public void deleteUser(Integer userId) {
        log.info("ActionLog.deleteUser.start");
        userRepository.deleteById(userId);
        log.info("ActionLog.deleteUser.end");
    }

    public static UserEntity getUser() {
        return (UserEntity) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}

