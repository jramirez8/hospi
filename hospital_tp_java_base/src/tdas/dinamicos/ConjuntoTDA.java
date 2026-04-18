package tdas.dinamicos;

public interface ConjuntoTDA {

    void InicializarConjunto();

    void Agregar(int x);

    void Sacar(int x);

    int Elegir(); //Sacar objeto arbitrariamente

    boolean ConjuntoVacio(); // True / False

    boolean Pertenece(int x) // True / False
}