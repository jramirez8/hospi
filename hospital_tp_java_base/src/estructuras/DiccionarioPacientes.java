package estructuras;

import tdas.dinamicos.ConjuntoTDA;
import tdas.dinamicos.DiccionarioSimpleTDA;
import modelos.Paciente;

public class DiccionarioPacientes implements DiccionarioSimpleTDA {
    private class NodoDic {
        int dni;
        Paciente paciente;
        NodoDic siguiente;
    }

    private NodoDic primero;

    private NodoDic Clave(int dni) {
        NodoDic aux = primero;
        while (aux != null && aux.dni != dni) {
            aux = aux.siguiente;
        }
        return aux;
    }

    public void InicializarDiccionario() {
        primero = null;
    }

    public void Agregar(int dni, Paciente paciente) {
        NodoDic nodo = Clave(dni);             //Buscamos si el dni existe
        if (nodo == null) {                      //Si el dni no existia.
            nodo = new NodoDic();
            nodo.dni = dni;
            nodo.paciente = paciente;
            nodo.siguiente = primero;
            primero = nodo;                  //Nuevo dni pasa a ser el primero
        }
        nodo.paciente = paciente;
    }

    public void Eliminar(int dni) {
        if (primero != null) {                           //Caso especifico: valor en primera pos
            if (primero.dni == dni) {
                primero = primero.siguiente;             //El segundo pasa a ser primero
            } else {                                     //Es algun otro
                NodoDic aux = primero;
                while (aux.siguiente != null && aux.siguiente.dni != dni) { //Hasta encontrar la coincidencia (desde el anterior)
                    aux = aux.siguiente;
                }
                if (aux != null && aux.siguiente != null) {
                    aux.siguiente = aux.siguiente.siguiente; //La coincidencia se ignora apuntando a su siguiente.
                }
            }
        }
    }

    public Paciente Recuperar(int dni) {
        NodoDic nodo = Clave(dni);                //Busco coincidencia
        return nodo.paciente;                     //Devuelvo el valor de la coincidencia
    }

    public ConjuntoTDA Claves() {
        ConjuntoTDA c = new ConjuntoLD();
        c.InicializarConjunto();

        NodoDic aux = primero;

        while (aux != null) {
            c.Agregar(aux.dni);
            aux = aux.siguiente;
        }

        return c;
    }
}
