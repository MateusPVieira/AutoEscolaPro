package br.edu.ifsp.model.entities.category;

public enum DrivingCategory {
   A("A - Carro"),
   B("B - Moto"),
   C("C - Caminhao"),
   D("D - Onibus");

    private String label;

    DrivingCategory(String label){
        this.label = label;
    }

    @Override
    public String toString(){
        return label;
    }
}
