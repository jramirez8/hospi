package estructuras;

import modelos.Turno;

public class RegistroAtenciones {
    private Nodo cabeza;

    private static class Nodo {
        Turno dato;
        Nodo siguiente;

        Nodo(Turno dato) {
            this.dato = dato;
        }
    }

    public void agregar(Turno turno) {
        Nodo nuevo = new Nodo(turno);
        nuevo.siguiente = cabeza;
        cabeza = nuevo;
    }

    public void listarPorMatricula(String matriculaCompleta) {
        Nodo actual = cabeza;
        boolean encontrado = false;

        while (actual != null) {
            if (actual.dato.getMedico().getMatriculaCompleta().equalsIgnoreCase(matriculaCompleta)) {
                System.out.println(actual.dato);
                encontrado = true;
            }
            actual = actual.siguiente;
        }

        if (!encontrado) {
            System.out.println("No hay turnos atendidos para ese médico.");
        }
    }

    public int contarPorEspecialidad(String especialidad) {
        int contador = 0;
        Nodo actual = cabeza;

        while (actual != null) {
            if (actual.dato.getEspecialidad().equalsIgnoreCase(especialidad)) {
                contador++;
            }
            actual = actual.siguiente;
        }

        return contador;
    }
}
