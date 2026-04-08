package modelos;

public class Turno {
    private Paciente paciente;
    private Medico medico;
    private String especialidad;
    private String fecha;
    private String estado; // PENDIENTE o ATENDIDO

    public Turno(Paciente paciente, Medico medico, String especialidad, String fecha) {
        this.paciente = paciente;
        this.medico = medico;
        this.especialidad = especialidad;
        this.fecha = fecha;
        this.estado = "PENDIENTE";
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public Medico getMedico() {
        return medico;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public String getFecha() {
        return fecha;
    }

    public String getEstado() {
        return estado;
    }

    public void marcarAtendido() {
        this.estado = "ATENDIDO";
    }

    @Override
    public String toString() {
        return "Turno | Paciente: " + paciente.getNombre()
                + " | DNI: " + paciente.getDni()
                + " | Médico: " + medico.getNombreCompleto()
                + " | Especialidad: " + especialidad
                + " | Fecha: " + fecha
                + " | Estado: " + estado;
    }
}
