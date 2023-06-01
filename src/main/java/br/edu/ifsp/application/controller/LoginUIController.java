package br.edu.ifsp.application.controller;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
public class LoginUIController {
    @FXML
    private TextField usernameField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private Button loginButton;
    @FXML
    private void loginClicked() {
        String username = usernameField.getText();
        String password = passwordField.getText();

        // Lógica de autenticação do usuário
    }
}
