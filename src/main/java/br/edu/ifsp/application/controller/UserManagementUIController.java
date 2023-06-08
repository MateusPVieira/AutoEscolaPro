package br.edu.ifsp.application.controller;

import br.edu.ifsp.application.view.WindowLoader;
import br.edu.ifsp.model.entities.user.User;
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
    private TableView<User> tableView;

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

    @FXML
    private void initialize(){
        //bindTableViewToItemsList();
        //bindColumnsToValueSources();
        //loadDataAndShow();
        
    }

    private void loadDataAndShow() {
        //List<User> users =  ListUserUseCase.findAll;
        //tableData.clear();
        //tableData.addAll(users); necessario uma correção no findAll
    }

    private void bindColumnsToValueSources() {
        cName.setCellValueFactory(new PropertyValueFactory<>("name"));
        cUsername.setCellValueFactory(new PropertyValueFactory<>("username"));
        cEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        cPhone.setCellValueFactory(new PropertyValueFactory<>("phone"));
        cAcessLevel.setCellValueFactory(new PropertyValueFactory<>("acessLevel"));
        cStatus.setCellValueFactory(new PropertyValueFactory<>("registrationStatus"));
    }

    private void bindTableViewToItemsList() {
        //tableData = FXCollections.observableArrayList();
        //tableView.setItems(tableData);
    }


    public void addClicked(ActionEvent actionEvent) throws IOException {
        WindowLoader.setRoot("InsertUserUI");
    }

    public void deactivateClicked(ActionEvent actionEvent) {
        /*User selectedItem = tableView.getSelectionModel().getSelectedItem();
        if(selectedItem != null){
            InactivateUserUseCase.inactivateUserUseCase(selectedItem);
            loadDataAndShow();
        }*/
    }

    public void activeClicked(ActionEvent actionEvent) {
        /*User selectedItem = tableView.getSelectionModel().getSelectedItem();
        if(selectedItem != null){
            ActivateUserUseCase.activateUserUseCase(selectedItem);
            loadDataAndShow();
        }*/
    }

    public void editClicked(ActionEvent actionEvent) throws IOException {
        WindowLoader.setRoot("UpdateUserUI");
    }

    public void returnClicked(ActionEvent actionEvent) throws IOException {
        WindowLoader.setRoot("LoginUI");
    }
}
