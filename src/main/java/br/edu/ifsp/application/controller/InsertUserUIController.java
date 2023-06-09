package br.edu.ifsp.application.controller;

import br.edu.ifsp.application.view.WindowLoader;
import br.edu.ifsp.model.entities.user.User;
import br.edu.ifsp.model.enums.AcessLevel;
import br.edu.ifsp.model.enums.TestStatus;
import br.edu.ifsp.model.usecases.user.CreateUserUseCase;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.util.Collections;

import static br.edu.ifsp.application.main.Main.createUserUseCase;



public class InsertUserUIController {

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtUsername;

    @FXML
    private PasswordField txtPassword;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtPhone;

    @FXML
    private TextField txtPasswordTips;

    @FXML
    private TextField txtPasswordTipsSecond;

    @FXML
    private ComboBox<AcessLevel> cbAcessLevel;

    @FXML
    private Button btnCancel;

    @FXML
    private Button btnSubmit;

    private User user;



    @FXML
    private void initialize(){
        cbAcessLevel.getItems().setAll(AcessLevel.values());
    }

    private void getEntityToView(){
        if (user == null) {
            user = new User();
        }
        user.setName(txtName.getText());
        user.setUsername(txtUsername.getText());
        user.setPassword(txtPassword.getText());
        user.setEmail(txtEmail.getText());
        user.setPhone(txtPhone.getText());
        user.setPasswordTips(Collections.singletonList(txtPasswordTips.getText()));
        user.setPasswordTips(Collections.singletonList(txtPasswordTipsSecond.getText()));
        user.setAcessLevel(cbAcessLevel.getValue());
    }



    private void setEntityIntoView(){
        txtName.setText(user.getName());
        txtUsername.setText(user.getUsername());
        txtPassword.setText(user.getPassword());
        txtEmail.setText(user.getEmail());
        txtPhone.setText(user.getPhone());
        txtPasswordTips.setText(user.getPasswordTips().toString());
        txtPasswordTipsSecond.setText(user.getPasswordTips().toString());
        cbAcessLevel.setValue(user.getAcessLevel());
    }


    public void cancelButtonAction(ActionEvent actionEvent) throws IOException {
        WindowLoader.setRoot("UserManagementUI");
    }

    public void submitButtonAction(ActionEvent actionEvent) throws IOException {
        getEntityToView();
        if(user.getName() == null){
            createUserUseCase.insert(user);
        }
        WindowLoader.setRoot("UserManagementUI");
    }
}
