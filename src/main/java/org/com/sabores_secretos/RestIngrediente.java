/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.com.sabores_secretos;

import org.com.sabores_secretos.entities.Ingrediente;
import org.com.sabores_secretos.dao.consultas.DAO_IngredienteImpl;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author lopez
 */
@ApplicationScoped
@Path("/ingrediente")//modificado antes ingredient ahora es ingredient
public class RestIngrediente {

    DAO_IngredienteImpl ingrediente = new DAO_IngredienteImpl();

    //metodo que comprueba si existe, si existe te dice la id que ya tiene, si no existe lo crea y te dice la id
    //curl -XPOST http://localhost:8080/sabores_secretos/servicios/ingrediente/crear/nombre_ingrediente
    @POST
    @Path("/crear/{nombre}")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response crearIngrediente(@PathParam("nombre") String nombre) {
        int clave = 0;
        try {
            int respuesta = ingrediente.existe(nombre);

            if (respuesta > 0) {
                return Response.status(Response.Status.CREATED).entity("El ingrediente " + nombre + " ya existe con la ID: " + respuesta).build();
            }
            if (respuesta < 0) {
                return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error ingrediente " + respuesta).build();
            }

            if (respuesta == 0) {
                Ingrediente i = new Ingrediente(0, nombre);
                clave = ingrediente.registrar(i);
            }
            return Response.status(Response.Status.CREATED).entity("Ingrediente Creado con ID: " + clave).build();

        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error al crear el ingrediente").build();

        }

    }

    //dandole el nombre del ingrediente lo busca y lo devuelve en la cabecera
    //curl -XGET http://localhost:8080/sabores_secretos/servicios/ingredient/leer/nombre_ingrediente
    @GET
    @Path("/leer/{nombre}")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response leerIngrediente(@PathParam("nombre") String nombre) {

        try {
            Ingrediente i = ingrediente.buscarIngrediente(nombre);
            if (i.getNombre().isEmpty()) {
                return Response.status(Response.Status.NOT_FOUND).entity("No se ha encontrado el ingrediente").build();
            } else {
                return Response.ok(i).build();
            }
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }

    }

    //curl -XGET http://localhost:8080/sabores_secretos/servicios/ingrediente/listar
    @GET
    @Path("/listar")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response listarIngredientes() {
        List<Ingrediente> lista_ingredientes;

        try {
            lista_ingredientes = ingrediente.listar();

            if (lista_ingredientes.size() < 1) {
                return Response.status(Response.Status.NOT_FOUND).entity("No hay Ingredientes").build();
            }

        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }

        return Response.ok(lista_ingredientes).build();
    }

    //curl -XDELETE http://localhost:8080/sabores_secretos/servicios/ingrediente/borrar/naranja
    @DELETE
    @Path("/borrar/{nombre}")
    public Response borrarIngrediente(@PathParam("nombre") String nombre) {

        try {
            Ingrediente i = new Ingrediente();
            i.setNombre(nombre);
            int respuesta = ingrediente.eliminar(i);
            switch (respuesta) {
                case 1:
                    return Response.status(Response.Status.OK).entity("Ingrediente borrado correctamente").build();
                case -1:
                    return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error al crear el ingrediente").build();
                case -2:
                    return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error SQL").build();
                default:
                    break;
            }

        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
        return Response.status(Response.Status.OK).build();

    }

    //curl -X PUT -H "Content-Type: application/json" -d @C:\Users\lopez\Desktop\scripts\ingrediente.json http://localhost:8080/sabores_secretos/servicios/ingrediente/modificar/naranja
    //metodo que recibe un objeto ingrediente a modificar y su nuevo nombre
    @PUT
    @Path("/modificar/{nuevo_nombre}")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response modificarIngrediente(@PathParam("nuevo_nombre") String nombre, Ingrediente i) {

        try {
            int respuesta = ingrediente.modificar(i, nombre);

            switch (respuesta) {
                case 1:
                    return Response.status(Response.Status.OK).entity("Modificado").build();
                case 0:
                    return Response.status(Response.Status.NOT_FOUND).entity("No se ha encontrado el ingrediente").build();
                case -1:
                    return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error al modificar el ingrediente").build();
                case -2:
                    return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error SQL").build();
                default:
                    break;
            }

        } catch (Exception ex) {
            Logger.getLogger(RestIngrediente.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Response.status(Response.Status.OK).build();
    }

}
