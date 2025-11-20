package co.edu.poli.parcial.model;

import java.io.Serializable;


public class Bus extends Vehiculo implements Serializable {

   
    private int numeroSillas;

 
    public Bus() {}

 
    public Bus(String placa, String marca, int fechaManofactura, int capacidadMin, Conductor conductor, int numeroSillas) {
        super(placa, marca, FechaManofactura, capacidadMin, conductor);
        this.numeroSillas = numeroSillas;
    }


    public int getNumeroSillas() {
        return numeroSillas;
    }

  
    public void setNumeroSillas(int numeroSillas) {
        this.numeroSillas = numeroSillas;
    }

  
    @Override
    public void mostrarInformacion() {
        System.out.println("\n--- Bus ---");
        System.out.println("Placa: " + getPlaca());
        System.out.println("Marca: " + getMarca());
        System.out.println("Fecha de Manofacturacion: " + getFechaManofactura());
        System.out.println("Capacidad: " + getCapacidadMin() + " kilogramos ");
        System.out.println("Conductor: " + getConductor().getNombre());
        System.out.println("Numero de Sillas: " + numeroSillas);
    }

}
