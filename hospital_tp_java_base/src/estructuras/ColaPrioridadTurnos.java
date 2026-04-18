package estructuras;

import modelos.Turno;
import tdas.dinamicos.ColaPrioridadTurnosTDA;

// IMPLEMENTACIÓN DE ColaPrioridadTurnosTDA - DINÁMICO
// Usa lista enlazada para que crezca según se agregan turnos
public class ColaPrioridadTurnos implements ColaPrioridadTurnosTDA {
    
    private Nodo primero;

    private class Nodo {
        Turno dato;
        int prioridad;
        Nodo siguiente;

        Nodo(Turno dato, int prioridad) {
            this.dato = dato;
            this.prioridad = prioridad;
        }
    }

    public void InicializarCola() {
        primero = null;
    }

    public void AcolarPrioridad(Turno turno, int prioridad){
        Nodo nuevo = new Nodo(turno, prioridad);

        // Si la cola está vacía o el turno tiene más prioridad que el frente
        if (primero == null || primero.prioridad > prioridad){
            nuevo.siguiente = primero; //Nuevo nodo al principio
            primero = nuevo;
        } else {
        // Si no, buscar el lugar correcto para insertar
            Nodo aux = primero;

            while (aux.siguiente != null && aux.siguiente.prioridad <= prioridad){
                aux = aux.siguiente;
            }

            nuevo.siguiente = aux.siguiente;
            aux.siguiente = nuevo;
        }

    }

    public void Desacolar(){
        primero = primero.siguiente; //Se olvida el primero (primero pasa al siguiente)
    }

    public Turno Primero() {
        return primero.dato;
    }

    public boolean ColaVacia() {
        return (primero == null);
    }

    public int Prioridad() {
        return primero.prioridad;
    }
}
