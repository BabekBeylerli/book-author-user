package com.example.ingress_task.model;

import com.example.ingress_task.dao.entity.BookEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class UserDto {
    private String name;
    private Integer age;

}
