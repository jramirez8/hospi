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

            op = leerEntero("Opción: ");

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
        int esp = leerEntero("Opción: ");

        String horario = leerTexto("Horario: ");

        System.out.println("Tipo matrícula: 1.P 2.N");
        int tipo = leerEntero("Opción: ");

        String numero = leerTexto("Número matrícula: ");

        Medico m = new Medico(nombre, apellido, esp, horario, tipo, numero);

        medicos.Agregar(m);
    }

    // =========================
    // PACIENTES
    // =========================
    private void menuPacientes() {
        int dni = leerEntero("DNI: ");

        String nombre = leerTexto("Nombre: ");
        int edad = leerEntero("Edad: ");

        Paciente p = new Paciente(dni, nombre, edad);

        pacientes.Agregar(dni, p);
    }

    // =========================
    // TURNOS
    // =========================
    private void menuTurnos() {

        int dni = leerEntero("DNI paciente: ");
        Paciente p = pacientes.Recuperar(dni);

        if (p == null) return;

        Medico m = medicos.Elegir(); // simplificación (podés mejorar luego)

        String fecha = leerTexto("Fecha: ");
        int prioridad = leerEntero("Prioridad (1 alta - 3 baja): ");

        Turno t = new Turno(p, m, fecha, prioridad);

        if (m.getEspecialidad().equals("CARDIOLOGIA")) {
            colaCardiologia.AcolarPrioridad(t, prioridad);
        }

        if (m.getEspecialidad().equals("PEDIATRIA")) {
            colaPediatria.AcolarPrioridad(t, prioridad);
        }

        if (m.getEspecialidad().equals("TRAUMATOLOGIA")) {
            colaTraumatologia.AcolarPrioridad(t, prioridad);
        }
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
        System.out.print(msg);
        return scanner.nextLine();
    }

    private int leerEntero(String msg) {
        System.out.print(msg);
        return Integer.parseInt(scanner.nextLine());
    }
}
