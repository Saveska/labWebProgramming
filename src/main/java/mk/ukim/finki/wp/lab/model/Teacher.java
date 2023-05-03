package mk.ukim.finki.wp.lab.model;

import lombok.Data;
import mk.ukim.finki.wp.lab.model.converter.TeacherFullnameConverter;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Entity
public class Teacher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
//    private String name;
//    private String surname;
    @Convert(converter = TeacherFullnameConverter.class)
    private TeacherFullname teacherFullname;

    private LocalDate dateOfEmpolyment;

    public Teacher() {
    }

    public Teacher(TeacherFullname teacherFullname) {
        this.teacherFullname = teacherFullname;
    }
    public Teacher(String name, String surname) {
        this.teacherFullname = new TeacherFullname();
        this.teacherFullname.setName(name);
        this.teacherFullname.setSurname(surname);
    }
    public String getName(){
        return teacherFullname.getName();
    }
    public String getSurname(){
        return teacherFullname.getSurname();
    }

    //    public Teacher(String name, String surname) {
//        this.name = name;
//        this.surname = surname;
//    }


}
