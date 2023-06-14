package br.edu.ifsp.application.controller;

import br.edu.ifsp.application.controller.qualification.InsertQualificationUI;
import br.edu.ifsp.application.view.WindowLoader;
import br.edu.ifsp.model.dao.UserDAO;
import br.edu.ifsp.model.daosqlite.InstructorDAOSQLite;
import br.edu.ifsp.model.daosqlite.QualificationProcessDAOSQLite;
import br.edu.ifsp.model.daosqlite.StudentDAOSQLite;
import br.edu.ifsp.model.daosqlite.UserDAOSQLite;
import br.edu.ifsp.model.entities.instructor.Instructor;
import br.edu.ifsp.model.entities.student.Student;
import br.edu.ifsp.model.entities.user.User;
import br.edu.ifsp.model.enums.AcessLevel;
import br.edu.ifsp.model.enums.RegistrationStatus;
import br.edu.ifsp.model.enums.TestStatus;
import br.edu.ifsp.model.usecases.instructor.ListInstructorUseCase;
import br.edu.ifsp.model.usecases.qualification.InsertQualificationProcessUseCase;
import br.edu.ifsp.model.usecases.qualification.InsertQualificationRequestModel;
import br.edu.ifsp.model.usecases.student.CreateStudentUseCase;
import br.edu.ifsp.model.usecases.student.ListStudentUseCase;
import br.edu.ifsp.model.usecases.student.UpdateStudentUseCase;
import br.edu.ifsp.model.usecases.user.CreateUserUseCase;
import br.edu.ifsp.model.usecases.user.ListUserUseCase;
import br.edu.ifsp.model.usecases.user.UpdateUserUseCase;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;



public class InsertUserUIController {

    private static final Logger logger = LogManager.getLogger(InsertUserUIController.class);

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

    UserDAO userDAO;

    CreateUserUseCase createUserUseCase;

    ListUserUseCase listUserUseCase;

    UserDAOSQLite userDAOSQLite;

    List passwordTips = new ArrayList<>();

    public InsertUserUIController() {
        this.userDAO = new UserDAOSQLite();
        this.createUserUseCase = new CreateUserUseCase(userDAO);
        this.listUserUseCase = new ListUserUseCase(userDAO);
    }

    @FXML
    public void initialize(){
        try {
            cbAcessLevel.getItems().addAll(AcessLevel.values());
        } catch (Exception e){
            logger.error(e.getMessage());
        }
    }

    public void cancelButtonAction(ActionEvent actionEvent) throws IOException {
        WindowLoader.setRoot("UserManagementUI");
    }

    public void submitButtonAction(ActionEvent actionEvent) throws IOException {
        try {
            long id;
            Optional<User> user = listUserUseCase.findOneByEmail(txtEmail.getText());
            if(user.isPresent()){
                id = user.get().getId();
            } else {
                id = 0;
            }
            var insertedUser = getUser();
            if (id == 0) {
                id = createUserUseCase.insert(insertedUser);
                showAlert(Alert.AlertType.INFORMATION, "Success", "User id: " + id +" created successfully.");
                logger.info("User id: " + id +" created successfully.");
            }
        } catch (Exception e){
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Error", e.getMessage());
        }
    }

    public User getUser(){
        var userInput = new User();
        passwordTips.add(txtPasswordTips.getText());
        passwordTips.add(txtPasswordTipsSecond.getText());
        userInput.setName(txtName.getText());
        userInput.setUsername(txtUsername.getText());
        userInput.setPassword(txtPassword.getText());
        userInput.setEmail(txtEmail.getText());
        userInput.setPhone(txtPhone.getText());
        userInput.setPasswordTips(passwordTips);
        userInput.setAcessLevel(cbAcessLevel.getValue());
        userInput.setRegistrationStatus(RegistrationStatus.ACTIVE);
        return(userInput);
    }


    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }



}
