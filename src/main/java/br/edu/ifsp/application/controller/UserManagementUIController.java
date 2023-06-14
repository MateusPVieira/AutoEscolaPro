package br.edu.ifsp.application.controller;

import br.edu.ifsp.application.view.WindowLoader;
import br.edu.ifsp.model.dao.StudentDAO;
import br.edu.ifsp.model.dao.UserDAO;
import br.edu.ifsp.model.daosqlite.InstructorDAOSQLite;
import br.edu.ifsp.model.daosqlite.StudentDAOSQLite;
import br.edu.ifsp.model.daosqlite.UserDAOSQLite;
import br.edu.ifsp.model.entities.instructor.Instructor;
import br.edu.ifsp.model.entities.student.Student;
import br.edu.ifsp.model.entities.user.User;
import br.edu.ifsp.model.exceptions.EntityNotFoundException;
import br.edu.ifsp.model.usecases.instructor.ListInstructorUseCase;
import br.edu.ifsp.model.usecases.student.ListStudentUseCase;
import br.edu.ifsp.model.usecases.user.ActivateUserUseCase;
import br.edu.ifsp.model.usecases.user.InactivateUserUseCase;
import br.edu.ifsp.model.usecases.user.ListUserUseCase;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import java.io.IOException;
import java.util.List;

public class UserManagementUIController {

    @FXML
    private TableView<User> tbUsersView;

    @FXML
    private TableColumn<User, String> cName;

    @FXML
    private TableColumn<User, String> cUsername;

    @FXML
    private TableColumn<User, String> cEmail;

    @FXML
    private TableColumn<User, String> cPhone;

    @FXML
    private TableColumn<User, String> cAcessLevel;

    @FXML
    private TableColumn<User, String> cStatus;

    private ObservableList<User> tableData;

    UserDAO userDAO;
    ListUserUseCase listUserUseCase;

    ActivateUserUseCase activateUserUseCase;

    InactivateUserUseCase inactivateUserUseCase;


    public UserManagementUIController() {
        userDAO = new UserDAOSQLite();
        listUserUseCase = new ListUserUseCase(userDAO);
    }


    @FXML
    private void initialize(){
        bindTableViewToItemsList();
        bindColumnsToValueSources();
        loadDataAndShow();
    }

    private void bindTableViewToItemsList() {
        tableData = FXCollections.observableArrayList();
        tbUsersView.setItems(tableData);

    }

    private void bindColumnsToValueSources() {
        cName.setCellValueFactory(new PropertyValueFactory<>("name"));
        cUsername.setCellValueFactory(new PropertyValueFactory<>("username"));
        cEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        cPhone.setCellValueFactory(new PropertyValueFactory<>("phone"));
        cAcessLevel.setCellValueFactory(new PropertyValueFactory<>("acessLevel"));
        cStatus.setCellValueFactory(new PropertyValueFactory<>("registrationStatus"));
    }


    private void loadDataAndShow() {
        List<User> userList = listUserUseCase.findAll().orElseThrow(() -> new EntityNotFoundException("Nenhum usuário encontrado!"));
        tableData.clear();
        tableData.addAll(userList);
    }


    public void addClicked(ActionEvent actionEvent) throws IOException {
        WindowLoader.setRoot("InsertUserUI");
    }

    public void deactivateClicked(ActionEvent actionEvent) {
        User selectedItem = tbUsersView.getSelectionModel().getSelectedItem();
        if(selectedItem != null){
            inactivateUserUseCase.inactivateUserUseCase(selectedItem.getId());
            loadDataAndShow();
        }
    }

    public void activeClicked(ActionEvent actionEvent) {
        User selectedItem = tbUsersView.getSelectionModel().getSelectedItem();
        if(selectedItem != null){
            activateUserUseCase.activateUser(selectedItem.getId());
            loadDataAndShow();
        }
    }

    public void editClicked(ActionEvent actionEvent) throws IOException {
        WindowLoader.setRoot("UpdateUserUI");
    }

    public void returnClicked(ActionEvent actionEvent) throws IOException {
        WindowLoader.setRoot("MainUI");
    }
}
