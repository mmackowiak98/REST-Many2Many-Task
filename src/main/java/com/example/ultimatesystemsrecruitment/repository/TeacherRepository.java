package com.example.ultimatesystemsrecruitment.repository;

import com.example.ultimatesystemsrecruitment.model.entity.Teacher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher,Long> {
    Page<Teacher> getByOrderByIdAsc(Pageable pageable);

    Optional<Teacher> findBySurnameIgnoreCase(String surname);

    Optional<Teacher> findByNameIgnoreCaseAndSurnameIgnoreCase(String name, String surname);


}
