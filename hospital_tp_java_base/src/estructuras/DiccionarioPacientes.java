package estructuras;

import modelos.Paciente;
import tdas.dinamicos.DiccionarioTDA;

// IMPLEMENTACIÓN DE DiccionarioTDA - DINÁMICO
// Usa lista enlazada para guardar pacientes con su DNI
public class DiccionarioPacientes implements DiccionarioTDA<String, Paciente> {
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

    @Override
    public boolean put(String dni, Paciente paciente) {
        if (dni == null || paciente == null) {
            return false;
        }

        // Verificar que el DNI no exista
        Nodo actual = cabeza;
        while (actual != null) {
            if (actual.clave.equals(dni)) {
                return false;
            }
            actual = actual.siguiente;
        }

        // Agregar al principio
        Nodo nuevo = new Nodo(dni, paciente);
        nuevo.siguiente = cabeza;
        cabeza = nuevo;
        return true;
    }

    @Override
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

    @Override
    public boolean contiene(String dni) {
        return get(dni) != null;
    }

    @Override
    public int size() {
        int count = 0;
        Nodo actual = cabeza;
        while (actual != null) {
            count++;
            actual = actual.siguiente;
        }
        return count;
    }

    @Override
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
