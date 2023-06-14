package br.edu.ifsp.application.controller;

import br.edu.ifsp.application.view.WindowLoader;
import br.edu.ifsp.model.entities.user.User;
import br.edu.ifsp.model.enums.AcessLevel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import java.io.IOException;

//import static br.edu.ifsp.application.main.Main.updateUserUseCase;

public class UserUpdateUIController {

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtUsername;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtPhone;

    @FXML
    private ComboBox<AcessLevel> cbAcessLevel;

    @FXML
    private Button btnConfirm;

    @FXML
    private Button btnReturn;

    private User user;


    @FXML
    private void initialize(){
        cbAcessLevel.getItems().setAll(AcessLevel.values());
    }

    private void getEntityToView(){
        if (user == null) {
            //user = new User(txtName.getText(), txtUsername.getText(), txtPassword.getText(), txtEmail.getText(), txtPhone.getText(), txtPasswordTips.getText(), txtPasswordTipsSecond.getText(), cbAcessLevel.getValue());
        }
        user.setName(txtName.getText());
        user.setUsername(txtUsername.getText());
        user.setEmail(txtEmail.getText());
        user.setPhone(txtPhone.getText());
        user.setAcessLevel(cbAcessLevel.getValue());
    }



    private void setEntityIntoView(){
        txtName.setText(user.getName());
        txtUsername.setText(user.getUsername());
        txtEmail.setText(user.getEmail());
        txtPhone.setText(user.getPhone());
        cbAcessLevel.setValue(user.getAcessLevel());
    }

    public void confirmClicked(ActionEvent actionEvent) throws IOException {
        getEntityToView();
        if(user.getName() == null){
            //updateUserUseCase.update(user);
        }
        WindowLoader.setRoot("UserManagementUI");
    }

    public void returnClicked(ActionEvent actionEvent) throws IOException {
        WindowLoader.setRoot("UserManagementUI");
    }
}
