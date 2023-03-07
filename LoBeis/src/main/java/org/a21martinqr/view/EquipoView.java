package org.a21martinqr.view;

import org.a21martinqr.DAO.DAOEquipo;
import org.a21martinqr.DAO.DAOEstadio;
import org.a21martinqr.client.MongoDBCollection;
import org.a21martinqr.model.Equipo;
import org.a21martinqr.model.Estadio;
import org.bson.types.ObjectId;

import java.util.List;
import java.util.Scanner;

public class EquipoView {

    private DAOEquipo daoEquipo;

    private DAOEstadio daoEstadio = new DAOEstadio(MongoDBCollection.getConexion());

    private final Scanner scanner;


    public EquipoView() {
        this.daoEquipo = new DAOEquipo(MongoDBCollection.getConexion());
        scanner = new Scanner(System.in);
    }


    public void mostrarMenu() {
        System.out.println("Bienvenido al sistema de equipos:");
        boolean salir = false;
        while (!salir) {
            System.out.println("Seleccione una opción:");
            System.out.println("1. Ver todos los equipos");
            System.out.println("2. Agregar un nuevo equipo");
            System.out.println("3. Actualizar un equipo existente");
            System.out.println("4. Eliminar un equipo existente");
            System.out.println("5. Buscar equipo por nombre");
            System.out.println("6. Salir");
            String opcion = scanner.nextLine();
            switch (opcion) {
                case "1":
                    mostrarTodosLosEquipos();
                    break;
                case "2":
                    agregarNuevoEquipo();
                    break;
                case "3":
                    actualizarEquipo();
                    break;
                case "4":
                    eliminarEquipo();
                    break;
                case "5":
                    mostrarEquipoPorNombre();
                    break;

                case "6":
                    salir = true;
                    break;
                default:
                    System.out.println("Opción no válida, intente de nuevo.");
                    break;
            }
        }
    }

    private void mostrarTodosLosEquipos() {
        System.out.println("Mostrando todos los equipos:");
        List<Equipo> equipos = daoEquipo.obtenerTodosLosEquipos();
        for (Equipo equipo : equipos) {
            System.out.println(equipo);
        }
    }

    private void agregarNuevoEquipo() {
        ObjectId id = new ObjectId();
        System.out.println("Ingrese el nombre del equipo:");
        String nombre = scanner.nextLine();
        System.out.println("Ingrese el nombre de la ciudad del equipo:");
        String ciudad = scanner.nextLine();
        System.out.println("Ingrese el nombre del país del equipo:");
        String pais = scanner.nextLine();
        System.out.println("Ingrese la fundación del equipo:");
        int fundacion = scanner.nextInt();
        System.out.println("Ingrese el nombre del estadio del equipo:");
        String nombreEstadio = scanner.nextLine();

        Equipo equipo = new Equipo(id, nombre, ciudad, pais, fundacion, nombreEstadio);
        daoEquipo.agregarEquipo(equipo);
        System.out.println("Equipo agregado con éxito.");

    }

    private void actualizarEquipo() {
        System.out.println("Ingrese el id del equipo a actualizar:");
        ObjectId id = new ObjectId(scanner.nextLine());
        System.out.println("Ingrese el nombre del equipo:");
        String nombre = scanner.nextLine();
        System.out.println("Ingrese el nombre de la ciudad del equipo:");
        String ciudad = scanner.nextLine();
        System.out.println("Ingrese el nombre del país del equipo:");
        String pais = scanner.nextLine();
        System.out.println("Ingrese la fundación del equipo:");
        int fundacion = scanner.nextInt();
        System.out.println("Ingrese el nombre del estadio del equipo:");
        String estadio = scanner.nextLine();


        Equipo equipoActualizado = new Equipo(id, nombre, ciudad, pais, fundacion, estadio);
        daoEquipo.actualizarEquipo(equipoActualizado);
        System.out.println("Equipo actualizado con éxito.");
    }

    private void eliminarEquipo() {
        System.out.println("Ingrese el id del equipo a eliminar:");
        ObjectId id = new ObjectId(scanner.nextLine());
        daoEquipo.eliminarEquipo(id);
        System.out.println("Equipo eliminado con éxito.");
    }

    private void mostrarEquipoPorNombre() {
        System.out.println("Ingrese el nombre del equipo a buscar:");
        String nombre = scanner.nextLine();
        daoEquipo.obtenerEquiposPorNombre(nombre);
        System.out.println("Equipo encontrado con éxito.");
    }


}
