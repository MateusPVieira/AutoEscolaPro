package br.edu.ifsp.application.controller;

import br.edu.ifsp.application.view.WindowLoader;
import br.edu.ifsp.model.dao.StudentDAO;
import br.edu.ifsp.model.daosqlite.StudentDAOSQLite;
import br.edu.ifsp.model.entities.student.Student;
import br.edu.ifsp.model.exceptions.EntityNotFoundException;
import br.edu.ifsp.model.usecases.student.CreateStudentUseCase;
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

public class StudentManagementUIContoller {
    StudentDAO studentDAO;
    ListStudentUseCase listStudentUseCase;

    @FXML
    TableColumn<Student, String> cName;
    @FXML
    TableColumn<Student, String> cCPF;
    @FXML
    TableColumn<Student, String> cRG;
    @FXML
    TableColumn<Student, String> cCNH;
    @FXML
    TableColumn<Student, String> cAdress;
    @FXML
    TableColumn<Student, String> cPhone;
    @FXML
    TableColumn<Student, String> cEmail;

    @FXML
    TableView<Student> tbStudentsView;


    private ObservableList<Student> tableData;

    public StudentManagementUIContoller() {
        studentDAO = new StudentDAOSQLite();
        listStudentUseCase = new ListStudentUseCase(studentDAO);
    }

    @FXML
    public void initialize() {
        bindTableViewToItemsList();
        bindColumnsToValueSources();
        loadDataAndShow();
    }

    private void bindTableViewToItemsList() {
        tableData = FXCollections.observableArrayList();
        tbStudentsView.setItems(tableData);
    }

    private void bindColumnsToValueSources() {
        cName.setCellValueFactory(new PropertyValueFactory<>("name"));
        cCPF.setCellValueFactory(new PropertyValueFactory<>("cpf"));
        cRG.setCellValueFactory(new PropertyValueFactory<>("rg"));
        cCNH.setCellValueFactory(new PropertyValueFactory<>("cnh"));
        cAdress.setCellValueFactory(new PropertyValueFactory<>("address"));
        cPhone.setCellValueFactory(new PropertyValueFactory<>("phone"));
        cEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
    }

    private void loadDataAndShow() {
        List<Student> studentList = listStudentUseCase.findAll().orElseThrow(() -> new EntityNotFoundException("Nenhum estudante encontrado!"));
        tableData.clear();
        tableData.addAll(studentList);
    }

    public void createStudent(ActionEvent actionEvent) throws IOException {
        WindowLoader.setRoot("StudentUI");
    }

    public void deleteStudent(ActionEvent actionEvent) {
       Student student = tbStudentsView.getSelectionModel().getSelectedItem();
       if (student != null){
           //
       }
    }

    public void editStudent(ActionEvent actionEvent) throws IOException {
        Student student = tbStudentsView.getSelectionModel().getSelectedItem();
        if (student != null){
            WindowLoader.setRoot("StudentEditUI");
            StudentEditUIController controller = (StudentEditUIController) WindowLoader.getController();
            controller.setStudent(student);
        }
    }

    public void detailStudent(ActionEvent actionEvent) {
        Student student = tbStudentsView.getSelectionModel().getSelectedItem();
        if (student != null){
            //
        }
    }

    public void returnClicked(ActionEvent actionEvent) throws IOException {
        WindowLoader.setRoot("MainUI");
    }
}
