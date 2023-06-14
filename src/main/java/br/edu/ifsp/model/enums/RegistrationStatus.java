package br.edu.ifsp.model.enums;

public enum RegistrationStatus {
    ACTIVE("ACTIVE"),
    INACTIVE("INACTIVE");

    private String label;

    RegistrationStatus(String label){
        this.label = label;
    }

    @Override
    public String toString(){
        return label;
    }
}
