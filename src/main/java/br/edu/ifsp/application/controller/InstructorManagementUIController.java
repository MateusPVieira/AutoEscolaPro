package br.edu.ifsp.application.controller;

import br.edu.ifsp.application.view.WindowLoader;
import br.edu.ifsp.model.dao.InstructorDAO;
import br.edu.ifsp.model.daosqlite.InstructorDAOSQLite;
import br.edu.ifsp.model.entities.instructor.Instructor;
import br.edu.ifsp.model.entities.instructor.Instructor;
import br.edu.ifsp.model.enums.RegistrationStatus;
import br.edu.ifsp.model.exceptions.EntityNotFoundException;
import br.edu.ifsp.model.usecases.instructor.ListInstructorUseCase;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.util.List;

public class InstructorManagementUIController {
    InstructorDAO instructorDAO;
    ListInstructorUseCase listInstructorUseCase;
    @FXML
    TableView<Instructor> tbInstructorsView;

    @FXML
    TableColumn<Instructor, String> cName;
    @FXML
    TableColumn<Instructor, String> cCPF;
    @FXML
    TableColumn<Instructor, String> cCNH;
    @FXML
    TableColumn<Instructor, String> cPhone;

//    @FXML
//    TableColumn<Instructor, List<String>> cCategory;

    @FXML
    TableColumn<Instructor, String> cStatus;








    private ObservableList<Instructor> tableData;

    public InstructorManagementUIController() {
        instructorDAO = new InstructorDAOSQLite();
        listInstructorUseCase = new ListInstructorUseCase(instructorDAO);
    }
    @FXML
    public void initialize() {
        bindTableViewToItemsList();
        bindColumnsToValueSources();
        loadDataAndShow();
    }

    private void bindTableViewToItemsList() {
        tableData = FXCollections.observableArrayList();
        tbInstructorsView.setItems(tableData);
    }

    private void bindColumnsToValueSources() {
        cName.setCellValueFactory(new PropertyValueFactory<>("name"));
        cCPF.setCellValueFactory(new PropertyValueFactory<>("cpf"));
        cCNH.setCellValueFactory(new PropertyValueFactory<>("cnh"));
        cPhone.setCellValueFactory(new PropertyValueFactory<>("phone"));
 //       cCategoria.setCellValueFactory(new PropertyValueFactory<>("phone"));
        cStatus.setCellValueFactory(new PropertyValueFactory<>("registrationStatus"));
    }

    private void loadDataAndShow() {
        List<Instructor> instructorList = listInstructorUseCase.findAll().orElseThrow(() -> new EntityNotFoundException("Nenhum instrutor encontrado!"));
        tableData.clear();
        tableData.addAll(instructorList);
    }


    public void detailInstructor(ActionEvent actionEvent) {
    }

    public void editInstructor(ActionEvent actionEvent) {
    }

    public void createInstructor(ActionEvent actionEvent) throws IOException {
        WindowLoader.setRoot("InstructorUI");
    }

    public void returnClicked(ActionEvent actionEvent) throws IOException {
        WindowLoader.setRoot("MainUI");
    }
}
