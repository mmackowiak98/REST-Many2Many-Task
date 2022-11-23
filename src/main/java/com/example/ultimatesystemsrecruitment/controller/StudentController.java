package com.example.ultimatesystemsrecruitment.controller;

import com.example.ultimatesystemsrecruitment.model.dto.SearchDTO;
import com.example.ultimatesystemsrecruitment.model.dto.StudentDTO;
import com.example.ultimatesystemsrecruitment.model.entity.Student;
import com.example.ultimatesystemsrecruitment.model.entity.Teacher;
import com.example.ultimatesystemsrecruitment.service.student.StudentServiceImpl;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import java.util.Set;

@RestController
@RequestMapping("/api/")
@AllArgsConstructor
public class StudentController {

    StudentServiceImpl studentService;
    ModelMapper modelMapper;


    @PostMapping("/save/student")
    @ResponseStatus(HttpStatus.CREATED)
    public Student saveStudent(@Valid @RequestBody StudentDTO studentDTO){
        Student mappedStudent = modelMapper.map(studentDTO,Student.class);
        return studentService.saveEntity(mappedStudent);
    }

    @GetMapping("/get/students")
    @ResponseStatus(HttpStatus.OK)
    public Page<Student> getAllStudents(Pageable pageable){
        return studentService.getAll(pageable);
    }

    @PostMapping("/get/student")
    @ResponseStatus(HttpStatus.OK)
    public Student findStudent(@RequestBody SearchDTO dto){
        return studentService.findStudent(dto.getName(),dto.getSurname())
                .orElseThrow(EntityNotFoundException::new);
    }

    @PostMapping("/get/student/teachers")
    @ResponseStatus(HttpStatus.OK)
    public Set<Teacher> findStudentTeachers(@RequestBody SearchDTO dto){
        Student student = studentService.findStudentTeachers(dto.getName(), dto.getSurname())
                .orElseThrow(EntityNotFoundException::new);
        return student.getTeacherSet();
    }


    @PutMapping("/put/student/{studentSurname}/{teacherSurname}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Student addStudentToTeacher(@PathVariable String studentSurname, @PathVariable String teacherSurname) {
        return studentService.addTeacherToStudent(studentSurname,teacherSurname);
    }

    @DeleteMapping("/delete/student/{studentSurname}/{teacherSurname}")
    @ResponseStatus(HttpStatus.OK)
    public Student removeTeacherFromStudent(@PathVariable String studentSurname,@PathVariable String teacherSurname){
        return studentService.removeTeacherFromStudent(studentSurname,teacherSurname);
    }

    @DeleteMapping("/delete/student")
    @ResponseStatus(HttpStatus.OK)
    public void removeTeacher(@Valid @RequestBody StudentDTO studentDTO){
        Student mappedStudent = modelMapper.map(studentDTO,Student.class);
        studentService.removeStudent(mappedStudent);
    }
}
