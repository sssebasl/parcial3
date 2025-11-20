package co.edu.poli.parcial.model;

import java.io.Serializable;


public class Conductor implements Serializable {

   
    private String id;

  
    private String nombre;

  
    private String nacionalidad;


    public Conductor() {}

 
    public Conductor(String id, String nombre, String nacionalidad) {
        this.id = id;
        this.nombre = nombre;
        this.nacionalidad = nacionalidad;
    }

   
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

 
    public String getNombre() {
        return nombre;
    }

    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

 
    public String getNacionalidad() {
        return nacionalidad;
    }

   
    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

}
