package mk.ukim.finki.wp.lab.model.exceptions;

public class CourseAlreadyExists extends RuntimeException{
    public CourseAlreadyExists() {
        super("Course already exists");
    }
}
