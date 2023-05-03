package mk.ukim.finki.wp.lab.model.exceptions;

public class InsufficientInfoForNewStudentException extends RuntimeException{
    public InsufficientInfoForNewStudentException() {
        super("Insufficient info for new student!");
    }
}
