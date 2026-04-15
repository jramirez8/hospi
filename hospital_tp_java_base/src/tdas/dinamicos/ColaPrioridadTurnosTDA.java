package tdas.dinamicos;

import modelos.Turno;

// TDA Cola de Prioridad de Turnos - DINÁMICO
// Especial para trabajar con turnos del hospital
public interface ColaPrioridadTurnosTDA {
    
    // Inicializa la cola vacía
    void inicializarCola();

    // Agrega un turno respetando su prioridad
    void acolar(Turno turno);

    // Saca el turno con mayor prioridad
    Turno desacolar();

    // Ve el próximo turno sin sacarlo
    Turno verPrimero();

    // Dice si la cola está vacía
    boolean estaVacia();

    // Retorna cuántos turnos hay
    int size();

    // Muestra todos los turnos
    void listar();
}
