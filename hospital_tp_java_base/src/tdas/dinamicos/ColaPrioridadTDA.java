package tdas.dinamicos;

// TDA Cola con Prioridad - DINÁMICO
// Estructura que crece según se agregan elementos
public interface ColaPrioridadTDA<T extends Comparable<T>> {
    
    // Inicializa la cola vacía
    void inicializar();

    // Agrega un elemento respetando su prioridad
    void acolar(T elemento);

    // Saca el elemento con mayor prioridad
    T desacolar();

    // Ve cuál es el próximo elemento sin sacarlo
    T verPrimero();

    // Dice si la cola está vacía
    boolean estaVacia();

    // Retorna cuántos elementos hay
    int size();

    // Muestra todos los elementos
    void listar();
}
