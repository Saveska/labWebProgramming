package mk.ukim.finki.wp.lab.model;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@Entity
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long courseId;
    private String name;
    private String description;
    @ManyToMany
    private Set<Student> students;
    @ManyToOne
    private Teacher teacher;

    @Enumerated(EnumType.STRING)
    private Type type;

    public Course() {
    }

//
//    public Course(String name, String description, Teacher teacher) {
//        this.students = new HashSet<>();
//        this.name = name;
//        this.description = description;
//        this.teacher = teacher;
//    }

    public Course(Long courseId) {
        this.courseId = courseId;
    }

    public Course(String name, String description, Set<Student> students, Teacher teacher) {
        this.name = name;
        this.description = description;
        this.students = students;
        this.teacher = teacher;
    }

}
