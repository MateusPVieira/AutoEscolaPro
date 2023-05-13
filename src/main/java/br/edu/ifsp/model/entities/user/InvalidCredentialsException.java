package br.edu.ifsp.model.entities.user;

public class InvalidCredentialsException extends RuntimeException {
    public InvalidCredentialsException() {
        super("Senha incorreta");
    }
}