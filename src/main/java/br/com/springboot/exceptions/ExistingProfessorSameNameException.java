package br.com.springboot.exceptions;

public class ExistingProfessorSameNameException extends Exception {
    public ExistingProfessorSameNameException(String message){
        super(message);
    }
}
