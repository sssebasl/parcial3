package co.edu.poli.parcial.model;

import java.io.Serializable;

/**
 * Clase que representa una película dentro del sistema de producciones audiovisuales.
 * 
 * <p>Hereda de {@link Vehiculo} e implementa {@link Serializable}
 * para permitir su almacenamiento y recuperación desde archivos.</p>
 * 
 * <p>Incluye un atributo adicional para el género cinematográfico.</p>
 * 
 * @author Felipe Parra
 */
public class Camion extends Vehiculo implements Serializable {

    /** Género cinematográfico de la película */
    private String genero;

    /**
     * Constructor por defecto.
     * <p>Permite crear un objeto {@code Pelicula} sin inicializar sus atributos.</p>
     */
    public Camion() {}

    /**
     * Constructor que inicializa todos los atributos de la película.
     * 
     * @param placa código único de la producción
     * @param marca título de la película
     * @param fechaEstreno año de estreno de la película
     * @param capacidad duración en minutos
     * @param director objeto {@link Conductor} asociado a la película
     * @param genero género cinematográfico de la película
     */
    public Camion(String placa, String marca, int fechaEstreno, int capacidad, Conductor director, String genero) {
        super(placa, marca, fechaEstreno, capacidad, director);
        this.genero = genero;
    }

    /**
     * Obtiene el género de la película.
     * 
     * @return género cinematográfico
     */
    public String getGenero() {
        return genero;
    }

    /**
     * Asigna un nuevo género a la película.
     * 
     * @param genero nuevo género cinematográfico
     */
    public void setGenero(String genero) {
        this.genero = genero;
    }

    /**
     * Muestra la información completa de la película en consola.
     * <p>Sobrescribe el método {@link Vehiculo#mostrarInformacion()}.</p>
     */
    @Override
    public void mostrarInformacion() {
        System.out.println("\n--- Película ---");
        System.out.println("Placa: " + getPlaca());
        System.out.println("Marca : " + getMarca());
        System.out.println("Fecha de estreno: " + getFechaEstreno());
        System.out.println("Capacidad: " + getCapacidadMin() + " kilogramos ");
        System.out.println("Conductor: " + getConductor().getNombre());
        System.out.println("Género: " + genero);
    }
}