package mk.ukim.finki.wp.lab.exceptions;

public class InvalidAuthorException extends RuntimeException {
    public InvalidAuthorException() {
        super("You have provided invalid author argument!");
    }
}
