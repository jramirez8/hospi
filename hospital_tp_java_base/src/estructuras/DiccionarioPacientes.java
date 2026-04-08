package estructuras;

import modelos.Paciente;

public class DiccionarioPacientes {
    private Nodo cabeza;

    private static class Nodo {
        String clave;
        Paciente valor;
        Nodo siguiente;

        Nodo(String clave, Paciente valor) {
            this.clave = clave;
            this.valor = valor;
        }
    }

    public boolean put(String dni, Paciente paciente) {
        if (dni == null || paciente == null) {
            return false;
        }

        Nodo actual = cabeza;
        while (actual != null) {
            if (actual.clave.equals(dni)) {
                return false;
            }
            actual = actual.siguiente;
        }

        Nodo nuevo = new Nodo(dni, paciente);
        nuevo.siguiente = cabeza;
        cabeza = nuevo;
        return true;
    }

    public Paciente get(String dni) {
        Nodo actual = cabeza;
        while (actual != null) {
            if (actual.clave.equals(dni)) {
                return actual.valor;
            }
            actual = actual.siguiente;
        }
        return null;
    }

    public boolean contiene(String dni) {
        return get(dni) != null;
    }

    public void listar() {
        if (cabeza == null) {
            System.out.println("No hay pacientes cargados.");
            return;
        }

        Nodo actual = cabeza;
        while (actual != null) {
            System.out.println(actual.valor);
            actual = actual.siguiente;
        }
    }
}
