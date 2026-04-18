package modelos;

public class Medico {
    private String nombre;
    private String apellido;
    private String especialidad; //Cardiología  Pediatría  Traumatología
    private String horarioDisponible;
    private String tipoMatricula; // P o N
    private String numeroMatricula;

    public Medico(String nombre, String apellido, String especialidad,
                  String horarioDisponible, String tipoMatricula, String numeroMatricula) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.especialidad = especialidad;
        this.horarioDisponible = horarioDisponible;
        this.tipoMatricula = tipoMatricula.toUpperCase();
        this.numeroMatricula = numeroMatricula;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public String getNombreCompleto() {
        return nombre + " " + apellido;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public String getHorarioDisponible() {
        return horarioDisponible;
    }

    public String getTipoMatricula() {
        return tipoMatricula;
    }

    public String getNumeroMatricula() {
        return numeroMatricula;
    }

    public String getMatriculaCompleta() {
        return tipoMatricula + "-" + numeroMatricula;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Medico) {  //Si el objeto que se pasó es un medico
            Medico m = (Medico) o;  //Se convierte "o" a tipo medico
            return this.getMatriculaCompleta().equals(m.getMatriculaCompleta());  //Condicion para que sean iguales
        }
        return false;
    }

    @Override
    public String toString() {
        return "Médico: " + getNombreCompleto()
                + " | Matrícula: " + getMatriculaCompleta()
                + " | Especialidad: " + especialidad
                + " | Horario: " + horarioDisponible;
    }
}
