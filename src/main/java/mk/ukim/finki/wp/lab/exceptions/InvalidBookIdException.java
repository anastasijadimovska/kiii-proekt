package mk.ukim.finki.wp.lab.exceptions;

public class InvalidBookIdException extends RuntimeException{
    public InvalidBookIdException(){
        super("The book does not exist!");
    }
}
