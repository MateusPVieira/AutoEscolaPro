package br.edu.ifsp.application.controller;

import br.edu.ifsp.application.view.WindowLoader;
import br.edu.ifsp.model.entities.user.Session;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.io.IOException;

public class MainUI {

    private String user;
    @FXML
    private Label lbUser;

    public void initialize() throws IOException {
        if (!Session.getInstance().checkSession())
           this.logoutClicked();

        this.user = Session.getInstance().getUser().getName();
        lbUser.setText(lbUser.getText() + user);
    }


    public void qualificationClicked() throws IOException {
        WindowLoader.setRoot("QualificationProcessManagementUI");
    }

    public void logoutClicked() throws IOException {
        Session.getInstance().setUser(null);
        WindowLoader.setRoot("LoginUI");
    }

}
