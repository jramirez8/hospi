package tdas.dinamicos;

// TDA Diccionario - DINÁMICO
// Estructura clave-valor que crece según se agregan elementos
public interface DiccionarioTDA<K, V> {
    
    // Agrega un par clave-valor
    boolean put(K clave, V valor);

    // Busca el valor de una clave
    V get(K clave);

    // Dice si existe una clave
    boolean contiene(K clave);

    // Retorna cuántos elementos hay
    int size();

    // Muestra todos los elementos
    void listar();
}
