package org.com.sabores_secretos;

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
import org.com.sabores_secretos.dao.consultas.DAO_PasosRecetaImpl;
import org.com.sabores_secretos.entities.PasosReceta;

@ApplicationScoped
@Path("/pasosReceta")
public class RestPasosReceta {

    DAO_PasosRecetaImpl pasos = new DAO_PasosRecetaImpl();

    //metodo para devolver todos los pasos de una receta en concreto
    //curl -XGET http://localhost:8080/sabores_secretos/servicios/pasosReceta/listar/1
    @GET
    @Path("/listar/{id_receta}")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response listarPasos(@PathParam("id_receta") int id_receta) {
        List<PasosReceta> listaPasosReceta;

        try {
            listaPasosReceta = pasos.listar(id_receta);

            if (listaPasosReceta.size() < 1) {
                return Response.status(Response.Status.NOT_FOUND).entity("No hay pasos").build();
            }
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        }

        return Response.ok(listaPasosReceta).build();

    }

//curl -X POST -H "Content-Type: application/json" -d @C:\Users\lopez\Desktop\scripts\pasosReceta.json http://localhost:8080/sabores_secretos/servicios/pasosReceta/crear
    @POST
    @Path("/crear")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response crearPaso(PasosReceta p) {
        try {
            int respuesta = pasos.registrar(p);

            if (respuesta > 0) {
                return Response.status(Response.Status.CREATED).entity("paso receta creado correctamente id pasoReceta: " + respuesta).build();
            }else if (respuesta == -1) {
                return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error al crear paso receta").build();
            } else if (respuesta == -2) {
                return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error SQL").build();
            } 

        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error al crear paso receta\n" + e.getMessage()).build();

        }

        return Response.ok(Response.Status.OK).build();

    }

    //curl -XPUT http://localhost:8080/sabores_secretos/servicios/pasosReceta/modificar/1/2/cagarse%20encima
    @PUT
    @Path("/modificar/{id_receta}/{id_paso}/{nuevo}")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response modificarPaso(@PathParam("id_receta") int id_receta, @PathParam("id_paso") int id_paso, @PathParam("nuevo") String nuevo) {

        try {
            PasosReceta r = new PasosReceta();
            r.setId_receta(id_receta);
            r.setId_paso(id_paso);
            int respuesta = pasos.modificar(r, nuevo);

            switch (respuesta) {
                case 0:
                    return Response.status(Response.Status.NOT_FOUND).entity("No se ha encontrado el paso").build();
                case 1:
                    return Response.status(Response.Status.OK).entity("Paso modificado correctamente").build();
                case -1:
                    return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error al modificar el paso").build();
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
    
    //curl -XPUT http://localhost:8080/sabores_secretos/servicios/pasosReceta/modificar/imagen/1/2
    @PUT
    @Path("/modificar/imagen/{id_receta}/{id_paso}")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response modificarImagen(@PathParam("id_receta") int id_receta, @PathParam("id_paso") int id_paso, PasosReceta pasosReceta) {

        try {
            String imagen = pasosReceta.getImagen();
            PasosReceta r = new PasosReceta();
            r.setId_receta(id_receta);
            r.setId_paso(id_paso);
            int respuesta = pasos.modificarImagen(r, imagen);

            switch (respuesta) {
                case 0:
                    return Response.status(Response.Status.NOT_FOUND).entity("No se ha encontrado el paso").build();
                case 1:
                    return Response.status(Response.Status.OK).entity("Paso modificado correctamente").build();
                case -1:
                    return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error al modificar el paso").build();
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
    

    //curl -XDELETE http://localhost:8080/sabores_secretos/servicios/pasosReceta/borrar/1/3
    @DELETE
    @Path("/borrar/{id_receta}/{id_paso}")
    public Response borrarPaso(@PathParam("id_receta") int id_receta, @PathParam("id_paso") int id_paso) {
        try {
            PasosReceta p = new PasosReceta();
            p.setId_receta(id_receta);
            p.setId_paso(id_paso);

            int respuesta = pasos.eliminar(p, id_receta);
            switch (respuesta) {
                case 0:
                    return Response.status(Response.Status.NOT_FOUND).entity("No se ha encontrado el paso").build();
                case 1:
                    return Response.status(Response.Status.OK).entity("Paso borrado").build();
                case -1:
                    return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error al borrar el paso").build();
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

}
