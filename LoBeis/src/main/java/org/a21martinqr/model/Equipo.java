package org.a21martinqr.model;

import org.bson.Document;
import org.bson.types.ObjectId;

public class Equipo {

    private ObjectId id;
    private String nombre;
    private String ciudad;
    private String pais;
    private int fundacion;
    private String estadio;

    public Equipo() {
    }

    public Equipo(ObjectId id, String nombre, String ciudad, String pais, int fundacion, String estadio) {
        this.id = id;
        this.nombre = nombre;
        this.ciudad = ciudad;
        this.pais = pais;
        this.fundacion = fundacion;
        this.estadio = estadio;
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public int getFundacion() {
        return fundacion;
    }

    public void setFundacion(int fundacion) {
        this.fundacion = fundacion;
    }

    public String getEstadio() {
        return estadio;
    }

    public void setEstadio(String estadio) {
        this.estadio = estadio;
    }

    public Document toDocument() {
        Document document = new Document();
        document.append("nombre", nombre);
        document.append("ciudad", ciudad);
        document.append("pais", pais);
        document.append("fundacion", fundacion);
        document.append("estadio", estadio);
        return document;
    }

    public static Equipo fromDocument(Document document) {
        Equipo equipo = new Equipo();
        equipo.setId(document.getObjectId("_id"));
        equipo.setNombre(document.getString("nombre"));
        equipo.setCiudad(document.getString("ciudad"));
        equipo.setPais(document.getString("pais"));
        equipo.setFundacion(document.getInteger("fundacion"));
        equipo.setEstadio(document.getString("estadio"));
        return equipo;
    }

    @Override
    public String toString() {
        return "Equipo{" + "id=" + id + ", nombre=" + nombre + ", ciudad=" + ciudad + ", pais=" + pais + ", fundacion=" + fundacion + ", estadio=" + estadio + '}';
    }
}
