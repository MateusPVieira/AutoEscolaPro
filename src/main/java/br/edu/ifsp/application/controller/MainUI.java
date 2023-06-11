package br.edu.ifsp.application.controller;

import br.edu.ifsp.application.view.WindowLoader;
import br.edu.ifsp.model.entities.user.Session;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

public class MainUI {
    private static final Logger logger = LogManager.getLogger(MainUI.class);
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
        logger.info("Usuario deslogado com suceso!");
        WindowLoader.setRoot("LoginUI");
    }

}
