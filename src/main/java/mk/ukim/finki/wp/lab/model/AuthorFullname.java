package mk.ukim.finki.wp.lab.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class AuthorFullname implements Serializable {
    private String name;
    private String surname;

    public AuthorFullname(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }
    public AuthorFullname(){

    }

    @Override
    public String toString() {
        return name + " " + surname;
    }
}
