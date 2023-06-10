package br.edu.ifsp.application.controller;

import br.edu.ifsp.application.view.WindowLoader;

import java.io.IOException;

public class QualificationProcessManagementUI {

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
