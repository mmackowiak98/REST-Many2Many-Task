package com.example.ultimatesystemsrecruitment.service.teacher;

import com.example.ultimatesystemsrecruitment.model.entity.Teacher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface TeacherService {

    Teacher saveEntity(Teacher teacher);
    void removeTeacher(String name, String surname);
    Page<Teacher> getAll(Pageable pageable);
    Optional<Teacher> findTeacher(String name, String surname);
    Optional<Teacher> findTeacherStudents(String name, String surname);
    Teacher addStudentToTeacher(String studentSurname, String teacherSurname);
    Teacher removeStudentFromTeacher(String studentSurname, String teacherSurname);


}
