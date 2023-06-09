package br.edu.ifsp.model.entities.category;

public class DrivingCategory {
    private long id;
    private char abbreviation;
    private int vehicle;

    public DrivingCategory(char abbreviation, int vehicle) {
        this.abbreviation = abbreviation;
        this.vehicle = vehicle;
    }

    public DrivingCategory(long id, char abbreviation, int vehicle) {
        this.id = id;
        this.abbreviation = abbreviation;
        this.vehicle = vehicle;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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
