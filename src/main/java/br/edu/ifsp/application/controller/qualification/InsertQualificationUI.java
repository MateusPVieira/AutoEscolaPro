package br.edu.ifsp.application.controller.qualification;

import br.edu.ifsp.application.view.WindowLoader;
import br.edu.ifsp.model.dao.InstructorDAO;
import br.edu.ifsp.model.dao.QualificationProcessDAO;
import br.edu.ifsp.model.dao.StudentDAO;
import br.edu.ifsp.model.daosqlite.InstructorDAOSQLite;
import br.edu.ifsp.model.daosqlite.QualificationProcessDAOSQLite;
import br.edu.ifsp.model.daosqlite.StudentDAOSQLite;
import br.edu.ifsp.model.entities.instructor.Instructor;
import br.edu.ifsp.model.entities.student.Student;
import br.edu.ifsp.model.usecases.instructor.ListInstructorUseCase;
import br.edu.ifsp.model.usecases.qualification.InsertQualificationRequestModel;
import br.edu.ifsp.model.usecases.student.ListStudentUseCase;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import br.edu.ifsp.model.entities.category.DrivingCategory;
import br.edu.ifsp.model.enums.TestStatus;
import br.edu.ifsp.model.usecases.qualification.InsertQualificationProcessUseCase;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;



public class InsertQualificationUI {
    private static final Logger logger = LogManager.getLogger(InsertQualificationUI.class);
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

    private InsertQualificationRequestModel qualificationProcess;


    private InsertQualificationProcessUseCase insertQualificationProcessUseCase;
    private ListInstructorUseCase listInstructorUseCase;
    private ListStudentUseCase listStudentUseCase;
    private StudentDAO studentDAO;
    private InstructorDAO instructorDAO;
    private QualificationProcessDAO qualificationProcessDAO;

    public InsertQualificationUI() {
        this.studentDAO = new StudentDAOSQLite();
        this.instructorDAO = new InstructorDAOSQLite();
        this.qualificationProcessDAO = new QualificationProcessDAOSQLite();
        this.insertQualificationProcessUseCase = new InsertQualificationProcessUseCase(studentDAO, instructorDAO, qualificationProcessDAO);
        this.listInstructorUseCase = new ListInstructorUseCase(instructorDAO);
        this.listStudentUseCase = new ListStudentUseCase(studentDAO);

    }

    public void initialize() {
        try {
//        List<Instructor> instructors = listInstructorUseCase.findAll().orElseThrow(() -> new EntityNotFoundException("Não foi possível recuperar os instrutores!"));
//        List<Student> students = listStudentUseCase.findAll().orElseThrow(() -> new EntityNotFoundException("Não foi possível recuperar os alunos!"));
//        cbInstructor.getItems().addAll(instructors);
//        cbStudent.getItems().addAll(students);
            cbEyeExam.getItems().addAll(TestStatus.values());
            cbTheoricExam.getItems().addAll(TestStatus.values());
            cbPsychoExam.getItems().addAll(TestStatus.values());
        } catch (Exception e){
            logger.error(e.getMessage());
        }
    }


    @FXML
    private void handleSubmitButtonAction() {


        try {
            insertQualificationProcessUseCase.insert(qualificationProcess);
            showAlert(Alert.AlertType.INFORMATION, "Success", "Qualification process created successfully.");
            logger.info("Qualification process created successfully!");

        } catch (Exception e) {
            logger.error(e);
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


    private void getEntityToView(){
        if (qualificationProcess == null) {
            qualificationProcess = new InsertQualificationRequestModel(
                    cbStudent.getValue().getId(),
                    cbInstructor.getValue().getId(),
                    cbEyeExam.getValue(),
                    cbPsychoExam.getValue(),
                    cbTheoricExam.getValue(),
                    Integer.parseInt(tfNumberOfLessons.getText()),
                    Long.parseLong(tfQualificationValue.getText()),
                    cbDrivingCategory.getValue()

            );
        }
    }

}
