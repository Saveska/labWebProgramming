package mk.ukim.finki.wp.lab.service.impl;

import mk.ukim.finki.wp.lab.model.Student;
//import mk.ukim.finki.wp.lab.repository.impl.StudentRepository;
import mk.ukim.finki.wp.lab.model.exceptions.InsufficientInfoForNewStudentException;
import mk.ukim.finki.wp.lab.model.exceptions.UsernameAlreadyExistsException;
import mk.ukim.finki.wp.lab.repository.jpa.StudentRepositoryDB;
import mk.ukim.finki.wp.lab.service.StudentService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepositoryDB studentRepository;

    public StudentServiceImpl(StudentRepositoryDB studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public List<Student> listAll() {
        return studentRepository.findAll();
    }

    @Override
    public List<Student> searchByNameOrSurname(String text) {
        return studentRepository.findAllByNameOrSurname(text,text);
    }

//    @Override
//    public Student save(String username, String password, String name, String surname) {
////        if(studentRepository.findByUsername(username) == null)
//        return studentRepository.save(new Student(username,password,name,surname,Boolean.TRUE));
//    }

    @Override
    public Student save(String username, String password, String name, String surname) throws InsufficientInfoForNewStudentException{

        if (Objects.equals(username, "") || Objects.equals(name, "") || Objects.equals(surname, "") || Objects.equals(password, "")) {
            throw new InsufficientInfoForNewStudentException();
        }
        if (studentRepository.existsByUsername(username)){
            throw new UsernameAlreadyExistsException();
        }
        return studentRepository.save(new Student(username, password, name, surname, Boolean.TRUE));


    }
}
