package mk.ukim.finki.wp.lab.exceptions;

public class InvalidReviewException  extends  RuntimeException{

    public InvalidReviewException (){
        super("You have provided invalid review argument!");
    }
}
