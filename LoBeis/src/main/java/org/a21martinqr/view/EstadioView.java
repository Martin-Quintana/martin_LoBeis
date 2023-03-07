package org.a21martinqr.view;

import org.a21martinqr.DAO.DAOEstadio;
import org.a21martinqr.client.MongoDBCollection;
import org.a21martinqr.model.Estadio;
import org.bson.types.ObjectId;

import java.util.List;
import java.util.Scanner;

public class EstadioView {

    private final DAOEstadio daoEstadio;

    private final Scanner scanner;

    public EstadioView() {
        this.daoEstadio = new DAOEstadio(MongoDBCollection.getConexion());
        scanner = new Scanner(System.in);


    }

    public EstadioView(DAOEstadio daoEstadio) {
        this.daoEstadio = daoEstadio;
        scanner = new Scanner(System.in);
    }

    public void mostrarMenu() {
        System.out.println("Bienvenido al sistema de estadios:");
        boolean salir = false;
        while (!salir) {
            System.out.println("Seleccione una opción:");
            System.out.println("1. Ver todos los estadios");
            System.out.println("2. Agregar un nuevo estadio");
            System.out.println("3. Actualizar un estadio existente");
            System.out.println("4. Eliminar un estadio existente");
            System.out.println("5. Agrupar estadios por ciudad");
            System.out.println("6. Salir");
            String opcion = scanner.nextLine();
            switch (opcion) {
                case "1":
                    mostrarTodosLosEstadios();
                    break;
                case "2":
                    agregarNuevoEstadio();
                    break;
                case "3":
                    actualizarEstadio();
                    break;
                case "4":
                    eliminarEstadio();
                    break;
                case "5":
                    mostrarEstadioPorCiudad();
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

    void mostrarTodosLosEstadios() {
        List<Estadio> estadios = daoEstadio.obtenerTodosLosEstadios();
        System.out.println("Mostrando todos los estadios:");
        for (Estadio estadio : estadios) {
            System.out.println(estadio);
        }
    }

    private void agregarNuevoEstadio() {
        ObjectId id = new ObjectId();

        System.out.println("Ingrese el nombre del estadio:");
        String nombre = scanner.nextLine();
        System.out.println("Ingrese la ciudad del estadio:");
        String ciudad = scanner.next();
        System.out.println("Ingrese la capacidad del estadio:");
        int capacidad = scanner.nextInt();
        System.out.println("Ingrese el pais del estadio:");
        String pais = scanner.nextLine();


        Estadio estadio = new Estadio(id, nombre, ciudad, pais, capacidad);
        daoEstadio.agregarEstadio(estadio);
        System.out.println("Estadio agregado con éxito.");
    }

    private void actualizarEstadio() {


        System.out.println("Ingrese el id del estadio a actualizar:");
        ObjectId id = new ObjectId(scanner.nextLine());
        System.out.println("Ingrese el nombre del estadio:");
        String nombre = scanner.nextLine();
        System.out.println("Ingrese la ciudad del estadio:");
        String ciudad = scanner.nextLine();
        System.out.println("Ingrese el pais del estadio:");
        String pais = scanner.nextLine();
        System.out.println("Ingrese la capacidad del estadio:");
        int capacidad = scanner.nextInt();

        scanner.nextLine();
        Estadio estadioactualizado = new Estadio(id, nombre, ciudad, pais, capacidad);
        daoEstadio.actualizarEstadio(estadioactualizado);
    }

    private void eliminarEstadio() {
        System.out.println("Ingrese el id del estadio a eliminar:");
        ObjectId id = new ObjectId(scanner.nextLine());
        Estadio estadioExistente = daoEstadio.obtenerEstadioPorId(id);
        if (estadioExistente != null) {
            daoEstadio.eliminarEstadio(estadioExistente);
            System.out.println("Estadio eliminado con éxito.");
        } else {
            System.out.println("No se encontró el estadio con el id especificado.");
        }
    }


    //todo list estadios por ciudad
    private void mostrarEstadioPorCiudad() {
        System.out.println("Ingrese la ciudad del estadio:");
        String ciudad = scanner.nextLine();
        Estadio estadioExistente = daoEstadio.agruparEstadioPorCiudad(ciudad);
        if (estadioExistente != null) {
            daoEstadio.agruparEstadioPorCiudad(String.valueOf(estadioExistente));
            System.out.println("Estadio encontrado con éxito.");
            System.out.println(estadioExistente);
        } else {
            System.out.println("No se encontró el estadio con la ciudad especificada.");
        }
    }





}
