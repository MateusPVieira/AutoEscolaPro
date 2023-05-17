package br.edu.ifsp.model.exceptions;

public class InactiveItemException extends RuntimeException{
    public InactiveItemException(String message) {
        super(message);
    }
}
