package estructuras;

import modelos.Medico;
import tdas.estaticos.ConjuntoEstaticoTDA;

// IMPLEMENTACIÓN DE ConjuntoMedicosTDA - ESTÁTICO
// Usa un arreglo fijo para guardar médicos sin duplicados
public class ConjuntoMedicos implements ConjuntoEstaticoTDA {
    private Medico[] a;
    private int cantidad;

    public void InicializarConjunto() {
        a = new Medico[100];
        cantidad = 0;
    }

    public void Agregar(Medico medico) {
        // No agrega si hay problemas
        if (!this.Pertenece(medico)) {
            a[cantidad] = medico;
            cantidad++;
        }
    }

    public Medico Elegir() {  //No debe estar vacio
        return a[cantidad - 1];
    }

    public boolean Pertenece(Medico medico) {  // True / False
        int i = 0;
        while (i < cantidad && !a[i].equals(medico)) {   //Comparamos por identidad, no por objeto (ya que dos objetos diferentes en memoria pueden referenciar a un mismo medico)
            i++;
        }
        return (i < cantidad);
    }

    public void Sacar(Medico medico) {
        int i = 0;
        while (i < cantidad && !a[i].equals(medico)) {   //Comparacion por identidad nuevamente
            i++;
        }
        if (i < cantidad) {
            a[i] = a[cantidad - 1];   //El valor a eliminar se reemplaza con el ultimo valor (a[cantidad - 1]), y ese ultimo valor se elimina reduciendo uno la catidad.
            cantidad--;
        }
    }

    public boolean ConjuntoVacio() {  // True / False
        return (cantidad == 0);
    }
}
