package br.edu.ifsp.application.view.model.exceptions;

public class InactiveItemException extends RuntimeException{
    public InactiveItemException(String message) {
        super(message);
    }
}
