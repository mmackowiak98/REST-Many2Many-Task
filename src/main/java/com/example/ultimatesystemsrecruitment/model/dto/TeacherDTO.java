package com.example.ultimatesystemsrecruitment.model.dto;

import com.example.ultimatesystemsrecruitment.enums.Subject;
import lombok.Data;



@Data
public class TeacherDTO {
    private Long id;
    private String name;
    private String surname;
    private String email;
    private int age;
    private Subject subject;
}
