//package mk.ukim.finki.wp.lab.repository.impl;
//
//import mk.ukim.finki.wp.lab.bootstrap.DataHolder;
//import mk.ukim.finki.wp.lab.model.Course;
//import mk.ukim.finki.wp.lab.model.Student;
//import mk.ukim.finki.wp.lab.model.Teacher;
//import mk.ukim.finki.wp.lab.model.exceptions.CourseAlreadyExists;
//import mk.ukim.finki.wp.lab.model.exceptions.CourseDoesntExistException;
//import org.springframework.stereotype.Repository;
//
//import java.util.List;
//
//@Repository
//public class CourseRepository {
//
//    public List<Course> findAllCourses(){
//        return DataHolder.courses;
//    }
//
//    public Course findById(Long courseId){
//        return DataHolder.courses.stream().filter(course -> course.getCourseId().equals(courseId))
//                .findFirst()
//                .orElse(null);
//    }
//
//    public List<Student> findAllStudentsByCourse(Long courseId){
//        Course course = findById(courseId);
//        return course.getStudents();
//    }
//
//    public Course addStudentToCourse(Student student, Course course){ //?
//        List<Student> students = course.getStudents();
//        students.add(student);
//        course.setStudents(students);
//        return course;
//    }
//
//
//    public Course editCourse(Long id, String name, String description, Teacher teacher){
//        if(DataHolder.courses.stream().anyMatch(c -> c.getName().equals(name))){
//            throw new CourseAlreadyExists();
//        }
//        Course course = DataHolder.courses.stream().filter(c -> c.getCourseId().equals(id)).findFirst().orElseThrow(CourseDoesntExistException::new);
//        course.setName(name);
//        course.setDescription(description);
//        course.setTeacher(teacher);
//
//        return course;
//    }
//
//    public Course addCourse(Course course) throws CourseAlreadyExists{
//        if(DataHolder.courses.stream().anyMatch(c -> c.getCourseId().equals(course.getCourseId()))){
//            throw new CourseAlreadyExists();
//        }
//        DataHolder.courses.add(course);
//        return course;
//    }
//
//    public void deleteCourseById(Long id){
//        try{
//            DataHolder.courses.remove(this.findById(id));
//
//        }catch(Exception e){
//            System.out.println(e.getMessage());
//        }
//    }
//}
