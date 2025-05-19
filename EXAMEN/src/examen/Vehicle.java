package examen;

import java.util.Objects;

class Vehicle {
    private String matricula;
    private String marca;
    private String model;
    private int any;
    private double preu;

    public Vehicle(String matricula, String marca, String model, int any, double preu) {
        this.matricula = matricula;
        this.marca = marca;
        this.model = model;
        this.any = any;
        this.preu = preu;
    }

    public int getAny() {
        return any;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 23 * hash + Objects.hashCode(this.matricula);
        hash = 23 * hash + Objects.hashCode(this.marca);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Vehicle other = (Vehicle) obj;
        if (!Objects.equals(this.matricula, other.matricula)) {
            return false;
        }
        return Objects.equals(this.marca, other.marca);
    }

    @Override
    public String toString() {
        return "Vehicle{" + "matricula=" + matricula + ", marca=" + marca + ", model=" + model + ", any=" + any + ", preu=" + preu + '}';
    }
    
}