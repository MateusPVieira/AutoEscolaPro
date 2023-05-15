package br.edu.ifsp.model.entities;

public class InactiveItemException extends RuntimeException{
    public InactiveItemException(String message) {
        super(message);
    }
}
