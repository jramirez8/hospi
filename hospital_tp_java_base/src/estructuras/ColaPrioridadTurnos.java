package estructuras;

import modelos.Turno;
import tdas.dinamicos.ColaPrioridadTurnosTDA;

// IMPLEMENTACIÓN DE ColaPrioridadTurnosTDA - DINÁMICO
// Usa lista enlazada para que crezca según se agregan turnos
public class ColaPrioridadTurnos implements ColaPrioridadTurnosTDA {
    
    private Nodo frente;
    private int cantidad;

    private class Nodo {
        Turno dato;
        Nodo siguiente;

        Nodo(Turno dato) {
            this.dato = dato;
        }
    }

    @Override
    public void inicializarCola() {
        frente = null;
        cantidad = 0;
    }

    @Override
    public void acolar(Turno turno){
        Nodo nuevo = new Nodo(turno);

        // Si la cola está vacía o el turno tiene más prioridad que el frente
        if (frente == null || turno.getPrioridad() > frente.dato.getPrioridad()){
            nuevo.siguiente = frente;
            frente = nuevo;
        } else {
        // Si no, buscar el lugar correcto para insertar
            Nodo actual = frente;

            while (actual.siguiente != null && actual.siguiente.dato.getPrioridad() >= turno.getPrioridad()){
                actual = actual.siguiente;
            }

            nuevo.siguiente = actual.siguiente;
            actual.siguiente = nuevo;
        }

        cantidad++;
    }

    @Override
    public Turno desacolar(){
        if (estaVacia()) {
            return null;
        }

        Turno dato = frente.dato;
        frente = frente.siguiente;
        cantidad--;

        return dato;
    }

    @Override
    public Turno verPrimero() {
        if (estaVacia()) {
            return null;
        }
        return frente.dato;
    }

    @Override
    public boolean estaVacia() {
        return frente == null;
    }

    @Override
    public int size() {
        return cantidad;
    }

    @Override
    public void listar() {
        if(estaVacia()) {
            System.out.println("No hay turnos en la cola.");
            return;
        }
    
        Nodo actual = frente;
        while (actual != null) {
            System.out.println(actual.dato);
            actual = actual.siguiente;
        }
    }
}
