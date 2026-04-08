package estructuras;

import modelos.Medico;

public class ConjuntoMedicos {
    private Medico[] datos;
    private int cantidad;

    public ConjuntoMedicos(int capacidadMaxima) {
        datos = new Medico[capacidadMaxima];
        cantidad = 0;
    }

    public boolean agregar(Medico medico) {
        if (medico == null || cantidad == datos.length || contieneMatricula(medico.getTipoMatricula(), medico.getNumeroMatricula())) {
            return false;
        }
        datos[cantidad] = medico;
        cantidad++;
        return true;
    }

    public boolean contieneMatricula(String tipo, String numero) {
        return buscarPorMatricula(tipo, numero) != null;
    }

    public Medico buscarPorMatricula(String tipo, String numero) {
        for (int i = 0; i < cantidad; i++) {
            if (datos[i].getTipoMatricula().equalsIgnoreCase(tipo)
                    && datos[i].getNumeroMatricula().equals(numero)) {
                return datos[i];
            }
        }
        return null;
    }

    public int size() {
        return cantidad;
    }

    public Medico get(int indice) {
        if (indice < 0 || indice >= cantidad) {
            return null;
        }
        return datos[indice];
    }

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
