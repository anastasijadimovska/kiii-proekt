package mk.ukim.finki.wp.lab.exceptions;

public class InvalidBookStoreException extends RuntimeException{
    public InvalidBookStoreException(){
        super("The book store does not exist!");
    }
}
