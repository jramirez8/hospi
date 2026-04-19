package modelos;

public class Medico {

    // =========================
    // ESPECIALIDADES FIJAS
    // =========================
    public static final String CARDIOLOGIA = "CARDIOLOGIA";
    public static final String PEDIATRIA = "PEDIATRIA";
    public static final String TRAUMATOLOGIA = "TRAUMATOLOGIA";

    // =========================
    // TIPO DE MATRÍCULA
    // =========================
    public static final String MATRICULA_PROFESIONAL = "P";
    public static final String MATRICULA_NACIONAL = "N";

    private String nombre;
    private String apellido;
    private int especialidad;
    private String horarioDisponible;
    private String tipoMatricula;
    private String numeroMatricula;

    // =========================
    // CONSTRUCTOR
    // =========================
    public Medico(String nombre, String apellido,
                   int opcionEspecialidad,
                   String horarioDisponible,
                   int opcionMatricula,
                   String numeroMatricula) {

        this.nombre = nombre;
        this.apellido = apellido;
        this.horarioDisponible = horarioDisponible;
        this.numeroMatricula = numeroMatricula;

        // -------------------------
        // ASIGNAR ESPECIALIDAD
        // -------------------------
        switch (opcionEspecialidad) {   //1 cardio  2 pedia  3 trauma
            case 1:
                this.especialidad = 1;
                break;
            case 2:
                this.especialidad = 2;
                break;
            case 3:
                this.especialidad = 3;
                break;
            default:
                this.especialidad = 1; // fallback
        }

        // -------------------------
        // ASIGNAR MATRÍCULA
        // -------------------------
        switch (opcionMatricula) {
            case 1:
                this.tipoMatricula = MATRICULA_PROFESIONAL;
                break;
            case 2:
                this.tipoMatricula = MATRICULA_NACIONAL;
                break;
            default:
                this.tipoMatricula = MATRICULA_PROFESIONAL;
        }
    }

    // =========================
    // GETTERS
    // =========================
    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public String getNombreCompleto() {
        return nombre + " " + apellido;
    }

    public int getEspecialidad() {
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

    // =========================
    // EQUALS (por matrícula)
    // =========================
    @Override
    public boolean equals(Object o) {
        if (o instanceof Medico) {
            Medico m = (Medico) o;
            return this.getMatriculaCompleta()
                    .equals(m.getMatriculaCompleta());
        }
        return false;
    }

    // =========================
    // TO STRING
    // =========================
    @Override
    public String toString() {
        return "Médico: " + getNombreCompleto()
                + " | Matrícula: " + getMatriculaCompleta()
                + " | Especialidad: " + especialidad
                + " | Horario: " + horarioDisponible;
    }
}
