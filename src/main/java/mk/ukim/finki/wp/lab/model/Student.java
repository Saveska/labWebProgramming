package mk.ukim.finki.wp.lab.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Entity
public class Student {
    @Id
    private String username;
    private String password;
    private String name;
    private String surname;
    private Boolean newStudent;


    public Student() {
    }

    public Student(String username, String password, String name, String surname, Boolean newStudent) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.newStudent = newStudent;
    }

    public Student(String username) {
        this.username = username;
    }
}
