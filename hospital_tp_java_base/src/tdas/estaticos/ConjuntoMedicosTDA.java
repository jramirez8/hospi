package tdas.estaticos;

import modelos.Medico;

// TDA Conjunto de Médicos - ESTÁTICO
// Especial para trabajar con médicos, sin duplicados
public interface ConjuntoMedicosTDA {
    
    void InicializarConjunto();

    // Agrega un médico al conjunto
    void Agregar(Medico medico);

    // Medico arbitrario
    Medico Elegir();

    // Dice si existe un médico
    boolean Pertenece(Medico medico);

    // Obtiene un médico por su posición
    void Sacar(Medico medico);

    // True / False
    boolean ConjuntoVacio();
}
