package es.aritzherrero.ejerciciob.Model;

import java.util.Objects;


public class Persona {

    // Variables de clase
    private  String nombre;
    private  String apellidos;
    private  int edad;

    // Constructor
    public Persona(String nom,String ape,int edad) {
        this.nombre = (nom);
        this.apellidos = (ape);
        this.edad =(edad);
    }


    // Métodos getter y setter
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String sNombre) {
        this.nombre = sNombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String sApellidos) {
        this.apellidos =sApellidos;
    }

    public Integer getEdad() {
        return edad;
    }

    public void setEdad(int nEdad) {
        this.edad = nEdad;
    }



    @Override
    public int hashCode() {
        return Objects.hash(edad, apellidos, nombre);
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Persona other = (Persona) obj;
        return edad == other.edad && Objects.equals(apellidos, other.apellidos)
                && Objects.equals(nombre, other.nombre);
    }

}