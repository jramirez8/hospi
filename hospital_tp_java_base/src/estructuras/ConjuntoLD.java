package estructuras;

import tdas.dinamicos.ConjuntoTDA;

public class ConjuntoLD implements ConjuntoTDA {

    private class Nodo {
        int dato;
        Nodo sig;
    }

    private Nodo primero;

    public void InicializarConjunto() {
        primero = null;
    }

    public void Agregar(int x) {
        if (!this.Pertenece(x)) { // evita duplicados
            Nodo nuevo = new Nodo();
            nuevo.dato = x;
            nuevo.sig = primero;
            primero = nuevo;
        }
    }

    public void Sacar(int x) {
        if (primero != null) {
            if (primero.dato == x) {
                primero = primero.sig;
            } else {
                Nodo aux = primero;
                while (aux.sig != null && aux.sig.dato != x) {
                    aux = aux.sig;
                }
                if (aux.sig != null) {
                    aux.sig = aux.sig.sig;
                }
            }
        }
    }

    public int Elegir() {
        return primero.dato; // precondición: no vacío
    }

    public boolean ConjuntoVacio() {
        return primero == null;
    }

    public boolean Pertenece(int x) {
        Nodo aux = primero;
        while (aux != null && aux.dato != x) {
            aux = aux.sig;
            }
            return aux != null;
        }
    }