package estructuras;

import modelos.Turno;

public class ColaTurnos {
    private Nodo frente;
    private Nodo fin;
    private int cantidad;

    private static class Nodo {
        Turno dato;
        Nodo siguiente;

        Nodo(Turno dato) {
            this.dato = dato;
        }
    }

    public void encolar(Turno turno) {
        Nodo nuevo = new Nodo(turno);
        if (estaVacia()) {
            frente = nuevo;
            fin = nuevo;
        } else {
            fin.siguiente = nuevo;
            fin = nuevo;
        }
        cantidad++;
    }

    public Turno desencolar() {
        if (estaVacia()) {
            return null;
        }

        Turno dato = frente.dato;
        frente = frente.siguiente;
        if (frente == null) {
            fin = null;
        }
        cantidad--;
        return dato;
    }

    public Turno verPrimero() {
        if (estaVacia()) {
            return null;
        }
        return frente.dato;
    }

    public boolean estaVacia() {
        return frente == null;
    }

    public int size() {
        return cantidad;
    }

    public void listar() {
        if (estaVacia()) {
            System.out.println("No hay turnos en esta cola.");
            return;
        }

        Nodo actual = frente;
        while (actual != null) {
            System.out.println(actual.dato);
            actual = actual.siguiente;
        }
    }
}
