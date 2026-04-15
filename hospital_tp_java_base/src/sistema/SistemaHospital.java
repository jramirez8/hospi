package sistema;

import estructuras.ConjuntoMedicos;
import estructuras.DiccionarioPacientes;
import estructuras.GestorColasPorEspecialidad;
import estructuras.RegistroAtenciones;
import modelos.Medico;
import modelos.Paciente;
import modelos.Turno;

import java.util.Scanner;

public class SistemaHospital {
    private Scanner scanner;
    private ConjuntoMedicos medicos;
    private DiccionarioPacientes pacientes;
    private GestorColasPorEspecialidad colasEspecialidad;
    private RegistroAtenciones registroAtenciones;

    public SistemaHospital() {
        scanner = new Scanner(System.in);
        medicos = new ConjuntoMedicos(100);
        pacientes = new DiccionarioPacientes();
        colasEspecialidad = new GestorColasPorEspecialidad(50);
        registroAtenciones = new RegistroAtenciones();
    }

    public void iniciar() {
        int opcion;
        do {
            mostrarMenuPrincipal();
            opcion = leerEntero("Elegí una opción: ");

            switch (opcion) {
                case 1:
                    menuMedicos();
                    break;
                case 2:
                    menuPacientes();
                    break;
                case 3:
                    menuTurnos();
                    break;
                case 4:
                    reporteDiario();
                    break;
                case 0:
                    System.out.println("Saliendo del sistema...");
                    break;
                default:
                    System.out.println("Opción inválida.");
                    break;
            }
        } while (opcion != 0);
    }

    private void mostrarMenuPrincipal() {
        System.out.println("\n===== SISTEMA HOSPITAL =====");
        System.out.println("1. Gestión de médicos");
        System.out.println("2. Gestión de pacientes");
        System.out.println("3. Gestión de turnos");
        System.out.println("4. Reporte diario");
        System.out.println("0. Salir");
    }

    private void menuMedicos() {
        int opcion;
        do {
            System.out.println("\n--- GESTIÓN DE MÉDICOS ---");
            System.out.println("1. Agregar médico");
            System.out.println("2. Buscar médico por matrícula");
            System.out.println("3. Ver turnos próximos por matrícula");
            System.out.println("4. Ver turnos atendidos por matrícula");
            System.out.println("5. Listar médicos");
            System.out.println("0. Volver");

            opcion = leerEntero("Elegí una opción: ");

            switch (opcion) {
                case 1:
                    agregarMedico();
                    break;
                case 2:
                    buscarMedico();
                    break;
                case 3:
                    verTurnosProximosPorMedico();
                    break;
                case 4:
                    verTurnosAtendidosPorMedico();
                    break;
                case 5:
                    medicos.listar();
                    break;
                case 0:
                    System.out.println("Volviendo...");
                    break;
                default:
                    System.out.println("Opción inválida.");
                    break;
            }
        } while (opcion != 0);
    }

    private void menuPacientes() {
        int opcion;
        do {
            System.out.println("\n--- GESTIÓN DE PACIENTES ---");
            System.out.println("1. Agregar paciente");
            System.out.println("2. Buscar paciente por DNI");
            System.out.println("3. Listar pacientes");
            System.out.println("0. Volver");

            opcion = leerEntero("Elegí una opción: ");

            switch (opcion) {
                case 1:
                    agregarPaciente();
                    break;
                case 2:
                    buscarPaciente();
                    break;
                case 3:
                    pacientes.listar();
                    break;
                case 0:
                    System.out.println("Volviendo...");
                    break;
                default:
                    System.out.println("Opción inválida.");
                    break;
            }
        } while (opcion != 0);
    }

    private void menuTurnos() {
        int opcion;
        do {
            System.out.println("\n--- GESTIÓN DE TURNOS ---");
            System.out.println("1. Asignar turno");
            System.out.println("2. Atender siguiente paciente por especialidad");
            System.out.println("3. Ver cola de una especialidad");
            System.out.println("4. Ver resumen de colas");
            System.out.println("0. Volver");

            opcion = leerEntero("Elegí una opción: ");

            switch (opcion) {
                case 1:
                    asignarTurno();
                    break;
                case 2:
                    atenderSiguientePaciente();
                    break;
                case 3:
                    verColaEspecialidad();
                    break;
                case 4:
                    colasEspecialidad.listarEspecialidadesYConteoPendientes();
                    break;
                case 0:
                    System.out.println("Volviendo...");
                    break;
                default:
                    System.out.println("Opción inválida.");
                    break;
            }
        } while (opcion != 0);
    }

    private void agregarMedico() {
        System.out.println("\nAlta de médico");
        String nombre = leerTextoNoVacio("Nombre: ");
        String apellido = leerTextoNoVacio("Apellido: ");
        String especialidad = leerTextoNoVacio("Especialidad: ");
        String horario = leerTextoNoVacio("Horario disponible: ");
        String tipoMatricula = leerTipoMatricula();
        String numeroMatricula = leerSoloNumeros("Número de matrícula: ");

        Medico medico = new Medico(nombre, apellido, especialidad, horario, tipoMatricula, numeroMatricula);
        boolean agregado = medicos.agregar(medico);

        if (agregado) {
            System.out.println("Médico agregado correctamente.");
        } else {
            System.out.println("No se pudo agregar. Ya existe o se alcanzó la capacidad máxima.");
        }
    }

    private void buscarMedico() {
        Medico medico = leerMedicoPorMatricula();
        if (medico == null) {
            System.out.println("Médico no encontrado.");
        } else {
            System.out.println(medico);
        }
    }

    private void agregarPaciente() {
        System.out.println("\nAlta de paciente");
        String dni = leerSoloNumeros("DNI: ");

        if (pacientes.contiene(dni)) {
            System.out.println("Ya existe un paciente con ese DNI.");
            return;
        }

        String nombre = leerTextoNoVacio("Nombre: ");
        int edad = leerEntero("Edad: ");
        String historial = leerTextoNoVacio("Historial médico: ");

        Paciente paciente = new Paciente(dni, nombre, edad, historial);
        pacientes.put(dni, paciente);
        System.out.println("Paciente agregado correctamente.");
    }

    private void buscarPaciente() {
        String dni = leerSoloNumeros("DNI del paciente: ");
        Paciente paciente = pacientes.get(dni);

        if (paciente == null) {
            System.out.println("Paciente no encontrado.");
        } else {
            System.out.println(paciente);
        }
    }

    private void asignarTurno() {
        System.out.println("\nAsignación de turno");
        Medico medico = leerMedicoPorMatricula();
        if (medico == null) {
            System.out.println("No existe un médico con esa matrícula.");
            return;
        }

        String dni = leerSoloNumeros("DNI del paciente: ");
        Paciente paciente = pacientes.get(dni);
        if (paciente == null) {
            System.out.println("No existe un paciente con ese DNI.");
            return;
        }

        String fecha = leerTextoNoVacio("Fecha del turno: ");

        int prioridad = leerEntero("Prioridad (1=NORMAL, 2=URGENTE, 3=EMERGENCIA): ");
        while (prioridad < 1 || prioridad > 3) {
            System.out.println("Ingresá un número entre 1 y 3.");
            prioridad = leerEntero("Prioridad (1=NORMAL, 2=URGENTE, 3=EMERGENCIA): ");
        }

        Turno turno = new Turno(paciente, medico, medico.getEspecialidad(), fecha, prioridad);
        colasEspecialidad.encolarTurno(medico.getEspecialidad(), turno);
        System.out.println("Turno asignado correctamente en la cola de " + medico.getEspecialidad());
    }

    private void atenderSiguientePaciente() {
        System.out.println("\nAtender siguiente paciente");
        String especialidad = leerTextoNoVacio("Especialidad: ");
        Turno turno = colasEspecialidad.atenderSiguiente(especialidad);

        if (turno == null) {
            System.out.println("No hay pacientes en espera para esa especialidad.");
            return;
        }

        turno.marcarAtendido();
        registroAtenciones.agregar(turno);
        System.out.println("Se atendió al siguiente paciente:");
        System.out.println(turno);
    }

    private void verColaEspecialidad() {
        String especialidad = leerTextoNoVacio("Especialidad: ");
        colasEspecialidad.listarCola(especialidad);
    }

    private void verTurnosProximosPorMedico() {
        Medico medico = leerMedicoPorMatricula();
        if (medico == null) {
            System.out.println("Médico no encontrado.");
            return;
        }
        colasEspecialidad.listarTurnosProximosPorMedico(medico.getMatriculaCompleta());
    }

    private void verTurnosAtendidosPorMedico() {
        Medico medico = leerMedicoPorMatricula();
        if (medico == null) {
            System.out.println("Médico no encontrado.");
            return;
        }
        registroAtenciones.listarPorMatricula(medico.getMatriculaCompleta());
    }

    private void reporteDiario() {
        System.out.println("\n--- REPORTE DIARIO DE PACIENTES ATENDIDOS POR ESPECIALIDAD ---");

        if (colasEspecialidad.cantidadEspecialidades() == 0) {
            System.out.println("Todavía no hay especialidades registradas en turnos.");
            return;
        }

        for (int i = 0; i < colasEspecialidad.cantidadEspecialidades(); i++) {
            String especialidad = colasEspecialidad.getEspecialidadEn(i);
            int atendidos = registroAtenciones.contarPorEspecialidad(especialidad);
            System.out.println("Especialidad: " + especialidad + " | Atendidos hoy: " + atendidos);
        }
    }

    private Medico leerMedicoPorMatricula() {
        String tipo = leerTipoMatricula();
        String numero = leerSoloNumeros("Número de matrícula: ");
        return medicos.buscarPorMatricula(tipo, numero);
    }

    private String leerTextoNoVacio(String mensaje) {
        String texto;
        do {
            System.out.print(mensaje);
            texto = scanner.nextLine().trim();
            if (texto.isEmpty()) {
                System.out.println("El campo no puede estar vacío.");
            }
        } while (texto.isEmpty());
        return texto;
    }

    private String leerSoloNumeros(String mensaje) {
        String texto;
        do {
            System.out.print(mensaje);
            texto = scanner.nextLine().trim();
            if (!texto.matches("\\d+")) {
                System.out.println("Ingresá solo números.");
            }
        } while (!texto.matches("\\d+"));
        return texto;
    }

    private String leerTipoMatricula() {
        String tipo;
        do {
            System.out.print("Tipo de matrícula (P/N): ");
            tipo = scanner.nextLine().trim().toUpperCase();
            if (!tipo.equals("P") && !tipo.equals("N")) {
                System.out.println("Ingresá P o N.");
            }
        } while (!tipo.equals("P") && !tipo.equals("N"));
        return tipo;
    }

    private int leerEntero(String mensaje) {
        while (true) {
            try {
                System.out.print(mensaje);
                return Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Debés ingresar un número entero.");
            }
        }
    }
}
