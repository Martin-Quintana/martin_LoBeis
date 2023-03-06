package org.a21martinqr.DAO;

import com.mongodb.client.ClientSession;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.a21martinqr.model.Equipo;
import org.bson.Document;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.List;

import static org.a21martinqr.model.Equipo.fromDocument;

public class DAOEquipo {

    private MongoCollection<Document> equipoCollection;

    public DAOEquipo(MongoDatabase database) {
        equipoCollection = database.getCollection("Equipos");
    }

    public DAOEquipo(MongoCollection<Document> equipoCollection) {
        this.equipoCollection = equipoCollection;
    }

    public List<Equipo> obtenerTodosLosEquipos() {
        List<Equipo> equipos = new ArrayList<>();
        for (Document document : equipoCollection.find()) {
            Equipo equipo = new Equipo();
            equipo.setId(document.getObjectId("_id"));
            equipo.setNombre(document.getString("nombre"));
            equipo.setCiudad(document.getString("ciudad"));
            equipo.setPais(document.getString("pais"));
            equipo.setEstadio(document.getString("estadio"));
            equipos.add(equipo);
        }
        return equipos;
    }

    public void agregarEquipo(Equipo equipo) {
        Document document = new Document("nombre", equipo.getNombre())
                .append("ciudad", equipo.getCiudad())
                .append("pais", equipo.getPais())
                .append("estadio", equipo.getEstadio());
        equipoCollection.insertOne(document);
        equipo.setId(document.getObjectId("_id"));
    }

    public void actualizarEquipo(Equipo equipo) {
        equipoCollection.updateOne(new Document("_id", equipo.getId()),
                new Document("$set", new Document("nombre", equipo.getNombre())
                        .append("ciudad", equipo.getCiudad())
                        .append("pais", equipo.getPais())
                        .append("estadio", equipo.getEstadio())));
    }

    public void eliminarEquipo(ObjectId equipo) {
        equipoCollection.deleteOne(new Document("_id", equipo));
    }

    public Equipo obtenerEquipoPorId(ObjectId id) {
        Document document = equipoCollection.find(new Document("_id", id)).first();
        Equipo equipo = new Equipo();
        equipo.setId(document.getObjectId("_id"));
        equipo.setNombre(document.getString("nombre"));
        equipo.setCiudad(document.getString("ciudad"));
        equipo.setPais(document.getString("pais"));
        equipo.setEstadio(document.getString("estadio"));
        return equipo;
    }

    public List<Equipo> obtenerEquiposPorNombre(String nombre) {
        List<Equipo> equipos = new ArrayList<>();
        for (Document document : equipoCollection.find( new Document("nombre", nombre)
        )) {
            Equipo equipo = new Equipo();
            List<Equipo>equipoArrayList = new ArrayList<>();
            equipo.setId(document.getObjectId("_id"));
            equipo.setNombre(document.getString("nombre"));
            equipo.setCiudad(document.getString("ciudad"));
            equipo.setPais(document.getString("pais"));
            equipo.setEstadio(document.getString("estadio"));
            equipoArrayList.add(equipo);
            equipos.add(equipo);
            System.out.println(equipoArrayList);
        }
        return equipos;
    }



}
