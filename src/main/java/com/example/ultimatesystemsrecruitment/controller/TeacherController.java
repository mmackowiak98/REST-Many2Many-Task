package com.example.ultimatesystemsrecruitment.controller;

import com.example.ultimatesystemsrecruitment.model.dto.SearchDTO;
import com.example.ultimatesystemsrecruitment.model.dto.TeacherDTO;
import com.example.ultimatesystemsrecruitment.model.entity.Student;
import com.example.ultimatesystemsrecruitment.model.entity.Teacher;
import com.example.ultimatesystemsrecruitment.service.teacher.TeacherServiceImpl;
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
public class TeacherController {

    TeacherServiceImpl teacherService;
    ModelMapper modelMapper;

    @PostMapping("/save/teacher")
    @ResponseStatus(HttpStatus.CREATED)
    public Teacher saveStudent(@Valid @RequestBody TeacherDTO teacherDTO){
        Teacher mappedTeacher = modelMapper.map(teacherDTO,Teacher.class);
        return teacherService.saveEntity(mappedTeacher);
    }

    @GetMapping("/get/teachers")
    @ResponseStatus(HttpStatus.OK)
    public Page<Teacher> getAllTeachers(Pageable pageable){
            return teacherService.getAll(pageable);
    }

    @PostMapping("/get/teacher")
    @ResponseStatus(HttpStatus.OK)
    public Teacher findTeacher(@RequestBody SearchDTO dto){
        return teacherService.findTeacher(dto.getName(),dto.getSurname())
                .orElseThrow(EntityNotFoundException::new);
    }

    @PostMapping("/get/teacher/students")
    @ResponseStatus(HttpStatus.OK)
    public Set<Student> findTeacherStudents(@RequestBody SearchDTO dto){
        Teacher teacher = teacherService.findTeacherStudents(dto.getName(), dto.getSurname())
                .orElseThrow(EntityNotFoundException::new);
        return teacher.getStudentSet();
    }


    @PutMapping("/put/teacher/{studentSurname}/{teacherSurname}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Teacher addStudentToTeacher(@PathVariable String studentSurname,@PathVariable String teacherSurname){
        return teacherService.addStudentToTeacher(studentSurname,teacherSurname);
    }

    @DeleteMapping("/delete/teacher/{studentSurname}/{teacherSurname}")
    @ResponseStatus(HttpStatus.OK)
    public Teacher removeStudentFromTeacher(@PathVariable String studentSurname,@PathVariable String teacherSurname){
        return teacherService.removeStudentFromTeacher(studentSurname,teacherSurname);
    }

    @DeleteMapping("/delete/teacher")
    @ResponseStatus(HttpStatus.OK)
    public void removeTeacher(@RequestBody SearchDTO dto){
        teacherService.removeTeacher(dto.getName(),dto.getSurname());
    }



}
