package br.edu.ifsp.application.controller;
import br.edu.ifsp.application.view.WindowLoader;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.IOException;

public class LoginUIController {
    @FXML
    private TextField txtUsername;
    @FXML
    private PasswordField txtPassword;
    @FXML
    private Button btnLogin;
    @FXML
    private Label lbWarn;
    @FXML
    private void loginClicked() throws IOException {
            lbWarn.setText("");
            String username = txtUsername.getText();
            String password = txtPassword.getText();
            System.out.println("Username:" + username);

            if(username.isEmpty() || password.isEmpty()){
                lbWarn.setText("Campo Usuário ou Senha vazio(s)!");
        } else {
                WindowLoader.setRoot("InsertQualificationUI");

            }


    }

    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
