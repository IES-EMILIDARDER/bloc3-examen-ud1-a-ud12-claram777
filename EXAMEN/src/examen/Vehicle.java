package examen;

import java.util.Objects;
import java.util.function.BiFunction;
import java.util.function.Function;

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

    public String getMatricula() {
        return matricula;
    }

    public String getMarca() {
        return marca;
    }

    public String getModel() {
        return model;
    }

    public int getAny() {
        return any;
    }

    public double getPreu() {
        return preu;
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
        return "\nVehicle{" + "matricula=" + matricula + ", marca=" + marca + ", model=" + model + ", any=" + any + ", preu=" + preu + '}';
    }
    
    public Function<Vehicle, Vehicle> incrementaPreu1 (Vehicle v) {
        return null;
    }
    
}