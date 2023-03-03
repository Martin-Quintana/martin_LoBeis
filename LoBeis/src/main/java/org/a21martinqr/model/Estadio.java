package org.a21martinqr.model;

import org.bson.Document;
import org.bson.types.ObjectId;

import javax.print.Doc;

public class Estadio {

    private ObjectId id;
    private String nombre;
    private String ciudad;
    private String pais;
    private int capacidad;

    public Estadio() {
    }

    public Estadio(ObjectId id, String nombre, String ciudad, String pais, int capacidad) {
        this.id = id;
        this.nombre = nombre;
        this.ciudad = ciudad;
        this.pais = pais;
        this.capacidad = capacidad;
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

    public int getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }

    public Document toDocument() {
        Document document = new Document();
        document.append("nombre", nombre);
        document.append("ciudad", ciudad);
        document.append("pais", pais);
        document.append("capacidad", capacidad);
        return document;
    }

    public static Estadio fromDocument(Document document) {
        Estadio estadio = new Estadio();
        estadio.setId(document.getObjectId("_id"));
        estadio.setNombre(document.getString("nombre"));
        estadio.setCiudad(document.getString("ciudad"));
        estadio.setPais(document.getString("pais"));
        estadio.setCapacidad(document.getInteger("capacidad"));
        return estadio;
    }

    @Override
    public String toString() {
        return "Estadio{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", ciudad='" + ciudad + '\'' +
                ", pais='" + pais + '\'' +
                ", capacidad=" + capacidad +
                '}';
    }
}
