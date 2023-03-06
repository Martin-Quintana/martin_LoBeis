package org.a21martinqr.model;

import org.bson.Document;
import org.bson.types.ObjectId;

public class Jugador {

    private ObjectId id;
    private String nombre;
    private int edad;

    private String posicion;
    private String equipo;


    public Jugador() {
    }

    public Jugador(ObjectId id, String nombre, int edad, String posicion, String equipo) {
        this.id = new ObjectId();
        this.nombre = nombre;
        this.edad = edad;
        this.posicion = posicion;
        this.equipo = equipo;
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

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getEquipo() {
        return equipo;
    }

    public void setEquipo(String equipo) {
        this.equipo = equipo;
    }

    public String getPosicion() {
        return posicion;
    }

    public void setPosicion(String posicion) {
        this.posicion = posicion;
    }

    public Document toDocument() {
        Document document = new Document();
        document.append("nombre", nombre);
        document.append("edad", edad);
        document.append("posicion", posicion);
        document.append("equipo", equipo);
        return document;
    }

    public static Jugador fromDocument(Document document) {
        Jugador jugador = new Jugador();
        jugador.setId(document.getObjectId("_id"));
        jugador.setNombre(document.getString("nombre"));
        jugador.setEdad(document.getInteger("edad"));
        jugador.setPosicion(document.getString("posicion"));
        jugador.setEquipo(document.getString("equipo"));
        return jugador;
    }

    @Override
    public String toString() {
        return "Jugador{" + "id=" + id + ", nombre=" + nombre + ", edad=" + edad + ", posicion=" + posicion + ", equipo=" + equipo + '}';
    }



}
