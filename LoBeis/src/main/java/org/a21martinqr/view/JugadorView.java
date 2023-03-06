package org.a21martinqr.view;

import com.mongodb.client.MongoCollection;
import org.a21martinqr.DAO.DAOJugador;
import org.a21martinqr.client.MongoDBCollection;
import org.a21martinqr.model.Jugador;
import org.bson.types.ObjectId;

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
            System.out.println("5. Salir");
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
        String nombre = scanner.nextLine();
        System.out.println("Ingrese su edad");
        int edad = Integer.parseInt(scanner.nextLine());
        System.out.println("Ingrese la posición del nuevo jugador:");
        String posicion = scanner.nextLine();
        System.out.println("Ingrese el equipo del nuevo jugador:");
        String equipo = scanner.nextLine();
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
        int edad = Integer.parseInt(scanner.nextLine());
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

}