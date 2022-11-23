package com.example.ultimatesystemsrecruitment.service.student;

import com.example.ultimatesystemsrecruitment.model.entity.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface StudentService {
    Student saveEntity(Student student);

    Page<Student> getAll(Pageable pageable);

    Student addTeacherToStudent(String studentSurname, String teacherSurname);

    Student removeTeacherFromStudent(String studentSurname, String teacherSurname);

    void removeStudent(String name, String surname);

    Optional<Student> findStudent(String name, String surname);

    Optional<Student> findStudentTeachers(String name, String surname);
}
