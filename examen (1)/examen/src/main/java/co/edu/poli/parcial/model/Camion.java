package co.edu.poli.parcial.model;

import java.io.Serializable;


public class Camion extends Vehiculo implements Serializable {


    private String tipo;

 
    public Camion() {}

 
    public Camion(String placa, String marca, int fechaManofactura, int capacidad, Conductor conductor, String tipo) {
        super(placa, marca, fechaManofactura, capacidad, conductor);
        this.genero = genero;
    }

  
    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }


    @Override
    public void mostrarInformacion() {
        System.out.println("\n--- Camion ---");
        System.out.println("Placa: " + getPlaca());
        System.out.println("Marca : " + getMarca());
        System.out.println("Fecha de manofacturacion: " + getFechaManofactura());
        System.out.println("Capacidad: " + getCapacidadMin() + " kilogramos ");
        System.out.println("Conductor: " + getConductor().getNombre());
        System.out.println("Tipo: " + tipo);
    }

}
