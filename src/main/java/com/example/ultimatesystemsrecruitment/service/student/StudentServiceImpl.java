package com.example.ultimatesystemsrecruitment.service.student;

import com.example.ultimatesystemsrecruitment.model.entity.Student;
import com.example.ultimatesystemsrecruitment.model.entity.Teacher;
import com.example.ultimatesystemsrecruitment.repository.StudentRepository;
import com.example.ultimatesystemsrecruitment.repository.TeacherRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
@AllArgsConstructor
@Transactional
public class StudentServiceImpl implements StudentService {
    StudentRepository studentRepository;
    TeacherRepository teacherRepository;

    private final EntityManager entityManager;

    @Override
    public Student saveEntity(Student student) {
        return studentRepository.save(student);
    }
    @Override
    public Page<Student> getAll(Pageable pageable) {
        return studentRepository.getByOrderByIdAsc(pageable);
    }
    @Override
    public Student addTeacherToStudent(String studentSurname, String teacherSurname) {
        try {
            Teacher foundTeacher = teacherRepository.findBySurnameIgnoreCase(teacherSurname)
                    .orElseThrow(EntityNotFoundException::new);
            Student foundStudent = studentRepository.findBySurnameIgnoreCase(studentSurname)
                    .orElseThrow(EntityNotFoundException::new);

            foundStudent.getTeacherSet().add(foundTeacher);
            return entityManager.merge(foundStudent);
        }catch (EntityNotFoundException e) {
            e.getMessage();
        }
        return null;
    }
    @Override
    public Student removeTeacherFromStudent(String studentSurname, String teacherSurname) {
        try {
            Teacher foundTeacher = teacherRepository.findBySurnameIgnoreCase(teacherSurname)
                    .orElseThrow(EntityNotFoundException::new);

            Student foundStudent = studentRepository.findBySurnameIgnoreCase(studentSurname)
                    .orElseThrow(EntityNotFoundException::new);


            Set<Teacher> operatedSet = new HashSet<>();

            foundStudent.getTeacherSet()
                    .stream()
                    .filter(t -> t.equals(foundTeacher))
                    .forEach(operatedSet::add);

            foundStudent.getTeacherSet().removeAll(operatedSet);
            return entityManager.merge(foundStudent);
        } catch (EntityNotFoundException e) {
            e.getMessage();
        }
        return null;
    }
    @Override
    public void removeStudent(String name, String surname) {
        Student studentToDelete = studentRepository.findByNameIgnoreCaseAndSurnameIgnoreCase(name, surname)
                .orElseThrow(EntityNotFoundException::new);
        studentRepository.delete(studentToDelete);
    }
    @Override
    public Optional<Student> findStudent(String name, String surname) {
        return studentRepository.findByNameIgnoreCaseAndSurnameIgnoreCase(name, surname);

    }
    @Override
    public Optional<Student> findStudentTeachers(String name, String surname) {
        return studentRepository.findByNameIgnoreCaseAndSurnameIgnoreCase(name, surname);
    }
}


