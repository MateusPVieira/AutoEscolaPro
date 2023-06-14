package br.edu.ifsp.model.enums;

public enum TestStatus {
    DONE ("DONE"),
    IN_PROGRESS("IN_PROGRESS"),
    TO_DO ("TO_DO");

    private String label;

    TestStatus(String label){this.label = label;}

    @Override
    public String toString(){
        return label;
    }
}
