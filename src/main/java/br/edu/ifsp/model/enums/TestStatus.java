package br.edu.ifsp.model.enums;

public enum TestStatus {
    DONE ("Concluído"),
    IN_PROGRESS("Em andamento"),
    TO_DO ("A fazer");

    private String label;

    TestStatus(String label){this.label = label;}

    @Override
    public String toString(){
        return label;
    }
}
