package br.edu.ifsp.application.controller;

import br.edu.ifsp.application.view.WindowLoader;
import br.edu.ifsp.model.dao.StudentDAO;
import br.edu.ifsp.model.daosqlite.StudentDAOSQLite;
import br.edu.ifsp.model.entities.student.Student;
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

public class StudentUIController {
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
    TextField txtEmail;

    StudentDAO studentDAO;
    CreateStudentUseCase createStudentUseCase;
    ListStudentUseCase listStudentUseCase;
    UpdateStudentUseCase updateStudentUseCase;

    public StudentUIController() {
        studentDAO = new StudentDAOSQLite();
        createStudentUseCase = new CreateStudentUseCase(studentDAO);
        listStudentUseCase = new ListStudentUseCase(studentDAO);
        updateStudentUseCase = new UpdateStudentUseCase(studentDAO);

    }

    public void initialize(){

    }

    public void returnClicked() throws IOException {
        WindowLoader.setRoot("StudentManagementUI");
    }

    public void saveOrUpdate(ActionEvent actionEvent) {
        try {
            Optional<Student> student = listStudentUseCase.findOneByCpf(txtCPF.getText());
            long id = student.get().getId();
            var insertedStudent = getStudent();
            if (id == 0) {
                id = createStudentUseCase.insert(insertedStudent);
                showAlert(Alert.AlertType.INFORMATION, "Success", "Student id: " + id +" created successfully.");
                logger.info("Student id: " + id +" created successfully.");
            } else {
                insertedStudent.setId(id);
                updateStudentUseCase.update(insertedStudent);
                showAlert(Alert.AlertType.INFORMATION, "Success", "Student id: " + id +" updated successfully.");
                logger.info("Student id: " + id +" updated successfully.");

            }
        } catch (Exception e){
            logger.error(e);
            showAlert(Alert.AlertType.ERROR, "Error", e.getMessage());
        }

    }

    public Student getStudent() {
        return new Student(
                txtName.getText(),
                txtCPF.getText(),
                txtRG.getText(),
                txtCNH.getText(),
                txtAdress.getText(),
                txtPhone.getText(),
                txtEmail.getText()
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
