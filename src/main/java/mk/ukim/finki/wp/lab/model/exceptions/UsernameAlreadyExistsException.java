package mk.ukim.finki.wp.lab.model.exceptions;

public class UsernameAlreadyExistsException extends RuntimeException{
    public UsernameAlreadyExistsException() {
        super("Username already exists!");
    }
}
