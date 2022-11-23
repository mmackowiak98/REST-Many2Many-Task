package com.example.ultimatesystemsrecruitment.model.entity;

import com.example.ultimatesystemsrecruitment.enums.FieldOfStudy;
import com.example.ultimatesystemsrecruitment.validation.EmailConstraint;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;

import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Student {
    @Id
    @GeneratedValue
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;
    @Size(min = 3)
    private String name;
    private String surname;
    @EmailConstraint
    private String email;
    @Min(value = 18)
    private int age;

    @Enumerated(EnumType.STRING)
    private FieldOfStudy field;


    @ManyToMany(cascade = {CascadeType.MERGE})
    @Fetch(value = FetchMode.SELECT)
    @JoinTable(
            name = "teacher_student",
            joinColumns = @JoinColumn(name = "teacher_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "student_id", referencedColumnName = "id")
    )
    private Set<Teacher> teacherSet = new HashSet<>();



}
