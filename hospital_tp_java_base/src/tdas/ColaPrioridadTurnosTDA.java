package tdas;

import modelos.Turno;

public interface ColaPrioridadTurnosTDA {
    
    void inicializarCola();

    void encolar(Turno turno);

    Turno desencolar();

    Turno verPrimero();

    boolean estaVacia();

    int size();

    void listar();
}
