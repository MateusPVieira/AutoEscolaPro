package br.edu.ifsp.model.enums;

import javafx.scene.control.Label;

public enum AcessLevel {
    //ADMIN, USER
    ADMIN("ADMIN"),
    USER("USER");

    private String label;

    AcessLevel(String label){
        this.label = label;
    }

    @Override
    public String toString(){
        return label;
    }
}
