package org.a21martinqr.model;

import org.bson.Document;
import org.bson.types.ObjectId;

public class Usuario {

    private ObjectId id;
    private String nombre;
    private int edad;
    private String correo;

    public Usuario() {
    }

    public Usuario(ObjectId id, String nombre, int edad, String correo) {
        this.id = id;
        this.nombre = nombre;
        this.edad = edad;
        this.correo = correo;
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

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public Document toDocument() {
        Document document = new Document();
        document.append("nombre", nombre);
        document.append("edad", edad);
        document.append("correo", correo);
        return document;
    }

    public static Usuario fromDocument(Document document) {
        Usuario usuario = new Usuario();
        usuario.setId(document.getObjectId("_id"));
        usuario.setNombre(document.getString("nombre"));
        usuario.setEdad(document.getInteger("edad"));
        usuario.setCorreo(document.getString("correo"));
        return usuario;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", edad=" + edad +
                ", correo='" + correo + '\'' +
                '}';
    }
}
