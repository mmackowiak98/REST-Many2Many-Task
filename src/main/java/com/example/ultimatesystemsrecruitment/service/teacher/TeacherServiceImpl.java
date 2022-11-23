package com.example.ultimatesystemsrecruitment.service.teacher;

import com.example.ultimatesystemsrecruitment.model.entity.Student;
import com.example.ultimatesystemsrecruitment.model.entity.Teacher;
import com.example.ultimatesystemsrecruitment.repository.StudentRepository;
import com.example.ultimatesystemsrecruitment.repository.TeacherRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;


@AllArgsConstructor
@Service
@Transactional
public class TeacherServiceImpl implements TeacherService {
    TeacherRepository teacherRepository;
    StudentRepository studentRepository;
    @Override
    public Teacher saveEntity(Teacher teacher) {
        return teacherRepository.save(teacher);
    }
    @Override
    public Page<Teacher> getAll(Pageable pageable) {
        return teacherRepository.getByOrderByIdAsc(pageable);
    }
    @Override
    public Teacher addStudentToTeacher(String studentSurname, String teacherSurname) {
        try {
            Teacher foundTeacher = teacherRepository.findBySurnameIgnoreCase(teacherSurname)
                    .orElseThrow(EntityNotFoundException::new);
            Student foundStudent = studentRepository.findBySurnameIgnoreCase(studentSurname)
                    .orElseThrow(EntityNotFoundException::new);

            foundTeacher.getStudentSet().add(foundStudent);
            return teacherRepository.save(foundTeacher);
        }catch(EntityNotFoundException e){
            e.getMessage();
        }
        return null;
    }
    @Override
    public Teacher removeStudentFromTeacher(String studentSurname, String teacherSurname) {

        try{
        Teacher foundTeacher = teacherRepository.findBySurnameIgnoreCase(teacherSurname)
                .orElseThrow(EntityNotFoundException::new);

        Student foundStudent = studentRepository.findBySurnameIgnoreCase(studentSurname)
                .orElseThrow(EntityNotFoundException::new);

        Set<Student> operatedSet = new HashSet<>();

        foundTeacher.getStudentSet()
                .stream()
                .filter(s -> s.equals(foundStudent))
                .forEach(operatedSet::add);

        foundTeacher.getStudentSet().removeAll(operatedSet);

        return teacherRepository.save(foundTeacher);
        }catch(EntityNotFoundException e){
            e.getMessage();
        }
        return null;

    }
    @Override
    public void removeTeacher(Teacher teacher) {
        teacherRepository.delete(teacher);
    }
    @Override
    public Optional<Teacher> findTeacher(String name, String surname) {
        return teacherRepository.findByNameIgnoreCaseAndSurnameIgnoreCase(name,surname);

    }
    @Override
    public Optional<Teacher> findTeacherStudents(String name, String surname) {
        return teacherRepository.findByNameIgnoreCaseAndSurnameIgnoreCase(name, surname);

    }
}
