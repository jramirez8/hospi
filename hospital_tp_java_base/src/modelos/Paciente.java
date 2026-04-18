package modelos;

public class Paciente {
    private int dni;
    private String nombre;
    private int edad;

    public Paciente(int dni, String nombre, int edad) {
        this.dni = dni;
        this.nombre = nombre;
        this.edad = edad;
    }

    public int getDni() {
        return dni;
    }

    public String getNombre() {
        return nombre;
    }

    public int getEdad() {
        return edad;
    }

    @Override
    public String toString() {
        return "Paciente: " + nombre
                + " | DNI: " + dni
                + " | Edad: " + edad;
    }
}
