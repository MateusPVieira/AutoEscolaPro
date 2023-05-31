package br.edu.ifsp.application.view.model.exceptions;

public class EntityNotFoundException extends RuntimeException{
    public EntityNotFoundException (String message) {
        super(message);
    }
}
