package modelos;

public class Turno {
    private Paciente paciente;
    private Medico medico;
    private String especialidad;
    private String fecha;
    private String estado; // PENDIENTE o ATENDIDO
    private int prioridad; // 1=NORMAL, 2=URGENTE, 3=EMERGENCIA

    public Turno(Paciente paciente, Medico medico, String especialidad, String fecha, int prioridad) {
        this.paciente = paciente;
        this.medico = medico;
        this.especialidad = especialidad;
        this.fecha = fecha;
        this.estado = "PENDIENTE";
        this.prioridad = prioridad;
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

        public int getPrioridad() {
        return prioridad;
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
                + " | Estado: " + estado
                + " | Prioridad: " + prioridad;
    }
}
