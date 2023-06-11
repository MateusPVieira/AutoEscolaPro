package br.edu.ifsp.application.controller;

import br.edu.ifsp.application.view.WindowLoader;
import br.edu.ifsp.model.dao.UserDAO;
import br.edu.ifsp.model.daosqlite.UserDAOSQLite;
import br.edu.ifsp.model.entities.user.UserLoginDTO;
import br.edu.ifsp.model.usecases.user.UserLoginUseCase;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.IOException;
import java.sql.SQLException;

public class LoginUIController {
    @FXML
    private TextField txtUsername;
    @FXML
    private PasswordField txtPassword;
    @FXML
    private Button btnLogin;
    @FXML
    private Label lbWarn;


    private UserDAO userDAO;
    private UserLoginUseCase userLoginUseCase;

    public void initialize() {

    }

    public LoginUIController() {
        this.userDAO = new UserDAOSQLite();
        this.userLoginUseCase = new UserLoginUseCase(userDAO);
    }


    @FXML
    private void loginClicked() throws IOException {
        var isValidLogin = false;
        lbWarn.setText("");
        String username = txtUsername.getText();
        String password = txtPassword.getText();


        if (username.isEmpty() || password.isEmpty()) {
            lbWarn.setText("Campo Usuário ou Senha vazio(s)!");
        } else {

            try {

                isValidLogin = userLoginUseCase.loginUser(username, password);

                if (!isValidLogin) {
                    lbWarn.setText("Campo Usuário ou Senha incorreto(s)!");
                } else {
                    WindowLoader.setRoot("MainUI");
                }
            } catch (Exception e) {
                lbWarn.setText(e.getMessage());
            }
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
