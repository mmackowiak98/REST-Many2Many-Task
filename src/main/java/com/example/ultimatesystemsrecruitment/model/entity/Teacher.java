package com.example.ultimatesystemsrecruitment.model.entity;

import com.example.ultimatesystemsrecruitment.enums.Subject;
import com.example.ultimatesystemsrecruitment.validation.EmailConstraint;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
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
public class Teacher {
    @Id
    @GeneratedValue
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;
    @Size(min = 3)
    private String name;
    private String surname;
    @EmailConstraint
    private String email;
    @Min(value = 19)
    private int age;

    @Enumerated(EnumType.STRING)
    private Subject subject;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.MERGE, mappedBy = "teacherSet")
    @Fetch(value = FetchMode.SELECT)
    @JsonIgnore // to avoid recursion
    private Set<Student> studentSet = new HashSet<>();
    
}
