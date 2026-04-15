package tdas.estaticos;

// TDA Conjunto - ESTÁTICO
// Estructura que no crece, tiene capacidad máxima fija
public interface ConjuntoTDA<T> {
    
    // Agrega un elemento al conjunto
    boolean agregar(T elemento);

    // Dice si el conjunto contiene un elemento
    boolean contiene(T elemento);

    // Retorna cuántos elementos hay
    int size();

    // Obtiene un elemento por su posición
    T get(int indice);

    // Dice si el conjunto está vacío
    boolean estaVacio();

    // Dice si el conjunto está lleno
    boolean estaLleno();

    // Muestra todos los elementos
    void listar();
}
