package mk.ukim.finki.wp.lab.service.impl;

//import mk.ukim.finki.wp.lab.bootstrap.DataHolder;
import mk.ukim.finki.wp.lab.model.Course;
import mk.ukim.finki.wp.lab.model.Grade;
import mk.ukim.finki.wp.lab.model.Student;
import mk.ukim.finki.wp.lab.model.exceptions.CourseAlreadyExists;
import mk.ukim.finki.wp.lab.model.exceptions.CourseDoesntExistException;
//import mk.ukim.finki.wp.lab.repository.impl.StudentRepository;
//import mk.ukim.finki.wp.lab.repository.impl.TeacherRepository;
import mk.ukim.finki.wp.lab.repository.jpa.CourseRepositoryDB;
import mk.ukim.finki.wp.lab.repository.jpa.GradeRepositoryDB;
import mk.ukim.finki.wp.lab.repository.jpa.StudentRepositoryDB;
import mk.ukim.finki.wp.lab.repository.jpa.TeacherRepositoryDB;
import mk.ukim.finki.wp.lab.service.CourseService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class CourseServiceImpl implements CourseService {

//    private final CourseRepository courseRepository;
    private final StudentRepositoryDB studentRepository;
    private final TeacherRepositoryDB teacherRepository;

    private final CourseRepositoryDB courseRepository;
    private final GradeRepositoryDB gradeRepository;

    public CourseServiceImpl(CourseRepositoryDB courseRepository, StudentRepositoryDB studentRepository, TeacherRepositoryDB teacherRepository,GradeRepositoryDB gradeRepository) {
        this.courseRepository = courseRepository;
        this.studentRepository = studentRepository;
        this.teacherRepository = teacherRepository;
        this.gradeRepository = gradeRepository;
    }

    @Override
    public Set<Student> listStudentsByCourse(Long courseId) {
        return courseRepository.findById(courseId).get().getStudents();
    }

    @Override
    @Transactional
    public void addStudentInCourse(String username, Long courseId) {
        courseRepository.findById(courseId).orElseThrow(CourseDoesntExistException::new).getStudents().add(studentRepository.findByUsername(username));
    }

    @Override
    public List<Course> listAll() {
        return courseRepository.findAll();
    }

    @Override
    public String courseNameFromId(Long courseId) {
        Course newCourse = courseRepository.findAll().stream()
                .filter(course -> course.getCourseId().equals(courseId))
                .findFirst()
                .orElse(null);
        return newCourse.getName();
    }

    @Override
    @Transactional
    public Course editCourse(Long id, String name, String description, Long teacherId){
       // return courseRepository.editCourse(id,name,description,teacherRepository.findById(teacherId));
        Course course = courseRepository.findById(id).orElseThrow(CourseDoesntExistException::new);
        course.setName(name);
        course.setDescription(description);
        course.setTeacher(teacherRepository.findById(teacherId).get());
        return course;
    }

    @Override
    public Course addNewCourse(String name, String description, Long teacherId) throws CourseAlreadyExists {
        return courseRepository.save(new Course(name,description,new HashSet<>(),teacherRepository.findById(teacherId).get()));
    }

    @Override
    public Optional<Course> getCourseFromId(Long id){
        return courseRepository.findById(id);
    }

    @Override
    public void deleteCourseById(Long id) {
        courseRepository.deleteById(id);
    }

    @Override
    public List<Grade> getGradesForCourse(Long courseId){
        return gradeRepository.getGradesByCourse(courseRepository.findById(courseId).get());
    }

    @Override
    public void addGrade(Character grade, LocalDateTime time, Long courseId, String username) {
        gradeRepository.save(new Grade(grade,studentRepository.getReferenceById(username),courseRepository.getReferenceById(courseId),time));
    }
}

