package estructuras;

import modelos.Turno;

public class GestorColasPorEspecialidad {
    private EspecialidadCola[] datos;
    private int cantidad;

    private static class EspecialidadCola {
        String especialidad;
        ColaPrioridadTurnos cola;

        EspecialidadCola(String especialidad) {
            this.especialidad = especialidad;
            this.cola = new ColaPrioridadTurnos();
            this.cola.inicializarCola();
        }
    }

    public GestorColasPorEspecialidad(int capacidadMaxima) {
        datos = new EspecialidadCola[capacidadMaxima];
        cantidad = 0;
    }

    private EspecialidadCola buscarEspecialidad(String especialidad) {
        for (int i = 0; i < cantidad; i++) {
            if (datos[i].especialidad.equalsIgnoreCase(especialidad)) {
                return datos[i];
            }
        }
        return null;
    }

    public void encolarTurno(String especialidad, Turno turno) {
        EspecialidadCola ec = buscarEspecialidad(especialidad);

        if (ec == null) {
            if (cantidad == datos.length) {
                System.out.println("No se pueden registrar más especialidades.");
                return;
            }
            ec = new EspecialidadCola(especialidad);
            datos[cantidad] = ec;
            cantidad++;
        }

        ec.cola.encolar(turno);
    }

    public Turno atenderSiguiente(String especialidad) {
        EspecialidadCola ec = buscarEspecialidad(especialidad);
        if (ec == null) {
            return null;
        }
        return ec.cola.desencolar();
    }

    public void listarCola(String especialidad) {
        EspecialidadCola ec = buscarEspecialidad(especialidad);
        if (ec == null) {
            System.out.println("No existe cola para esa especialidad.");
            return;
        }
        ec.cola.listar();
    }

    public void listarEspecialidadesYConteoPendientes() {
        if (cantidad == 0) {
            System.out.println("No hay especialidades con turnos cargados.");
            return;
        }

        for (int i = 0; i < cantidad; i++) {
            System.out.println("Especialidad: " + datos[i].especialidad + " | Pendientes: " + datos[i].cola.size());
        }
    }

    public int cantidadEspecialidades() {
        return cantidad;
    }

    public String getEspecialidadEn(int indice) {
        if (indice < 0 || indice >= cantidad) {
            return null;
        }
        return datos[indice].especialidad;
    }

    public void listarTurnosProximosPorMedico(String matriculaCompleta) {
        boolean encontrado = false;

        for (int i = 0; i < cantidad; i++) {
            ColaPrioridadTurnos cola = datos[i].cola;
            ColaPrioridadTurnos auxiliar = new ColaPrioridadTurnos();
            auxiliar.inicializarCola();

            while (!cola.estaVacia()) {
                Turno t = cola.desencolar();
                if (t.getMedico().getMatriculaCompleta().equalsIgnoreCase(matriculaCompleta)) {
                    System.out.println(t);
                    encontrado = true;
                }
                auxiliar.encolar(t);
            }

            while (!auxiliar.estaVacia()) {
                cola.encolar(auxiliar.desencolar());
            }
        }

        if (!encontrado) {
            System.out.println("No hay turnos próximos para ese médico.");
        }
    }
}
