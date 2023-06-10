package br.edu.ifsp.application.controller;

import br.edu.ifsp.application.view.WindowLoader;
import br.edu.ifsp.model.entities.instructor.Instructor;
import br.edu.ifsp.model.entities.qualification.QualificationProcess;
import br.edu.ifsp.model.entities.student.Student;
import br.edu.ifsp.model.usecases.instructor.ListInstructorUseCase;
import br.edu.ifsp.model.usecases.student.ListStudentUseCase;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import br.edu.ifsp.model.entities.category.DrivingCategory;
import br.edu.ifsp.model.enums.TestStatus;
import br.edu.ifsp.model.enums.RegistrationStatus;
import br.edu.ifsp.model.usecases.qualification.InsertQualificationProcessUseCase;

import java.io.IOException;


public class InsertQualificationUI {
    @FXML
    private TextField tfQualificationValue;
    @FXML
    private TextField tfNumberOfLessons;
    @FXML
    private ComboBox<Student> cbStudent;
    @FXML
    private ComboBox<Instructor> cbInstructor;
    @FXML
    private ComboBox<TestStatus> cbEyeExam;
    @FXML
    private ComboBox<TestStatus> cbTheoricExam;
    @FXML
    private ComboBox<TestStatus> cbPsychoExam;
    @FXML
    private ComboBox<DrivingCategory> cbDrivingCategory;
    @FXML
    private Button btnSubmit;
    @FXML
    private Button btnCancel;

    private InsertQualificationProcessUseCase insertQualificationProcessUseCase;
    private ListInstructorUseCase listInstructorUseCase;
    private ListStudentUseCase listStudentUseCase;

    public void initialize() {
        //cbInstructor.getItems().addAll(listInstructorUseCase.findAll());
        //cbStudent.getItems().addAll(listStudentUseCase.findAll());
        cbEyeExam.getItems().addAll(TestStatus.values());
        cbTheoricExam.getItems().addAll(TestStatus.values());
        cbPsychoExam.getItems().addAll(TestStatus.values());
    }

    public InsertQualificationUI (){

    }

    public InsertQualificationUI(InsertQualificationProcessUseCase insertQualificationProcessUseCase, ListInstructorUseCase listInstructorUseCase, ListStudentUseCase listStudentUseCase) {
        this.insertQualificationProcessUseCase = insertQualificationProcessUseCase;
        this.listInstructorUseCase = listInstructorUseCase;
        this.listStudentUseCase = listStudentUseCase;
    }

    @FXML
    private void handleSubmitButtonAction() {
       //

        try {
            //insertQualificationProcessUseCase.insert(requestModel);
            showAlert(Alert.AlertType.INFORMATION, "Success", "Qualification process created successfully.");

        } catch (Exception e) {
            showAlert(Alert.AlertType.ERROR, "Error", e.getMessage());
        }
    }

    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public void returnClicked() throws IOException {
        WindowLoader.setRoot("QualificationProcessManagementUI");
    }

}
