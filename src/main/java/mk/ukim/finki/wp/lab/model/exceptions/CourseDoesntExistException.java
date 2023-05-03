package mk.ukim.finki.wp.lab.model.exceptions;

public class CourseDoesntExistException extends RuntimeException{
    public CourseDoesntExistException() {
        super("Course doesn't exist!");
    }
}
