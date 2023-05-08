package br.edu.ifsp.model.entities;

public class DrivingCategory {
    private char abbreviation;
    private int vehicle;

    public DrivingCategory(char abbreviation, int vehicle) {
        this.abbreviation = abbreviation;
        this.vehicle = vehicle;
    }

    public char getAbbreviation() {
        return abbreviation;
    }

    public void setAbbreviation(char abbreviation) {
        this.abbreviation = abbreviation;
    }

    public int getVehicle() {
        return vehicle;
    }

    public void setVehicle(int vehicle) {
        this.vehicle = vehicle;
    }

    @Override
    public String toString() {
        return "DrivingCategory{" +
                "abbreviation=" + abbreviation +
                ", vehicle=" + vehicle +
                '}';
    }
}
