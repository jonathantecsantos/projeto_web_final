package br.com.springboot.exceptions;

public class ExistingAlunoSameNameException extends Exception {
    public ExistingAlunoSameNameException(String message){
        super(message);
    }
}
