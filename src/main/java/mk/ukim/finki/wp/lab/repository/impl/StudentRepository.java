//package mk.ukim.finki.wp.lab.repository.impl;
//
//import mk.ukim.finki.wp.lab.bootstrap.DataHolder;
//import mk.ukim.finki.wp.lab.model.Student;
//import org.springframework.stereotype.Repository;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.stream.Collectors;
//
//@Repository
//public class StudentRepository {
//   // private List<Student> students;
//
//
//    public List<Student> findAllStudents(){
//        return DataHolder.students;
//    }
//
//    public List<Student> findAllByNameOrSurname(String text){
//        //text = ime ili prezime
//        return DataHolder.students.stream().filter(student -> student.getName().contains(text) || student.getSurname().contains(text))
//                .collect(Collectors.toList());
//    }
//
//    public Student findByUsername(String username) {
//        try{
//            if (DataHolder.students.stream().filter(i -> i.getUsername().equals(username)).findFirst().isEmpty()) {
//                throw new RuntimeException();
//            }
//            return DataHolder.students.stream().filter(i -> i.getUsername().equals(username)).findFirst().get();
//
//        }catch (Exception e){
//            return null;
//        }
//    }
//
//    public Student save(Student student){
//        if(student == null || student.getName() == null || student.getName().isEmpty()){
//            return null;
//        }
//        if(DataHolder.students.stream().noneMatch(s -> s.getUsername().equals(student.getUsername())))
//            DataHolder.students.add(student);
//        return student;
//    }
//
//}
