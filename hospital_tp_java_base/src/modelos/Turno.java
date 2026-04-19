package modelos;

public class Turno {
    private Paciente paciente;
    private String fecha;
    private static final String PENDIENTE = "PENDIENTE";
    private static final String ATENDIDO = "ATENDIDO";
    private String estado;
    private int especialidad;

    public Turno(Paciente paciente, int especialidad, String fecha) {
        this.paciente = paciente;
        this.especialidad = especialidad;
        this.fecha = fecha;
        this.estado = PENDIENTE;
    }

    public Paciente getPaciente() {
        return paciente;
    }
    public int getEspecialidad() {
        return especialidad;
    }

    public String getFecha() {
        return fecha;
    }

    public String getEstado() {
        return estado;
    }

    public void marcarAtendido() {
        this.estado = ATENDIDO;
    }

    public String toString() {
        return "Turno | Paciente: " + paciente.getNombre()
                + " | DNI: " + paciente.getDni()
                + " | Especialidad: " + especialidad
                + " | Fecha: " + fecha
                + " | Estado: " + estado;
    }
}
