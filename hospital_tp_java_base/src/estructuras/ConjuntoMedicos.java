package estructuras;

import modelos.Medico;
import tdas.estaticos.ConjuntoMedicosTDA;

// IMPLEMENTACIÓN DE ConjuntoMedicosTDA - ESTÁTICO
// Usa un arreglo fijo para guardar médicos sin duplicados
public class ConjuntoMedicos implements ConjuntoMedicosTDA {
    private Medico[] datos;
    private int cantidad;

    public ConjuntoMedicos(int capacidadMaxima) {
        datos = new Medico[capacidadMaxima];
        cantidad = 0;
    }

    @Override
    public boolean agregar(Medico medico) {
        // No agrega si hay problemas
        if (medico == null || cantidad == datos.length || contieneMatricula(medico.getTipoMatricula(), medico.getNumeroMatricula())) {
            return false;
        }
        datos[cantidad] = medico;
        cantidad++;
        return true;
    }

    @Override
    public boolean contieneMatricula(String tipo, String numero) {
        return buscarPorMatricula(tipo, numero) != null;
    }

    @Override
    public Medico buscarPorMatricula(String tipo, String numero) {
        for (int i = 0; i < cantidad; i++) {
            if (datos[i].getTipoMatricula().equalsIgnoreCase(tipo)
                    && datos[i].getNumeroMatricula().equals(numero)) {
                return datos[i];
            }
        }
        return null;
    }

    @Override
    public int size() {
        return cantidad;
    }

    @Override
    public Medico get(int indice) {
        if (indice < 0 || indice >= cantidad) {
            return null;
        }
        return datos[indice];
    }

    @Override
    public void listar() {
        if (cantidad == 0) {
            System.out.println("No hay médicos cargados.");
            return;
        }

        for (int i = 0; i < cantidad; i++) {
            System.out.println((i + 1) + ". " + datos[i]);
        }
    }
}
