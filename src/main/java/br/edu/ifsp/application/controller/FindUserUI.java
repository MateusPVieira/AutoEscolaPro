package br.edu.ifsp.application.controller;

import br.edu.ifsp.application.view.WindowLoader;
import javafx.event.ActionEvent;

import java.io.IOException;

public class FindUserUI {
    public void handleFindButtonAction(ActionEvent actionEvent) {
    }

    public void returnClicked(ActionEvent actionEvent) throws IOException {
        WindowLoader.setRoot("UserManagementUI");
    }
}
