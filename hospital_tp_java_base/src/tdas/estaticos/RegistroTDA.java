package tdas.estaticos;

// TDA Registro - ESTÁTICO
// Estructura que guarda datos en orden con capacidad máxima fija
public interface RegistroTDA<T> {
    
    // Agrega un elemento al registro
    boolean agregar(T elemento);

    // Obtiene un elemento por su posición
    T get(int indice);

    // Modifica un elemento en una posición
    boolean actualizar(int indice, T elemento);

    // Retorna cuántos elementos hay
    int size();

    // Retorna la capacidad máxima
    int capacidad();

    // Dice si el registro está vacío
    boolean estaVacio();

    // Dice si el registro está lleno
    boolean estaLleno();

    // Muestra todos los elementos
    void listar();
}
