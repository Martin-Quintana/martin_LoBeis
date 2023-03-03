package org.a21martinqr.Controller;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import org.a21martinqr.client.MongoDBCollection;
import org.a21martinqr.model.Usuario;

import java.awt.*;
import java.util.ArrayList;

public class UsuarioController {

    private final MongoCollection<Usuario> usuarioCollection;

    public UsuarioController() {
        usuarioCollection = MongoDBCollection.getConexion().getCollection("usuario", Usuario.class);
    }

    public void insertarUsuario(Usuario usuario) {
        usuarioCollection.insertOne(usuario);
    }

    public void actualizarUsuario(Usuario usuario) {
        usuarioCollection.updateOne(Filters.eq("id", usuario.getId()), Updates.combine(
                        Updates.set("nombre", usuario.getNombre()),
                        Updates.set("edad", usuario.getEdad()),
                        Updates.set("correo", usuario.getCorreo())
                )
        );
    }

    public void eliminarUsuario(Usuario usuario) {
        usuarioCollection.deleteOne(Filters.eq("id", usuario.getId()));
    }

    public ArrayList<Usuario> obtenerUsuarios() {
        ArrayList<Usuario> usuarios = new ArrayList<>();
        usuarioCollection.find().into(usuarios);
        return usuarios;
    }

    public Usuario obtenerUsuarioPorId(String id) {
        return usuarioCollection.find(Filters.eq("id", id)).first();

    }

    public Usuario obtenerUsuarioPorNombre(String nombre) {
        return usuarioCollection.find(Filters.eq("nombre", nombre)).first();
    }

    public Usuario obtenerUsuarioPorCorreo(String correo) {
        return usuarioCollection.find(Filters.eq("correo", correo)).first();
    }

    public Usuario obtenerUsuarioPorEdad(int edad) {
        return usuarioCollection.find(Filters.eq("edad", edad)).first();

    }

    

    }
