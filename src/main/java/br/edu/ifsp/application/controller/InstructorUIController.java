package br.edu.ifsp.application.controller;

import br.edu.ifsp.application.view.WindowLoader;
import br.edu.ifsp.model.dao.InstructorDAO;
import br.edu.ifsp.model.dao.StudentDAO;
import br.edu.ifsp.model.daosqlite.InstructorDAOSQLite;
import br.edu.ifsp.model.daosqlite.StudentDAOSQLite;
import br.edu.ifsp.model.entities.instructor.Instructor;
import br.edu.ifsp.model.entities.student.Student;
import br.edu.ifsp.model.usecases.instructor.CreateInstructorUseCase;
import br.edu.ifsp.model.usecases.instructor.ListInstructorUseCase;
import br.edu.ifsp.model.usecases.instructor.UpdateInstructorUseCase;
import br.edu.ifsp.model.usecases.student.CreateStudentUseCase;
import br.edu.ifsp.model.usecases.student.ListStudentUseCase;
import br.edu.ifsp.model.usecases.student.UpdateStudentUseCase;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.Optional;

public class InstructorUIController {
    private static final Logger logger = LogManager.getLogger(StudentUIController.class);
    @FXML
    TextField txtName;
    @FXML
    TextField txtCPF;
    @FXML
    TextField txtRG;
    @FXML
    TextField txtCNH;
    @FXML
    TextField txtAdress;
    @FXML
    TextField txtPhone;
    @FXML
    TextField txtBankAccount;
    @FXML
    TextField txtRegistrationStatus;


    InstructorDAO instructorDAO;
    CreateInstructorUseCase createInstructorUseCase;
    ListInstructorUseCase listInstructorUseCase;
    UpdateInstructorUseCase updateInstructorUseCase;

    public InstructorUIController() {
        instructorDAO = new InstructorDAOSQLite();
        createInstructorUseCase = new CreateInstructorUseCase(instructorDAO);
        listInstructorUseCase = new ListInstructorUseCase(instructorDAO);
        updateInstructorUseCase = new UpdateInstructorUseCase(instructorDAO);

    }

    public void initialize(){

    }

    public void returnClicked() throws IOException {
        WindowLoader.setRoot("InstructorManagementUI");
    }

    public void saveOrUpdate(ActionEvent actionEvent) {
        try {
            Optional<Instructor> instructor = listInstructorUseCase.findOneByCpf(txtCPF.getText());
            long id = instructor.get().getId();
            var insertedInstructor = getInstructor();
            if (id == 0) {
                id = createInstructorUseCase.insert(insertedInstructor;
                showAlert(Alert.AlertType.INFORMATION, "Success", "Instructor id: " + id +" created successfully.");
                logger.info("Instructor id: " + id +" created successfully.");
            } else {
                insertedInstructor.setId(id);
                updateInstructorUseCase.update(insertedInstructor);
                showAlert(Alert.AlertType.INFORMATION, "Success", "Instructor id: " + id +" updated successfully.");
                logger.info("Instructor id: " + id +" updated successfully.");

            }
        } catch (Exception e){
            logger.error(e);
            showAlert(Alert.AlertType.ERROR, "Error", e.getMessage());
        }

    }

    public Instructor getInstructor() {
        return new Instructor(
                txtName.getText(),
                txtCPF.getText(),
                txtRG.getText(),
                txtCNH.getText(),
                txtAdress.getText(),
                txtPhone.getText(),
                txtBankAccount.getText(),
                registrationStatus.RegistrationStatus()
        );
    }

    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
