package co.edu.poli.parcial.model;

import java.io.Serializable;

/**
 * Clase que representa una serie dentro del sistema de producciones audiovisuales.
 * 
 * <p>Hereda de {@link Vehiculo} e implementa {@link Serializable}
 * para permitir la persistencia de los datos en archivos.</p>
 * 
 * <p>Incluye un atributo adicional que define el número de temporadas de la serie.</p>
 * 
 * @author Felipe Parra
 */
public class Bus extends Vehiculo implements Serializable {

    /** Número total de temporadas que tiene la serie */
    private int numeroTemporadas;

    /**
     * Constructor por defecto.
     * <p>Permite crear una instancia de {@code Serie} sin inicializar sus atributos.</p>
     */
    public Bus() {}

    /**
     * Constructor que inicializa todos los atributos de la serie.
     * 
     * @param placa código único de la producción
     * @param marca título de la serie
     * @param fechaEstreno año de estreno de la serie
     * @param capacidadMin duración promedio por episodio en minutos
     * @param director objeto {@link Conductor} asociado a la serie
     * @param numeroTemporadas número total de temporadas
     */
    public Bus(String placa, String marca, int fechaEstreno, int capacidadMin, Conductor director, int numeroTemporadas) {
        super(placa, marca, fechaEstreno, capacidadMin, director);
        this.numeroTemporadas = numeroTemporadas;
    }

    /**
     * Obtiene el número de temporadas de la serie.
     * 
     * @return número de temporadas
     */
    public int getNumeroTemporadas() {
        return numeroTemporadas;
    }

    /**
     * Asigna un nuevo número de temporadas a la serie.
     * 
     * @param numeroTemporadas nuevo número de temporadas
     */
    public void setNumeroTemporadas(int numeroTemporadas) {
        this.numeroTemporadas = numeroTemporadas;
    }

    /**
     * Muestra la información completa de la serie en consola.
     * <p>Sobrescribe el método {@link Vehiculo#mostrarInformacion()}.</p>
     */
    @Override
    public void mostrarInformacion() {
        System.out.println("\n--- Bus ---");
        System.out.println("Placa: " + getPlaca());
        System.out.println("Título: " + getMarca());
        System.out.println("Fecha de estreno: " + getFechaEstreno());
        System.out.println("Capacidad: " + getCapacidadMin() + " kilogramos ");
        System.out.println("Director: " + getConductor().getNombre());
        System.out.println("Número de temporadas: " + numeroTemporadas);
    }
}