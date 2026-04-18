package sistema;

import estructuras.*;
import modelos.*;
import tdas.dinamicos.*;
import tdas.estaticos.ConjuntoEstaticoTDA;

import java.util.Scanner;

public class SistemaHospital {

    private Scanner scanner;

    // =========================
    // TDAs
    // =========================
    private ConjuntoEstaticoTDA medicos;
    private DiccionarioSimpleTDA pacientes;

    private ColaPrioridadTDA colaCardiologia;
    private ColaPrioridadTDA colaPediatria;
    private ColaPrioridadTDA colaTraumatologia;

    // =========================
    // CONTADORES REPORTE
    // =========================
    private int atendidosCardiologia;
    private int atendidosPediatria;
    private int atendidosTraumatologia;

    // =========================
    // CONSTRUCTOR
    // =========================
    public SistemaHospital() {
        scanner = new Scanner(System.in);

        medicos = new ConjuntoMedicos();
        medicos.InicializarConjunto();

        pacientes = new DiccionarioPacientes();
        pacientes.InicializarDiccionario();

        colaCardiologia = new ColaPrioridadTurnos();
        colaCardiologia.InicializarCola();

        colaPediatria = new ColaPrioridadTurnos();
        colaPediatria.InicializarCola();

        colaTraumatologia = new ColaPrioridadTurnos();
        colaTraumatologia.InicializarCola();
    }

    // =========================
    // MENU PRINCIPAL
    // =========================
    public void iniciar() {
        int op;
        do {
            System.out.println("\n===== HOSPITAL =====");
            System.out.println("1. Médicos");
            System.out.println("2. Pacientes");
            System.out.println("3. Turnos");
            System.out.println("4. Reporte");
            System.out.println("0. Salir");

            // Validamos que solo pueda elegir opciones del 0 al 4
            op = leerEnteroRango("Opción: ", 0, 4);

            switch (op) {
                case 1: menuMedicos(); break;
                case 2: menuPacientes(); break;
                case 3: menuTurnos(); break;
                case 4: reporte(); break;
            }
        } while (op != 0);
    }

    // =========================
    // MÉDICOS
    // =========================
    private void menuMedicos() {
        String nombre = leerTexto("Nombre: ");
        String apellido = leerTexto("Apellido: ");

        System.out.println("Especialidad: 1.Cardio 2.Pedia 3.Trauma");    
        // Validamos que solo ingrese 1, 2 o 3
        int esp = leerEnteroRango("Opción especialidad: ", 1, 3);

        String horario = leerTexto("Horario: ");

        System.out.println("Tipo matrícula: 1.Provincial 2.Nacional");
        // Validamos que solo ingrese 1 o 2
        int tipo = leerEnteroRango("Opción matrícula: ", 1, 2);

        String numero = leerTexto("Número matrícula: ");

        Medico m = new Medico(nombre, apellido, esp, horario, tipo, numero);
        medicos.Agregar(m);
        System.out.println("Médico agregado con éxito.");
    }

    // =========================
    // PACIENTES
    // =========================
    private void menuPacientes() {
        int dni = leerEntero("DNI: "); // Acá podrías usar un leerEnteroRango si querés evitar DNIs negativos
        String nombre = leerTexto("Nombre: ");
        
        // Validamos una edad lógica para un paciente (ej: entre 0 y 120 años)
        int edad = leerEnteroRango("Edad: ", 0, 120);

        Paciente p = new Paciente(dni, nombre, edad);
        pacientes.Agregar(dni, p);
        System.out.println("Paciente agregado con éxito.");
    }

    // =========================
    // TURNOS
    // =========================
    private void menuTurnos() {
        int dni = leerEntero("DNI paciente: ");
        Paciente p = pacientes.Recuperar(dni);

        if (p == null) {
            System.out.println("Error: No se encontró ningún paciente con ese DNI.");
            return;
        }

        // Asumo que elegís al médico con tu método actual
        Medico m = medicos.Elegir(); 
        
        if (m == null) {
            System.out.println("Error: No hay médicos disponibles.");
            return;
        }

        String fecha = leerTexto("Fecha (ej. DD/MM/AAAA): ");
        
        // Validamos que la prioridad solo pueda ser 1, 2 o 3
        int prioridad = leerEnteroRango("Prioridad (1 alta - 3 baja): ", 1, 3);

        Turno t = new Turno(p, m, fecha);

        if (m.getEspecialidad().equals("CARDIOLOGIA")) {
            colaCardiologia.AcolarPrioridad(t, prioridad);
        } else if (m.getEspecialidad().equals("PEDIATRIA")) {
            colaPediatria.AcolarPrioridad(t, prioridad);
        } else if (m.getEspecialidad().equals("TRAUMATOLOGIA")) {
            colaTraumatologia.AcolarPrioridad(t, prioridad);
        }
        
        System.out.println("Turno asignado con éxito.");
    }

    // =========================
    // ATENDER
    // =========================
    private void atender(String esp) {

        ColaPrioridadTDA cola = obtenerCola(esp);

        if (cola.ColaVacia()) return;

        Turno t = cola.Primero();
        cola.Desacolar();

        t.marcarAtendido();

        if (esp.equals("CARDIOLOGIA")) atendidosCardiologia++;
        if (esp.equals("PEDIATRIA")) atendidosPediatria++;
        if (esp.equals("TRAUMATOLOGIA")) atendidosTraumatologia++;
    }

    private ColaPrioridadTDA obtenerCola(String esp) {
        if (esp.equals("CARDIOLOGIA")) return colaCardiologia;
        if (esp.equals("PEDIATRIA")) return colaPediatria;
        return colaTraumatologia;
    }

    // =========================
    // REPORTE
    // =========================
    private void reporte() {
        System.out.println("Cardio: " + atendidosCardiologia);
        System.out.println("Pedia: " + atendidosPediatria);
        System.out.println("Trauma: " + atendidosTraumatologia);
    }

    // =========================
    // INPUT 
    // =========================

    private String leerTexto(String msg) {
        String input;
        do {
            System.out.print(msg);
            input = scanner.nextLine().trim(); // trim() quita espacios en blanco al principio y al final
            if (input.isEmpty()) {
                System.out.println("Error: El campo no puede estar vacío. Intentá de nuevo.");
            }
        } while (input.isEmpty());
        return input;
    }

    private int leerEntero(String msg) {
        int numero = 0;
        boolean valido = false;
        while (!valido) {
            System.out.print(msg);
            try {
                // Usamos Integer.parseInt(nextLine()) para evitar el bug del salto de línea del Scanner
                numero = Integer.parseInt(scanner.nextLine().trim());
                valido = true;
            } catch (NumberFormatException e) {
                System.out.println("Error: Tenés que ingresar un número entero válido.");
            }
        }
        return numero;
    }
    private int leerEnteroRango(String msg, int min, int max) {
        int numero;
        boolean valido = false;
        do {
            numero = leerEntero(msg);
            if (numero >= min && numero <= max) {
                valido = true;
            } else {
                System.out.println("Error: Por favor, ingresá una opción entre " + min + " y " + max + ".");
            }
        } while (!valido);
        return numero;
    }
    
}