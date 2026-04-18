package modelos;

public class Turno {
    private Paciente paciente;
    private Medico medico;
    private String fecha;
    private static final String PENDIENTE = "PENDIENTE";
    private static final String ATENDIDO = "ATENDIDO";
    private String estado;

    public Turno(Paciente paciente, Medico medico, String fecha) {
        this.paciente = paciente;
        this.medico = medico;
        this.fecha = fecha;
        this.estado = PENDIENTE;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public Medico getMedico() {
        return medico;
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
                + " | Médico: " + medico.getNombreCompleto()
                + " | Especialidad: " + medico.getEspecialidad()
                + " | Fecha: " + fecha
                + " | Estado: " + estado;
    }
}
