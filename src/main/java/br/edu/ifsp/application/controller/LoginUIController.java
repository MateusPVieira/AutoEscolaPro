package br.edu.ifsp.application.controller;

import br.edu.ifsp.application.repository.DatabaseBuilder;
import br.edu.ifsp.application.view.WindowLoader;
import br.edu.ifsp.model.dao.UserDAO;
import br.edu.ifsp.model.daosqlite.UserDAOSQLite;
import br.edu.ifsp.model.entities.user.UserLoginDTO;
import br.edu.ifsp.model.usecases.user.UserLoginUseCase;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.sql.SQLException;

public class LoginUIController {
    private static final Logger logger = LogManager.getLogger(LoginUIController.class);
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
                logger.error(e.getStackTrace());
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
