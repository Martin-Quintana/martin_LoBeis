package org.a21martinqr.DAO;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.a21martinqr.model.Jugador;
import org.bson.Document;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.List;

import static com.mongodb.client.model.Filters.eq;

public class DAOJugador {

    private MongoCollection<Document> jugadorCollection;


    public DAOJugador(MongoDatabase database) {
        jugadorCollection = database.getCollection("Jugadores");
    }

    public DAOJugador(MongoCollection<Document> jugadorCollection) {
        this.jugadorCollection = jugadorCollection;
    }



    public List<Jugador> obtenerTodosLosJugadores() {
        List<Jugador> jugadores = new ArrayList<>();
        for (Document document : jugadorCollection.find()) {
            Jugador jugador = new Jugador();
            jugador.setId(document.getObjectId("_id"));
            jugador.setNombre(document.getString("nombre"));
            jugador.setPosicion(document.getString("posicion"));
            jugador.setEquipo(document.getString("equipo"));
            jugadores.add(jugador);
        }
        return jugadores;
    }

    public void agregarJugador(Jugador jugador) {
        Document document = new Document("nombre", jugador.getNombre())
                .append("posicion", jugador.getPosicion())
                .append("equipo", jugador.getEquipo());
        jugadorCollection.insertOne(document);
        jugador.setId(document.getObjectId("_id"));
    }

    public void actualizarJugador(Jugador jugador) {
        jugadorCollection.updateOne(eq("_id", jugador.getId()), new Document("$set", new Document("nombre", jugador.getNombre())
                .append("posicion", jugador.getPosicion())
                .append("equipo", jugador.getEquipo())));
    }

    public void eliminarJugador(ObjectId id) {
        jugadorCollection.deleteOne(eq("_id", id));
    }

    public Jugador obtenerJugadorPorId(ObjectId id) {
        Document document = jugadorCollection.find(eq("_id", id)).first();
        Jugador jugador = new Jugador();
        assert document != null;
        jugador.setId(document.getObjectId("_id"));
        jugador.setNombre(document.getString("nombre"));
        jugador.setPosicion(document.getString("posicion"));
        jugador.setEquipo(document.getString("equipo"));
        return jugador;
    }
}
