package org.a21martinqr;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        MongoDBCollection mongoDB = new MongoDBCollection();
        Scanner scan = new Scanner(System.in);

        int opcion = 0;
        boolean salir = false;
        while (!salir) {
            System.out.println("MENU PRINCIPAL");
            System.out.println("1. Insertar datos");
            System.out.println("2. Borrar datos");
            System.out.println("3. Consultar datos");
            System.out.println("4. Actualizar datos");
            System.out.println("5 Agrupar datos");
            System.out.println("0. Salir");
            System.out.println("Elige una opcion: ");
            opcion = scan.nextInt();

            switch (opcion) {
                case 1:
                    mongoDB.insertarDatos();
                    break;
                case 2:
                    mongoDB.borradoDatos();
                    break;
                case 3:
                    mongoDB.menuConsultas();
                    break;
                case 4:
                    mongoDB.menuUpdates();
                    break;
                case 5:
                    mongoDB.menuAgrupaciones();
                    break;
                case 0:
                    salir = true;
                    break;
                default:
                    System.out.println("Opcion incorrecta");
            }
        }

    }
}