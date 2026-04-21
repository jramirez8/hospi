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
    // =========================
        // CARGA AUTOMÁTICA DE PACIENTES
        // =========================
        Paciente p1 = new Paciente(12345678, "Juan Pérez", 30);
        pacientes.Agregar(12345678, p1);

        Paciente p2 = new Paciente(87654321, "María García", 25);
        pacientes.Agregar(87654321, p2);

        Paciente p3 = new Paciente(11223344, "Carlos López", 40);
        pacientes.Agregar(11223344, p3);

        // =========================
        // CARGA AUTOMÁTICA DE MÉDICOS
        // =========================
        Medico m1 = new Medico("Ana", "Ruiz", 1, "9-17", 1, "123");
        medicos.Agregar(m1);

        Medico m2 = new Medico("Pedro", "Sánchez", 2, "8-16", 2, "456");
        medicos.Agregar(m2);

        Medico m3 = new Medico("Laura", "Fernández", 3, "10-18", 1, "789");
        medicos.Agregar(m3);
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
        int op;

        do {
            System.out.println("\n--- MÉDICOS ---");
            System.out.println("1. Agregar médico");
            System.out.println("2. Iniciar atención");
            System.out.println("3. Eliminar médico");
            System.out.println("0. Volver");

            op = leerEnteroRango("Opción: ", 0, 3);

            switch (op) {
                case 1:
                    agregarMedico();
                    break;
                case 2:
                    iniciarAtencionMedico();
                    break;
                case 3:
                    eliminarMedico();
                    break;
            }

        } while (op != 0);
    }

    private void agregarMedico() {
        String nombre = leerTexto("Nombre: ");
        String apellido = leerTexto("Apellido: ");

        System.out.println("Especialidad: 1.Cardio 2.Pedia 3.Trauma");
        int esp = leerEnteroRango("Opción especialidad: ", 1, 3);

        String horario = leerTexto("Horario: ");

        System.out.println("Tipo matrícula: 1.Provincial 2.Nacional");
        int tipo = leerEnteroRango("Opción matrícula: ", 1, 2);

        String numero = leerTexto("Número matrícula: ");

        Medico m = new Medico(nombre, apellido, esp, horario, tipo, numero);
        medicos.Agregar(m);

        if (medicos.Pertenece(m)) {
            System.out.println("Ya existe un médico con esa matrícula.");
            return;
        }
        System.out.println("Médico agregado con éxito.");
    }

    private Medico buscarMedicoPorMatricula(String matricula) {
        ConjuntoEstaticoTDA aux = new ConjuntoMedicos();
        aux.InicializarConjunto();

        Medico encontrado = null;

        while (!medicos.ConjuntoVacio()) {
            Medico m = medicos.Elegir();
            medicos.Sacar(m);

            if (m.getMatriculaCompleta().equalsIgnoreCase(matricula)) {
                encontrado = m;
            }

            aux.Agregar(m);
        }

        // restaurar
        while (!aux.ConjuntoVacio()) {
            Medico m = aux.Elegir();
            aux.Sacar(m);
            medicos.Agregar(m);
        }

        return encontrado;
    }

    private void iniciarAtencionMedico() {
        String matricula = leerTexto("Ingrese matrícula completa (ej: P-123): ");

        Medico m = buscarMedicoPorMatricula(matricula);

        if (m == null) {
            System.out.println("Médico no encontrado.");
            return;
        }

        int esp = m.getEspecialidad();

        System.out.println("Atendiendo especialidad: " + m.getEspecialidad());

        atender(esp);
    }

    private void eliminarMedico() {

        System.out.println("\n--- ELIMINAR MÉDICO ---");

        System.out.println("Tipo matrícula: 1.Provincial 2.Nacional");
        int tipo = leerEnteroRango("Opción: ", 1, 2);

        String numero = leerTexto("Número matrícula: ");

        // Creamos un médico auxiliar solo para comparar por matrícula
        Medico m = new Medico("", "", 1, "", tipo, numero);

        if (!medicos.Pertenece(m)) {
            System.out.println("No existe un médico con esa matrícula.");
            return;
        }

        medicos.Sacar(m);

        System.out.println("Médico eliminado correctamente.");
    }


    // =========================
    // PACIENTES
    // =========================
    private void menuPacientes() {
        int op;
        do {
            System.out.println("\n--- PACIENTES ---");
            System.out.println("1. Agregar paciente");
            System.out.println("2. Eliminar paciente");
            System.out.println("3. Buscar paciente");
            System.out.println("4. Listar pacientes");
            System.out.println("0. Volver");

            op = leerEnteroRango("Opción: ", 0, 4);

            switch (op) {
                case 1:
                    agregarPaciente();
                    break;
                case 2:
                    eliminarPaciente();
                    break;
                case 3:
                    buscarPaciente();
                    break;
                case 4:
                    listarPacientes();
                    break;
            }

        } while (op != 0);
    }

    private void agregarPaciente() {

        System.out.println("\n--- AGREGAR PACIENTE ---");

        int dni = leerEntero("DNI: ");

        // Validación importante
        if (existePaciente(dni)) {
            System.out.println("Ya existe un paciente con ese DNI.");
            return;
        }

        String nombre = leerTexto("Nombre: ");
        int edad = leerEnteroRango("Edad: ", 0, 120);

        Paciente p = new Paciente(dni, nombre, edad);
        pacientes.Agregar(dni, p);

        System.out.println("Paciente agregado correctamente.");
    }

    private void eliminarPaciente() {

        System.out.println("\n--- ELIMINAR PACIENTE ---");

        int dni = leerEntero("DNI: ");

        if (existePaciente(dni)) {
            System.out.println("No existe un paciente con ese DNI.");
            return;
        }

        pacientes.Eliminar(dni);

        System.out.println("Paciente eliminado correctamente.");
    }

    private void buscarPaciente() {

        System.out.println("\n--- BUSCAR PACIENTE ---");

        int dni = leerEntero("DNI: ");

        if (!existePaciente(dni)) {
            System.out.println("Paciente no encontrado.");
            return;
        }

        Paciente p = pacientes.Recuperar(dni);
        System.out.println(p);
    }

    private void listarPacientes() {

        System.out.println("\n--- LISTA DE PACIENTES ---");

        ConjuntoTDA claves = pacientes.Claves();

        if (claves.ConjuntoVacio()) {
            System.out.println("No hay pacientes cargados.");
            return;
        }

        while (!claves.ConjuntoVacio()) {
            int dni = claves.Elegir();
            claves.Sacar(dni);

            Paciente p = pacientes.Recuperar(dni);
            System.out.println(p);
        }
    }

    private boolean existePaciente(int dni) {
        ConjuntoTDA claves = pacientes.Claves();

        while (!claves.ConjuntoVacio()) {
            int x = claves.Elegir();
            claves.Sacar(x);

            if (x == dni) {
                return true;
            }
        }
        return false;
    }

    // =========================
    // TURNOS
    // =========================
    private void menuTurnos() {
        int op;
        do {
            System.out.println("\n--- TURNOS ---");
            System.out.println("1. Agregar turno");
            System.out.println("2. Eliminar turno");
            System.out.println("3. Ver próximo turno");
            System.out.println("0. Volver");

            op = leerEnteroRango("Opción: ", 0, 3);

            switch (op) {
                case 1:
                    agregarTurno();
                    break;
                case 2:
                    eliminarTurno();
                    break;
                case 3:
                    verProximoTurno();
                    break;
            }

        } while (op != 0);
    }

    private void agregarTurno() {

        System.out.println("\n--- AGREGAR TURNO ---");

        int dni = leerEntero("DNI paciente: ");
        if (!existePaciente(dni)) {
            System.out.println("Paciente inexistente.");
            return;
        }
        Paciente p = pacientes.Recuperar(dni);

        int especialidad = leerEnteroRango("Especialidad (1.Cardio 2.Pedia 3.Trauma): ", 1, 3);

        String fecha = leerTexto("Fecha: ");

        int prioridad = leerEnteroRango("Prioridad (1 alta - 3 baja): ", 1, 3);

        Turno t = new Turno(p, especialidad, fecha);

        ColaPrioridadTDA cola = obtenerCola(especialidad);
        cola.AcolarPrioridad(t, prioridad);

        System.out.println("Turno agregado correctamente.");
    }

    private void eliminarTurno() {

        System.out.println("\n--- CANCELAR TURNO ---");

        int especialidad = leerEnteroRango("Especialidad (1.Cardio 2.Pedia 3.Trauma): ", 1, 3);
        int dni = leerEntero("DNI del paciente a cancelar: ");

        ColaPrioridadTDA cola = obtenerCola(especialidad);

        if (cola.ColaVacia()) {
            System.out.println("No hay turnos en esa especialidad.");
            return;
        }

        ColaPrioridadTDA aux = new ColaPrioridadTurnos();
        aux.InicializarCola();

        boolean eliminado = false;

        while (!cola.ColaVacia()) {

            Turno t = cola.Primero();
            int prioridad = cola.Prioridad();
            cola.Desacolar();

            if (!eliminado && t.getPaciente().getDni() == dni) {
                eliminado = true; // lo “descartamos”
            } else {
                aux.AcolarPrioridad(t, prioridad);
            }
        }

        // Restaurar cola original
        while (!aux.ColaVacia()) {
            Turno t = aux.Primero();
            int prioridad = aux.Prioridad();
            aux.Desacolar();

            cola.AcolarPrioridad(t, prioridad);
        }

        if (eliminado) {
            System.out.println("Turno cancelado correctamente.");
        } else {
            System.out.println("No se encontró un turno con ese DNI.");
        }
    }

    private void verProximoTurno() {

        System.out.println("\n--- PRÓXIMO TURNO ---");

        int especialidad = leerEnteroRango("Especialidad (1.Cardio 2.Pedia 3.Trauma): ", 1, 3);

        ColaPrioridadTDA cola = obtenerCola(especialidad);

        if (cola.ColaVacia()) {
            System.out.println("No hay turnos en esa especialidad.");
            return;
        }

        Turno t = cola.Primero();

        System.out.println("Próximo turno:");
        System.out.println(t);
    }

    // =========================
    // ATENDER
    // =========================
    private void atender(int esp) {

        ColaPrioridadTDA cola = obtenerCola(esp);

        if (cola.ColaVacia()) {
            System.out.println("No hay pacientes en espera.");
            return;
        };

        Turno t = cola.Primero();
        cola.Desacolar();

        t.marcarAtendido();

        if (esp == 1) atendidosCardiologia++;
        if (esp == 2) atendidosPediatria++;
        if (esp == 3) atendidosTraumatologia++;

        System.out.println("Atendido: " + t);
    }

    private ColaPrioridadTDA obtenerCola(int esp) {
        if (esp == 1) return colaCardiologia;
        if (esp == 2) return colaPediatria;
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
            input = scanner.nextLine().trim();
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