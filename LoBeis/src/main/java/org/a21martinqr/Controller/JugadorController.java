package org.a21martinqr.Controller;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import org.a21martinqr.client.MongoDBCollection;
import org.a21martinqr.model.Jugador;

import java.awt.*;
import java.util.ArrayList;

public class JugadorController {

    private final MongoCollection<Jugador> jugadoresCollection;

    public JugadorController() {
        jugadoresCollection = MongoDBCollection.getConexion().getCollection("jugadores", Jugador.class);
    }

    public void insertarJugador(Jugador jugador) {
        jugadoresCollection.insertOne(jugador);
    }

    public void actualizarJugador(Jugador jugador) {
        jugadoresCollection.updateOne(Filters.eq("_id", jugador.getId()), Updates.combine(
                Updates.set("nombre", jugador.getNombre()),
                Updates.set("edad", jugador.getEdad()),
                Updates.set("posicion", jugador.getPosicion()),
                Updates.set("equipo", jugador.getEquipo())
        ));
    }

    public void eliminarJugador(Jugador jugador) {
        jugadoresCollection.deleteOne(Filters.eq("_id", jugador.getId()));
    }

    public ArrayList<Jugador> obtenerJugadores() {
        ArrayList<Jugador> jugadores = new ArrayList<>();
        jugadoresCollection.find().into(jugadores);
        return jugadores;
    }

    public Jugador obtenerJugadorPorId(String id) {
        return jugadoresCollection.find(Filters.eq("_id", id)).first();
    }

    public Jugador obtenerJugadorPorNombre(String nombre) {
        return jugadoresCollection.find(Filters.eq("nombre", nombre)).first();
    }

    public Jugador obtenerJugadorPorEdad(int edad) {
        return jugadoresCollection.find(Filters.eq("edad", edad)).first();
    }

    public Jugador obtenerJugadorPorPosicion(String posicion) {
        return jugadoresCollection.find(Filters.eq("posicion", posicion)).first();
    }

    public Jugador obtenerJugadorPorEquipo(String equipo) {
        return jugadoresCollection.find(Filters.eq("equipo", equipo)).first();
    }

    
}
