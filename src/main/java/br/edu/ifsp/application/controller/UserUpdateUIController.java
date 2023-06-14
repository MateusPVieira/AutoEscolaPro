package br.edu.ifsp.application.controller;

import br.edu.ifsp.application.view.WindowLoader;
import br.edu.ifsp.model.dao.StudentDAO;
import br.edu.ifsp.model.dao.UserDAO;
import br.edu.ifsp.model.daosqlite.StudentDAOSQLite;
import br.edu.ifsp.model.daosqlite.UserDAOSQLite;
import br.edu.ifsp.model.entities.qualification.QualificationProcess;
import br.edu.ifsp.model.entities.student.Student;
import br.edu.ifsp.model.entities.user.User;
import br.edu.ifsp.model.enums.AcessLevel;
import br.edu.ifsp.model.enums.RegistrationStatus;
import br.edu.ifsp.model.usecases.student.CreateStudentUseCase;
import br.edu.ifsp.model.usecases.student.ListStudentUseCase;
import br.edu.ifsp.model.usecases.student.UpdateStudentUseCase;
import br.edu.ifsp.model.usecases.user.ListUserUseCase;
import br.edu.ifsp.model.usecases.user.UpdateUserUseCase;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    UpdateUserUseCase updateUserUseCase;

    ListUserUseCase listUserUseCase;

    ListUserUseCase listUserUseCase1;


    UserDAO userDAO;

    private User selectedUser;

    List passwordTips = new ArrayList<>();

    public UserUpdateUIController(){
        userDAO = new UserDAOSQLite();
        listUserUseCase = new ListUserUseCase(userDAO);
        updateUserUseCase = new UpdateUserUseCase(userDAO);
    }


    @FXML
    private void initialize(){
        cbAcessLevel.getItems().setAll(AcessLevel.values());
    }


    public void setUser(User selectedUserParam){
        selectedUser = selectedUserParam;
        setUserFields();
    }

    public void setUserFields(){
        txtName.setText(selectedUser.getName());
        txtUsername.setText(selectedUser.getName());
        txtPhone.setText(selectedUser.getPhone());
        txtEmail.setText(selectedUser.getEmail());
        cbAcessLevel.setValue(selectedUser.getAcessLevel());
    }

    public void confirmClicked(ActionEvent actionEvent) throws IOException {
        var insertedUser = getUser();
        updateUserUseCase.update(insertedUser);
        WindowLoader.setRoot("UserManagementUI");
    }

    public User getUser(){
        var userInput = new User();
        passwordTips.add(selectedUser.getPasswordTips());
        passwordTips.add(selectedUser.getPasswordTips());
        userInput.setId(selectedUser.getId());
        userInput.setName(txtName.getText());
        userInput.setUsername(txtUsername.getText());
        userInput.setPassword(selectedUser.getPassword());
        userInput.setEmail(txtEmail.getText());
        userInput.setPhone(txtPhone.getText());
        userInput.setPasswordTips(passwordTips);
        userInput.setAcessLevel(cbAcessLevel.getValue());
        userInput.setRegistrationStatus(RegistrationStatus.ACTIVE);
        return(userInput);
    }

    public void returnClicked(ActionEvent actionEvent) throws IOException {
        WindowLoader.setRoot("UserManagementUI");
    }
}
