package com.example.ultimatesystemsrecruitment.model.dto;

import com.example.ultimatesystemsrecruitment.enums.FieldOfStudy;
import com.example.ultimatesystemsrecruitment.model.entity.Teacher;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
public class StudentDTO {
    private Long id;
    private String name;
    private String surname;
    private String email;
    private int age;
    private FieldOfStudy field;
    private Set<Teacher> teacherSet = new HashSet<>();
}
