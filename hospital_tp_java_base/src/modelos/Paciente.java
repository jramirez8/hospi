package modelos;

public class Paciente {
    private String dni;
    private String nombre;
    private int edad;
    private String historialMedico;

    public Paciente(String dni, String nombre, int edad, String historialMedico) {
        this.dni = dni;
        this.nombre = nombre;
        this.edad = edad;
        this.historialMedico = historialMedico;
    }

    public String getDni() {
        return dni;
    }

    public String getNombre() {
        return nombre;
    }

    public int getEdad() {
        return edad;
    }

    public String getHistorialMedico() {
        return historialMedico;
    }

    @Override
    public String toString() {
        return "Paciente: " + nombre
                + " | DNI: " + dni
                + " | Edad: " + edad
                + " | Historial: " + historialMedico;
    }
}
