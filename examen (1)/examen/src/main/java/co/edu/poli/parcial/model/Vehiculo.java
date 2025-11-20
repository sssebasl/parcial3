package co.edu.poli.parcial.model;

import java.io.Serializable;


public abstract class Vehiculo implements Serializable {

    private String placa;

 
    private String marca;


    private int fechaManofactura;


    private int capacidadMin;


    private Conductor conductor;

 
    public Vehiculo() {}


    public Vehiculo(String placa, String marca, int fechaManofactura, int capacidadMin, Conductor conductor) {
        this.placa = placa  != null ? placa : "";
        this.marca = marca;
        this.fechaManofactura = fechaManofactura;
        this.capacidadMin = capacidadMin;
        this.conductor = conductor;
    }

 
    public String getPlaca() { return placa; }


    public void setPlaca(String placa) { this.placa = placa; }


    public String getMarca() { return marca; }


    public void setMarca(String marca) { this.marca = marca; }


    public int getfechaManofactura() { return fechaManofactura; }


    public void setfechaManofactura(int fechaManofactura) { this.fechaManofactura = fechaManofactura; }


    public int getCapacidadMin() { return capacidadMin; }


    public void setCapacidadMin(int capacidadMin) { this.capacidadMin = capacidadMin; }

 
    public Conductor getConductor() { return conductor; }


    public void setConductor(Conductor conductor) { this.conductor = conductor; }


    public abstract void mostrarInformacion();

}
