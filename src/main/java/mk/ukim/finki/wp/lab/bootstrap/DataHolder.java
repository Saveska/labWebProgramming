//package mk.ukim.finki.wp.lab.bootstrap;
//
//import mk.ukim.finki.wp.lab.model.Course;
//import mk.ukim.finki.wp.lab.model.Student;
//
//import mk.ukim.finki.wp.lab.model.Teacher;
//import org.springframework.stereotype.Component;
//
//import javax.annotation.PostConstruct;
//import javax.swing.text.StyledEditorKit;
//import java.util.ArrayList;
//import java.util.Collections;
//import java.util.List;
//@Component
//public class DataHolder {
//
//    public static List<Student> students = new ArrayList<>();
//    public static List<Course> courses = new ArrayList<>();
//
//    public static List<Teacher> teachers = new ArrayList<>();
//
//    @PostConstruct
//    public void init(){
//        students.add(new Student("201062","pass123","Marija","Saveska", Boolean.FALSE));
//        students.add(new Student("201063","pass456","Ema","Saveska", Boolean.FALSE));
//        students.add(new Student("203064","pass789","Marko","Stojanoski", Boolean.FALSE));
//        students.add(new Student("201065","pass111","Vladimir","Veleski", Boolean.FALSE));
//        students.add(new Student("203066","pass222","Anastasija","Trajkoska", Boolean.FALSE));
//
//
//        List<Student> secondCourse = new ArrayList<>(); //4
//        List<Student> thirdCourse = new ArrayList<>(); //3
//        List<Student> fourthCourse = new ArrayList<>(); //2
//
//        List<Student> firstCourse = new ArrayList<>(students); //5
//        students.stream().skip(1).forEach(secondCourse::add);
//        students.stream().skip(2).forEach(thirdCourse::add);
//        students.stream().skip(3).forEach(fourthCourse::add);
//
//        teachers.add(new Teacher("Saso","Gramatikov"));
//        teachers.add(new Teacher("Dimitar","Trajanov"));
//        teachers.add(new Teacher("Magdalena","Kostovska"));
//        teachers.add(new Teacher("Riste","Stojanov"));
//        teachers.add(new Teacher("Verica","Bakeva"));
//
//        //Long courseId, String name, String description, List<Student> students
//        courses.add(new Course(
//               "Introduction to Data Science",
//               "Learning how to analyze data using Python",
//               firstCourse,
//                teachers.get(0)
//        ));
//        courses.add(new Course(
//                "Web programming",
//                "Learning how to make web apps",
//                secondCourse,
//                teachers.get(1)
//        ));
//        courses.add(new Course(
//                "Databases",
//                "Learning how to create and manage databases",
//                thirdCourse,
//                teachers.get(2)
//        ));
//        courses.add(new Course(
//                "Software Design and Architecture",
//                "Learning design patterns and how to use them",
//                fourthCourse,
//                teachers.get(3)
//        ));
//        courses.add(new Course(
//                "Operating systems",
//                "Learning how operating systems work",
//                teachers.get(4)
//        ));
//
//
//    }
//}
