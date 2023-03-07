package org.a21martinqr.view;

import com.mongodb.client.MongoCollection;
import org.a21martinqr.DAO.DAOJugador;
import org.a21martinqr.client.MongoDBCollection;
import org.a21martinqr.model.Equipo;
import org.a21martinqr.model.Jugador;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;

public class JugadorView {

    private final DAOJugador jugadorDAO;
    private final Scanner scanner;

    public JugadorView() {
        this.jugadorDAO = new DAOJugador(MongoDBCollection.getConexion());
        scanner = new Scanner(System.in);
    }

    public JugadorView(DAOJugador jugadorDAO) {
        this.jugadorDAO = jugadorDAO;
        scanner = new Scanner(System.in);
    }

    public void mostrarMenu() {
        System.out.println("Bienvenido al sistema de jugadores:");
        boolean salir = false;
        while (!salir) {
            System.out.println("Seleccione una opción:");
            System.out.println("1. Ver todos los jugadores");
            System.out.println("2. Agregar un nuevo jugador");
            System.out.println("3. Actualizar un jugador existente");
            System.out.println("4. Eliminar un jugador existente");
            System.out.println("5. Mostrar Jugadores de una posición");
            System.out.println("6. Mostrar jugador por nombre");
            System.out.println("7. Mostrar jugadores por equipo");
            System.out.println("8. Mostrar jugadores por edad");
            System.out.println("9. Mostrar jugadores menores de edad");
            System.out.println("10. Mostrar jugadores por equipo y posición");
            System.out.println("11. Agrupar jugadores por posición");
            System.out.println("12. Salir");
            String opcion = scanner.nextLine();
            switch (opcion) {
                case "1":
                    mostrarTodosLosJugadores();
                    break;
                case "2":
                    agregarNuevoJugador();
                    break;
                case "3":
                    actualizarJugador();
                    break;
                case "4":
                    eliminarJugador();
                    break;
                case "5":
                    mostrarJugadoresPorPosicion();
                    break;
                case "6":
                    mostrarJugadorPorNombre();
                    break;
                case "7":
                    mostrarJugadoresPorEquipo();
                    break;
                case "8":
                    mostrarJugadoresPorEdad();
                    break;
                case "9":
                    mostrarJugadoresMenoresEdad();
                    break;
                case "10":
                    mostrarJugadoresPorEquipoYPosicion();
                    break;
                case "11":
                    agrupaJugadoresPorPosicion();
                    break;
                case "12":
                    salir = true;
                    break;
                default:
                    System.out.println("Opción no válida, intente de nuevo.");
                    break;
            }
        }
    }

    private void mostrarTodosLosJugadores() {
        List<Jugador> jugadores = jugadorDAO.obtenerTodosLosJugadores();
        System.out.println("Jugadores registrados:");
        for (Jugador jugador : jugadores) {
            System.out.println(jugador);
        }
    }

    private void agregarNuevoJugador() {
        ObjectId id = new ObjectId();

        System.out.println("Ingrese el nombre del nuevo jugador:");
        String nombre = scanner.next();
        System.out.println("Ingrese su edad");
        int edad = scanner.nextInt();

        System.out.println("Ingrese la posición del nuevo jugador:");
        String posicion = scanner.next();

        System.out.println("Ingrese el equipo del nuevo jugador:");
        String equipo = scanner.next();
        Jugador jugador = new Jugador(id, nombre, edad, posicion, equipo);
        jugadorDAO.agregarJugador(jugador);
        System.out.println("Jugador agregado correctamente: " + jugador);
    }

    private void actualizarJugador() {
        System.out.println("Ingrese el id del jugador a actualizar:");
        ObjectId id = new ObjectId();
        id = new ObjectId(scanner.nextLine());
        Jugador jugadorExistente = jugadorDAO.obtenerJugadorPorId(id);
        if (jugadorExistente == null) {
            System.out.println("No se encontró ningún jugador con el id especificado.");
            return;
        }
        System.out.println("Ingrese el nuevo nombre del jugador:");
        String nombre = scanner.nextLine();
        System.out.println("Ingrese su edad");
        int edad = scanner.nextInt();
        System.out.println("Ingrese la nueva posición del jugador:");
        String posicion = scanner.nextLine();
        System.out.println("Ingrese el nuevo equipo del jugador:");
        String equipo = scanner.nextLine();
        Jugador jugadorActualizado = new Jugador(id, nombre, edad, posicion, equipo);
        jugadorActualizado.setId(jugadorExistente.getId());
        jugadorDAO.actualizarJugador(jugadorActualizado);
        System.out.println("Jugador actualizado correctamente: " + jugadorActualizado);
    }

    private void eliminarJugador() {
        System.out.println("Ingrese el id del jugador a eliminar:");
        ObjectId id = new ObjectId();
        id = new ObjectId(scanner.nextLine());
        Jugador jugadorExistente = jugadorDAO.obtenerJugadorPorId(id);
        if (jugadorExistente == null) {
            System.out.println("No se encontró ningún jugador con el id especificado.");
            return;
        }
        jugadorDAO.eliminarJugador(id);
        System.out.println("Jugador eliminado correctamente: " + jugadorExistente);
    }

    private void mostrarJugadorPorNombre() {
        System.out.println("Ingrese el nombre del jugador a buscar:");
        String nombre = scanner.nextLine();
        Jugador jugadorExistente = jugadorDAO.obtenerJugadorPorNombre(nombre);
        if (jugadorExistente == null) {
            System.out.println("No se encontró ningún jugador con el nombre especificado.");
            return;
        }
        System.out.println("Jugador encontrado: " + jugadorExistente);
    }

    private List<Jugador> mostrarJugadoresPorPosicion() {
        List<Jugador> jugadores = new ArrayList<>();
        System.out.println("Ingrese la posición del jugador a buscar:");
        String posicion = scanner.nextLine();
        jugadores = jugadorDAO.obtenerJugadoresPorPosicion(posicion);

        if (jugadores == null) {
            System.out.println("No se encontró ningún jugador con la posición especificada.");
            return jugadores;
        }
        System.out.println("Jugadores encontrados: " + jugadores);
        return jugadores;
    }

    private List<Jugador> mostrarJugadoresPorEdad() {
        List<Jugador> jugadores = new ArrayList<>();
        System.out.println("Ingrese la edad del jugador a buscar:");
        int edad = scanner.nextInt();
        jugadores = jugadorDAO.obtenerJugadoresPorEdad(edad);
        if (jugadores == null) {
            System.out.println("No se encontró ningún jugador con la edad especificada.");
            return jugadores;
        }
        System.out.println("Jugadores encontrados: " + jugadores);
        return jugadores;
    }

    private List<Jugador> mostrarJugadoresPorEquipo() {
        List<Jugador> jugadores = new ArrayList<>();
        System.out.println("Ingrese el equipo del jugador a buscar:");
        String equipo = scanner.nextLine();
        jugadores = jugadorDAO.obtenerJugadoresPorEquipo(equipo);
        if (jugadores == null) {
            System.out.println("No se encontró ningún jugador con el equipo especificado.");
            return jugadores;
        }
        System.out.println("Jugadores encontrados: " + jugadores);
        return jugadores;
    }

    private List<Jugador> mostrarJugadoresMayorEdad() {
        List<Jugador> jugadores = new ArrayList<>();
        jugadores = jugadorDAO.obtenerJugadoresMayoresDeEdad();
        if (jugadores == null) {
            System.out.println("No se encontró ningún jugador con la edad especificada.");
            return jugadores;
        }
        System.out.println("Jugadores encontrados: " + jugadores);
        return jugadores;
    }

    private List<Jugador> mostrarJugadoresMenoresEdad() {
        List<Jugador> jugadores = new ArrayList<>();
        jugadores = jugadorDAO.obtenerJugadoresMenoresDeEdad();
        if (jugadores == null) {
            System.out.println("No se encontró ningún jugador con la edad especificada.");
            return jugadores;
        }
        System.out.println("Jugadores encontrados: " + jugadores);
        return jugadores;
    }

    //Consulta de agrupación
    private void mostrarJugadoresPorEquipoYPosicion() {
        List<Jugador> jugadores = new ArrayList<>();
        System.out.println("Ingrese el equipo del jugador a buscar:");
        String equipo = scanner.nextLine();
        System.out.println("Ingrese la posición del jugador a buscar:");
        String posicion = scanner.nextLine();
        jugadores = jugadorDAO.obtenerJugadoresPorEquipoYPosicion(equipo, posicion);
        if (jugadores == null) {
            System.out.println("No se encontró ningún jugador con la edad especificada.");
            return;
        }
        System.out.println("Jugadores encontrados: " + jugadores);
    }

    private List<Jugador> agrupaJugadoresPorPosicion() {
        List<Jugador> jugadores = new ArrayList<>();
        System.out.println("Ingrese la posición del jugador a buscar:");
        String posicion = scanner.nextLine();
        jugadores = jugadorDAO.agruparJugadoresPorPosicion(posicion);
        if (jugadores == null) {
            System.out.println("No se encontró ningún jugador con la edad especificada.");
            return jugadores;
        } else {
            System.out.println("Jugadores encontrados: ");
            for (Jugador jugador : jugadores) {
                System.out.println(jugador);
            }
        }
        return jugadores;
    }

}