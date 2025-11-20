package co.edu.poli.parcial.model;

import java.io.Serializable;

/**
 * Clase abstracta que representa una producción audiovisual, 
 * como una película o una serie.
 * 
 * <p>Contiene los atributos y métodos comunes que comparten
 * todas las producciones del sistema. 
 * Implementa la interfaz {@link Serializable} para permitir
 * su almacenamiento y recuperación desde archivos.</p>
 * 
 * @author Felipe Parra
 */
public abstract class Vehiculo implements Serializable {

    /** Código único que identifica la producción */
    private String placa;

    /** Título de la producción */
    private String marca;

    /** Año de estreno de la producción */
    private int fechaEstreno;

    /** Duración de la producción en minutos */
    private int capacidadMin;

    /** Director responsable de la producción */
    private Conductor conductor;

    /**
     * Constructor vacío por defecto.
     * Permite crear una producción sin inicializar sus atributos.
     */
    public Vehiculo() {}

    /**
     * Constructor que inicializa todos los atributos de la producción.
     * 
     * @param placa código único de la producción
     * @param marca título de la producción
     * @param fechaEstreno año de estreno
     * @param capacidadMin duración en minutos
     * @param conductor objeto {@link Conductor} asociado a la producción
     */
    public Vehiculo(String placa, String marca, int fechaEstreno, int capacidadMin, Conductor conductor) {
        this.placa = placa  != null ? placa : "";
        this.marca = marca;
        this.fechaEstreno = fechaEstreno;
        this.capacidadMin = capacidadMin;
        this.conductor = conductor;
    }

    /** @return código de la producción */
    public String getPlaca() { return placa; }

    /** @param placa nuevo código de la producción */
    public void setPlaca(String placa) { this.placa = placa; }

    /** @return título de la producción */
    public String getMarca() { return marca; }

    /** @param marca nuevo título de la producción */
    public void setMarca(String marca) { this.marca = marca; }

    /** @return año de estreno de la producción */
    public int getFechaEstreno() { return fechaEstreno; }

    /** @param fechaEstreno nuevo año de estreno */
    public void setFechaEstreno(int fechaEstreno) { this.fechaEstreno = fechaEstreno; }

    /** @return duración de la producción en minutos */
    public int getCapacidadMin() { return capacidadMin; }

    /** @param capacidadMin nueva duración en minutos */
    public void setCapacidadMin(int capacidadMin) { this.capacidadMin = capacidadMin; }

    /** @return director asociado a la producción */
    public Conductor getConductor() { return conductor; }

    /** @param conductor nuevo director asociado */
    public void setConductor(Conductor conductor) { this.conductor = conductor; }

    /**
     * Muestra la información general de la producción audiovisual.
     * 
     * <p>Este método debe ser implementado por las subclases 
     * (por ejemplo, {@code Camion} o {@code Serie}).</p>
     */
    public abstract void mostrarInformacion();
}