package br.edu.ifsp.application.view.model.exceptions;

public class InvalidCredentialsException extends RuntimeException {
    public InvalidCredentialsException() {
        super("Senha incorreta");
    }
}