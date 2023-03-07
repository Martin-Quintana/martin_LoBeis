package org.a21martinqr.DAO;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.a21martinqr.model.Estadio;
import org.bson.Document;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.List;

public class DAOEstadio {

    private MongoCollection<Document> estadioCollection;

    public DAOEstadio(MongoDatabase database) {
        this.estadioCollection = database.getCollection("Estadios");
    }


    public List<Estadio> obtenerTodosLosEstadios() {
        List<Estadio> estadios = new ArrayList<>();
        for (Document document : estadioCollection.find()) {
            Estadio estadio = new Estadio();
            estadio.setId(document.getObjectId("_id"));
            estadio.setNombre(document.getString("nombre"));
            estadio.setCapacidad(document.getInteger("capacidad"));
            estadio.setCiudad(document.getString("ciudad"));
            estadio.setPais(document.getString("pais"));
            estadios.add(estadio);
        }
        return estadios;
    }

    public void agregarEstadio(Estadio estadio) {
        Document document = new Document("nombre", estadio.getNombre())
                .append("ciudad", estadio.getCiudad())
                .append("pais", estadio.getPais())
                .append("capacidad", estadio.getCapacidad());
        estadioCollection.insertOne(document);
        estadio.setId(document.getObjectId("_id"));
    }

    public void actualizarEstadio(Estadio estadio) {
        estadioCollection.updateOne(new Document("_id", estadio.getId()),
                new Document("$set", new Document("nombre", estadio.getNombre())
                        .append("ciudad", estadio.getCiudad())
                        .append("pais", estadio.getPais())
                        .append("capacidad", estadio.getCapacidad())));

    }

    public void eliminarEstadio(Estadio estadio) {
        estadioCollection.deleteOne(new Document("_id", estadio.getId()));
    }

    public Estadio obtenerEstadioPorId(ObjectId id) {
        Document document = estadioCollection.find(new Document("_id", id)).first();
        Estadio estadio = new Estadio();
        estadio.setId(document.getObjectId("_id"));
        estadio.setNombre(document.getString("nombre"));
        estadio.setCiudad(document.getString("ciudad"));
        estadio.setPais(document.getString("pais"));
        estadio.setCapacidad(document.getInteger("capacidad"));
        return estadio;
    }

    public Estadio obtenerEstadioPorNombre(String nombre) {
        Document document = estadioCollection.find(new Document("nombre", nombre)).first();
        Estadio estadio = new Estadio();
        estadio.setId(document.getObjectId("_id"));
        estadio.setNombre(document.getString("nombre"));
        estadio.setCiudad(document.getString("ciudad"));
        estadio.setPais(document.getString("pais"));
        estadio.setCapacidad(document.getInteger("capacidad"));
        return estadio;
    }

    public Estadio agruparEstadioPorCiudad(String ciudad) {
        Document document = estadioCollection.find(new Document("ciudad", ciudad)).first();
        Estadio estadio = new Estadio();
        estadio.setId(document.getObjectId("_id"));
        estadio.setNombre(document.getString("nombre"));
        estadio.setCiudad(document.getString("ciudad"));
        estadio.setPais(document.getString("pais"));
        estadio.setCapacidad(document.getInteger("capacidad"));
        return estadio;
    }
}

