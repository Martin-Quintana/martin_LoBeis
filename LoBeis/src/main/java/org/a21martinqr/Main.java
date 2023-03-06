package org.a21martinqr;

import org.a21martinqr.DAO.DAOEstadio;
import org.a21martinqr.client.MongoDBCollection;
import org.a21martinqr.model.Estadio;
import org.a21martinqr.view.EstadioView;
import org.a21martinqr.view.JugadorView;

public class Main {
    public static void main(String[] args) {

        MongoDBCollection conexion = new MongoDBCollection("localhost", 27017, "LoBeis");
        conexion.getConexion();

        JugadorView jugadorView = new JugadorView();
        EstadioView estadioView = new EstadioView();

        jugadorView.mostrarMenu();
        estadioView.mostrarMenu();

        conexion.cerrarConexion();
    }
}