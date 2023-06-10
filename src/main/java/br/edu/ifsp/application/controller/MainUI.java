package br.edu.ifsp.application.controller;

import br.edu.ifsp.application.view.WindowLoader;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.io.IOException;

public class MainUI {

    private String user;
    @FXML
    private Label lbUser;

    public void initialize(){
        this.user = "Admin";
        lbUser.setText(lbUser.getText() + user);
    }


    public void qualificationClicked() throws IOException {
        WindowLoader.setRoot("QualificationProcessManagementUI");
    }



}
