package com.example.ingress_task.model.auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserRegisterRequestDto {
    private String name;
    private Integer age;
    private String email;
    private String password;
    private Set<RoleDto> roles;

}
