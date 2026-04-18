package tdas.dinamicos;

import modelos.Paciente;
// TDA Diccionario - DINÁMICO
// Estructura clave-valor
public interface DiccionarioSimpleTDA {
    
    void InicializarDiccionario();

    void Agregar(int dni, Paciente paciente);

    void Eliminar(int dni);

    Paciente Recuperar(int dni);

    ConjuntoTDA Claves();
}
