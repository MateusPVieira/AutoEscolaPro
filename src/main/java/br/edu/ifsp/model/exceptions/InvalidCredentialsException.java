package br.edu.ifsp.model.exceptions;

public class InvalidCredentialsException extends RuntimeException {
    public InvalidCredentialsException() {
        super("Senha incorreta");
    }
}