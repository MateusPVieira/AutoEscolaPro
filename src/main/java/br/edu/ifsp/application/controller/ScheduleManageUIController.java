package br.edu.ifsp.application.controller;

import br.edu.ifsp.application.view.WindowLoader;
import br.edu.ifsp.model.dao.StudentDAO;
import br.edu.ifsp.model.daosqlite.StudentDAOSQLite;
import br.edu.ifsp.model.entities.schedule.Schedule;
import br.edu.ifsp.model.entities.student.Student;
import br.edu.ifsp.model.exceptions.EntityNotFoundException;
import br.edu.ifsp.model.usecases.student.ListStudentUseCase;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.util.List;

public class ScheduleManageUIController {
    StudentDAO studentDAO;
    ListStudentUseCase listStudentUseCase;
    @FXML
    TableView<Schedule> tbScheduleView;
    @FXML
    TableColumn<Schedule, String> cDate;
    @FXML
    TableColumn<Schedule, String> cSchStatus;
    @FXML
    TableColumn<Schedule, String> cType;
    @FXML
    TableColumn<Schedule, String> cRemuStatus;
    private ObservableList<Schedule> tableData;

    public ScheduleManageUIController() {
        studentDAO = new StudentDAOSQLite();
        listStudentUseCase = new ListStudentUseCase(studentDAO);
    }

//    @FXML
//    public void initialize() {
//        bindTableViewToItemsList();
//        bindColumnsToValueSources();
//        loadDataAndShow();
//    }
//
//    private void bindTableViewToItemsList() {
//        tableData = FXCollections.observableArrayList();
//        tbStudentsView.setItems(tableData);
//    }
//
//    private void bindColumnsToValueSources() {
//        cName.setCellValueFactory(new PropertyValueFactory<>("name"));
//        cCPF.setCellValueFactory(new PropertyValueFactory<>("cpf"));
//        cRG.setCellValueFactory(new PropertyValueFactory<>("rg"));
//        cCNH.setCellValueFactory(new PropertyValueFactory<>("cnh"));
//        cAdress.setCellValueFactory(new PropertyValueFactory<>("address"));
//        cPhone.setCellValueFactory(new PropertyValueFactory<>("phone"));
//        cEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
//    }
//
//    private void loadDataAndShow() {
//        List<Schedule> scheduleList = listStudentUseCase.findAll().orElseThrow(() -> new EntityNotFoundException("Nenhum estudante encontrado!"));
//        tableData.clear();
//        tableData.addAll(studentList);
//    }


    public void createSchedule(ActionEvent actionEvent) {
    }

    public void deleteStudent(ActionEvent actionEvent) {
    }

    public void editSchedule(ActionEvent actionEvent) {
    }

    public void detailSchedule(ActionEvent actionEvent) {
    }

    public void returnClicked(ActionEvent actionEvent) throws IOException {
        WindowLoader.setRoot("MainUI");
    }
}



