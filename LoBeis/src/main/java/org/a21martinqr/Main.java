package org.a21martinqr;

import org.a21martinqr.DAO.DAOEstadio;
import org.a21martinqr.client.MongoDBCollection;
import org.a21martinqr.model.Estadio;
import org.a21martinqr.view.EquipoView;
import org.a21martinqr.view.EstadioView;
import org.a21martinqr.view.JugadorView;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        MongoDBCollection conexion = new MongoDBCollection("localhost", 27017, "LoBeis");
        conexion.getConexion();
        JugadorView jugadorView = new JugadorView();
        EstadioView estadioView = new EstadioView();
        EquipoView equipoView = new EquipoView();


        //Menu
        System.out.println("Bienvenido al sistema de Gestion:");
        boolean salir = false;
        while (!salir) {
            System.out.println("Seleccione una opción:");
            System.out.println("1. Jugadores");
            System.out.println("2. Estadios");
            System.out.println("3. Equipos");
            System.out.println("4. Salir");
            String opcion = scanner.nextLine();
            switch (opcion) {
                case "1":
                    jugadorView.mostrarMenu();
                    break;
                case "2":
                    estadioView.mostrarMenu();
                    break;
                case "3":
                    equipoView.mostrarMenu();
                    break;
                case "4":
                    salir = true;
                    break;
                default:
                    System.out.println("Opción no válida, intente de nuevo.");
                    break;
            }
        }

        conexion.cerrarConexion();
    }
}