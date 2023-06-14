package br.edu.ifsp.application.controller;

import br.edu.ifsp.application.view.WindowLoader;
import br.edu.ifsp.model.dao.QualificationProcessDAO;
import br.edu.ifsp.model.dao.StudentDAO;
import br.edu.ifsp.model.daosqlite.QualificationProcessDAOSQLite;
import br.edu.ifsp.model.daosqlite.StudentDAOSQLite;
import br.edu.ifsp.model.entities.qualification.QualificationProcess;
import br.edu.ifsp.model.entities.reference.ValuesReference;
import br.edu.ifsp.model.entities.schedule.Schedule;
import br.edu.ifsp.model.entities.student.Student;
import br.edu.ifsp.model.enums.RegistrationStatus;
import br.edu.ifsp.model.enums.RemunerationStatus;
import br.edu.ifsp.model.enums.ScheduleStatus;
import br.edu.ifsp.model.enums.ScheduleType;
import br.edu.ifsp.model.exceptions.EntityNotFoundException;
import br.edu.ifsp.model.exceptions.InactiveItemException;
import br.edu.ifsp.model.usecases.qualification.ListQualificationProcessUseCase;
import br.edu.ifsp.model.usecases.student.CreateStudentUseCase;
import br.edu.ifsp.model.usecases.student.ListStudentUseCase;
import br.edu.ifsp.model.usecases.student.UpdateStudentUseCase;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class StudentEditUIController {
    QualificationProcessDAO qualificationProcessDAO = new QualificationProcessDAOSQLite();
    ListQualificationProcessUseCase listQualificationProcessUseCase = new ListQualificationProcessUseCase(qualificationProcessDAO);
    private Student selectedStudent;

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

    @FXML
    TableView<Schedule> tbScheduleView;
    @FXML
    TableColumn<Schedule, LocalDateTime> cDate;
    @FXML
    TableColumn<Schedule, ScheduleStatus> cSchStatus;
    @FXML
    TableColumn<Schedule, RemunerationStatus> cType;
    @FXML
    TableColumn<Schedule, ValuesReference> cRemuStatus;
    @FXML
    TableColumn<Schedule, ScheduleType> cReference;

    private ObservableList<Schedule> tableData;

    StudentDAO studentDAO;
    CreateStudentUseCase createStudentUseCase;
    ListStudentUseCase listStudentUseCase;
    UpdateStudentUseCase updateStudentUseCase;

    public StudentEditUIController() {
        studentDAO = new StudentDAOSQLite();
        createStudentUseCase = new CreateStudentUseCase(studentDAO);
        listStudentUseCase = new ListStudentUseCase(studentDAO);
        updateStudentUseCase = new UpdateStudentUseCase(studentDAO);

    }

        @FXML
    public void initialize() {
        bindTableViewToItemsList();
        bindColumnsToValueSources();
        loadDataAndShow();
    }

    private void bindTableViewToItemsList() {
        tableData = FXCollections.observableArrayList();
        tbScheduleView.setItems(tableData);
    }

        private void bindColumnsToValueSources() {
            cDate.setCellValueFactory(new PropertyValueFactory<>("scheduledDateTime"));
            cSchStatus.setCellValueFactory(new PropertyValueFactory<>("scheduleStatus"));
            cRemuStatus.setCellValueFactory(new PropertyValueFactory<>("remunerationStatus"));
            cType.setCellValueFactory(new PropertyValueFactory<>("scheduleType"));
            cReference.setCellValueFactory(new PropertyValueFactory<>("valuesReference"));
    }

    private void loadDataAndShow() {
        QualificationProcess qualificationProcess = listQualificationProcessUseCase.findByStudentId(selectedStudent.getId());
        List<Schedule> schedules =new ArrayList<>();
        schedules.addAll(qualificationProcess.getDrivingTests());
        schedules.addAll(qualificationProcess.getDrivingLessons());
        tableData.clear();
        tableData.addAll(schedules);
    }

    public void saveOrUpdate(ActionEvent actionEvent) {
    }

    public void returnClicked() throws IOException {
        WindowLoader.setRoot("ScheduleManageUI");
    }

    public void cancelSchedule(ActionEvent actionEvent) {
    }

    public void addSchedule(ActionEvent actionEvent) {
        try {
           QualificationProcess qualificationProcess = listQualificationProcessUseCase.findByStudentId(selectedStudent.getId());
           if (qualificationProcess.getRegistrationStatus().equals(RegistrationStatus.INACTIVE)){
               throw new InactiveItemException("Habilitação Inativa!");
           }
            ScheduleInsertUIController controller = (ScheduleInsertUIController) WindowLoader.getController();
            controller.setQualificationProcessToUI(qualificationProcess);
            WindowLoader.setRoot("ScheduleInsertUI");

        } catch (Exception e) {
            showAlert(Alert.AlertType.ERROR, "Error", "O aluno não possui habilitações ativas em andamento");
        }
    }

    public void setStudent(Student selectedStudentParam){
        selectedStudent = selectedStudentParam;
    }

    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
