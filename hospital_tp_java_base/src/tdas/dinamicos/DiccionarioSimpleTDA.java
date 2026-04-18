package tdas.dinamicos;

// TDA Diccionario - DINÁMICO
// Estructura clave-valor
public interface DiccionarioTDA {
    
    void InicializarDiccionario();

    void Agregar(int dni, Paciente paciente);

    void Eliminar(int dni);

    Paciente Recuperar(int dni);

    ConjuntoTDA Claves();
}
