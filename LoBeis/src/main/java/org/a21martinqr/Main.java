package org.a21martinqr;

import org.a21martinqr.client.MongoDBCollection;
import org.a21martinqr.view.JugadorView;

public class Main {
    public static void main(String[] args) {

        MongoDBCollection conexion = new MongoDBCollection("localhost", 27017, "LoBeis");
        conexion.getConexion();

        JugadorView jugadorView = new JugadorView();

        jugadorView.mostrarMenu();

        conexion.cerrarConexion();
    }
}