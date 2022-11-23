package com.example.ultimatesystemsrecruitment.repository;

import com.example.ultimatesystemsrecruitment.model.entity.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    Page<Student> getByOrderByIdAsc(Pageable pageable);

    Optional<Student> findBySurnameIgnoreCase(String surname);

    Optional<Student> findByNameIgnoreCaseAndSurnameIgnoreCase(String name, String surname);






}
