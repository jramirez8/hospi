package tdas.dinamicos;

import modelos.Turno;

// TDA Cola de Prioridad de Turnos - DINÁMICO
// Especial para trabajar con turnos del hospital
public interface ColaPrioridadTDA {
    
    // Inicializa la cola vacía
    void InicializarCola();

    // Agrega un turno respetando su prioridad
    void AcolarPrioridad(Turno turno, int prioridad);

    // Saca el turno con mayor prioridad
    void Desacolar();

    // Ve el próximo turno sin sacarlo
    Turno Primero();

    // Dice si la cola está vacía
    boolean ColaVacia();

    // Devuelve el indice del elemento con mas prioridad
    int Prioridad();
}
