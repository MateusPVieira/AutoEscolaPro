package br.edu.ifsp.application.controller.qualification;

import br.edu.ifsp.application.view.WindowLoader;
import br.edu.ifsp.model.dao.QualificationProcessDAO;
import br.edu.ifsp.model.daosqlite.QualificationProcessDAOSQLite;
import br.edu.ifsp.model.entities.instructor.Instructor;
import br.edu.ifsp.model.entities.qualification.QualificationProcess;
import br.edu.ifsp.model.entities.student.Student;
import br.edu.ifsp.model.enums.RegistrationStatus;
import br.edu.ifsp.model.exceptions.EntityNotFoundException;
import br.edu.ifsp.model.usecases.qualification.ListQualificationProcessUseCase;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

public class QualificationProcessManagementUI {
    QualificationProcessDAO qualificationProcessDAO;
    ListQualificationProcessUseCase listQualificationProcessUseCase;
    @FXML
    TableView<QualificationProcess> tbQualificationView;

    @FXML
    TableColumn<QualificationProcess, String> cCategory;
    @FXML
    TableColumn<QualificationProcess, Student> cStudent;
    @FXML
    TableColumn<QualificationProcess, Instructor> cInstructor;
    @FXML
    TableColumn<QualificationProcess, RegistrationStatus> cStatus;

    @FXML
    TableColumn<QualificationProcess, LocalDate> cOpDate;

    private ObservableList<QualificationProcess> tableData;

    public QualificationProcessManagementUI() {
        qualificationProcessDAO = new QualificationProcessDAOSQLite();
        listQualificationProcessUseCase = new ListQualificationProcessUseCase(qualificationProcessDAO);
    }
    @FXML
    public void initialize() {
        bindTableViewToItemsList();
        bindColumnsToValueSources();
        loadDataAndShow();
    }

    private void bindTableViewToItemsList() {
        tableData = FXCollections.observableArrayList();
        tbQualificationView.setItems(tableData);
    }

    private void bindColumnsToValueSources() {
        cCategory.setCellValueFactory(new PropertyValueFactory<>("drivingCategory"));
        cStudent.setCellValueFactory(new PropertyValueFactory<>("student"));
        cInstructor.setCellValueFactory(new PropertyValueFactory<>("instructor"));
        cStatus.setCellValueFactory(new PropertyValueFactory<>("registrationStatus"));
        cOpDate.setCellValueFactory(new PropertyValueFactory<>("openingDate"));
    }

    private void loadDataAndShow() {
        List<QualificationProcess> qualificationProcesses = listQualificationProcessUseCase.findAll().orElseThrow(() -> new EntityNotFoundException("Nenhuma Habilitação encontrada!"));
        tableData.clear();
        tableData.addAll(qualificationProcesses);
    }


    public void addClicked() throws IOException {
        WindowLoader.setRoot("InsertQualificationUI");
    }

    public void seeClicked(){

    }

    public void toggleClicked(){

    }

    public void returnClicked() throws IOException {
        WindowLoader.setRoot("MainUI");
    }
}
