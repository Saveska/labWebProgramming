package mk.ukim.finki.wp.lab.service;

import mk.ukim.finki.wp.lab.model.Course;
import mk.ukim.finki.wp.lab.model.Grade;
import mk.ukim.finki.wp.lab.model.Student;
import mk.ukim.finki.wp.lab.model.exceptions.CourseAlreadyExists;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Set;


public interface CourseService {
    Set<Student> listStudentsByCourse(Long courseId);
    void addStudentInCourse(String username, Long courseId);
    List<Course> listAll();
    String courseNameFromId(Long courseId);
    Course editCourse(Long id, String name, String description, Long teacherId);
    Course addNewCourse(String name, String description, Long teacherId) throws CourseAlreadyExists;
    Optional<Course> getCourseFromId(Long id);
    void deleteCourseById(Long id);
    List<Grade> getGradesForCourse(Long courseId);
    void addGrade(Character grade, LocalDateTime time, Long courseId, String username);
}
