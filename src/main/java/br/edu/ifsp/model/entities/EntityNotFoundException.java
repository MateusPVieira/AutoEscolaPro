package br.edu.ifsp.model.entities;

public class EntityNotFoundException extends RuntimeException{
    public EntityNotFoundException (String message) {
        super(message);
    }
}
