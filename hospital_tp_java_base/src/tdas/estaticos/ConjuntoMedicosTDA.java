package tdas.estaticos;

import modelos.Medico;

// TDA Conjunto de Médicos - ESTÁTICO
// Especial para trabajar con médicos, sin duplicados
public interface ConjuntoMedicosTDA {
    
    // Agrega un médico al conjunto
    boolean agregar(Medico medico);

    // Busca un médico por su matrícula
    Medico buscarPorMatricula(String tipoMatricula, String numeroMatricula);

    // Dice si existe un médico con esa matrícula
    boolean contieneMatricula(String tipoMatricula, String numeroMatricula);

    // Retorna cuántos médicos hay
    int size();

    // Obtiene un médico por su posición
    Medico get(int indice);

    // Muestra todos los médicos
    void listar();
}
