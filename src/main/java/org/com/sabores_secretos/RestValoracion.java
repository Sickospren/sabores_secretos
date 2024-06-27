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

import org.com.sabores_secretos.dao.consultas.DAO_ValoracionImpl;
import org.com.sabores_secretos.entities.Valoracion;

@ApplicationScoped
@Path("/valoracion")
public class RestValoracion {

    DAO_ValoracionImpl valoracion = new DAO_ValoracionImpl();
    
    
    //curl -XGET http://localhost:8080/sabores_secretos/servicios/valoracion/listar/1
    @GET
    @Path("/listar/{id_receta}")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response listarValoraciones(@PathParam("id_receta") int id_receta) {
        List<Valoracion> listaValoraciones;

        try {
            listaValoraciones = valoracion.listarValoracion(id_receta);
            if (listaValoraciones.size() < 1) {
                return Response.status(Response.Status.NOT_FOUND).entity("No hay valoraciones").build();
            }

        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        }

        return Response.ok(listaValoraciones).build();

    }

    //curl -X POST -H "Content-Type: application/json" -d @C:\Users\lopez\Desktop\scripts\valo.json http://localhost:8080/sabores_secretos/servicios/valoracion/crear
    @POST
    @Path("/crear")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response crearValoracion(Valoracion v) {
        try {
            int respuesta = valoracion.registrar(v);

            if (respuesta > 0) {
                return Response.status(Response.Status.CREATED).entity("valoracion creada correctamente id_valoracion: " + respuesta).build();
            } else if (respuesta == -1) {
                return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error al crear valoracion").build();
            } else if (respuesta == -2) {
                return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error SQL").build();
            }

        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error al postear la valoracion").build();

        }
        return Response.ok(Response.Status.OK).build();
    }

    //curl -X PUT -H "Content-Type: application/json" -d @C:\Users\lopez\Desktop\scripts\valo.json http://localhost:8080/sabores_secretos/servicios/valoracion/modificar/puntuacion/3
    @PUT
    @Path("/modificar/puntuacion/{nueva_puntuacion}")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response modificarPuntuacion(@PathParam("nueva_puntuacion") int nueva_puntuacion, Valoracion v) {
        Response.Status responseStatus = Response.Status.OK;
        try {
            int respuesta = valoracion.modificarPuntuacion(v, nueva_puntuacion);

            switch (respuesta) {
                case 0:
                    return Response.status(Response.Status.NOT_FOUND).entity("No se ha encontrado la valoracion").build();
                case 1:
                    return Response.status(Response.Status.OK).entity("valoracion modificada correctamente").build();
                case -1:
                    return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error al modificar la valoracion").build();
                case -2:
                    return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error SQL").build();
                default:
                    break;
            }

        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }

        return Response.status(responseStatus).build();
    }

    //curl -X PUT -H "Content-Type: application/json" -d @C:\Users\lopez\Desktop\scripts\valo.json http://localhost:8080/sabores_secretos/servicios/valoracion/modificar/comentario/exquisito
    @PUT
    @Path("/modificar/comentario/{nuevo_comentario}")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response modificarComentario(@PathParam("nuevo_comentario") String nuevo_comentario, Valoracion v) {
        Response.Status responseStatus = Response.Status.OK;

        try {
            int respuesta = valoracion.modificarComentario(v, nuevo_comentario);
            
            switch (respuesta) {
                case 0:
                    return Response.status(Response.Status.NOT_FOUND).entity("No se ha encontrado la valoracion").build();
                case 1:
                    return Response.status(Response.Status.OK).entity("valoracion modificada correctamente").build();
                case -1:
                    return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error al modificar la valoracion").build();
                case -2:
                    return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error SQL").build();
                default:
                    break;
            }

        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }

        return Response.status(responseStatus).build();
    }

    //curl -X DELETE http://localhost:8080/sabores_secretos/servicios/valoracion/borrar/7
    @DELETE
    @Path("/borrar/{id_valoracion}")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response borrarValoracion(@PathParam("id_valoracion") int id_valoracion) {
        Response.Status responseStatus = Response.Status.OK;

        try {
            Valoracion v = new Valoracion();
            v.setId_valoracion(id_valoracion);
            int respuesta = valoracion.eliminar(v);

            switch (respuesta) {
                case 0:
                    return Response.status(Response.Status.NOT_FOUND).entity("No se ha encontrado la valoracion").build();
                case 1:
                    return Response.status(Response.Status.OK).entity("valoracion borrada correctamente").build();
                case -1:
                    return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error al borrada la valoracion").build();
                case -2:
                    return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error SQL").build();
                default:
                    break;
            }
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();

        }
        return Response.status(responseStatus).build();
    }

}
