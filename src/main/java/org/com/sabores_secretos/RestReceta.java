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
import java.util.logging.Level;
import java.util.logging.Logger;

import org.com.sabores_secretos.dao.consultas.DAO_RecetaImpl;
import org.com.sabores_secretos.entities.Receta;

@ApplicationScoped
@Path("/receta")
public class RestReceta {

    DAO_RecetaImpl receta = new DAO_RecetaImpl();

    //metodo que devuelve un JSON o un XML con todas las recetas de la bd
    //curl -X GET http://localhost:8080/sabores_secretos/servicios/receta/listar
    @GET
    @Path("/listar")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response listarRecetas() {
        List<Receta> lista_recetas;
        Response.Status responseStatus = Response.Status.OK;

        try {
            lista_recetas = receta.listar();
            if (lista_recetas.size() < 1) {
                return Response.status(Response.Status.NOT_FOUND).entity("No hay recetas").build();
            }

        } catch (Exception ex) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
        if (responseStatus == Response.Status.OK) {
            return Response.ok(lista_recetas).build();
        } else {
            return Response.status(responseStatus).build();
        }

    }

    //curl -X POST -H "Content-Type: application/json" -d @C:\Users\lopez\Desktop\scripts\receta.json http://localhost:8080/sabores_secretos/servicios/receta/crear
    @POST
    @Path("/crear")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response crearReceta(Receta r) {
        try {

            int respuesta = receta.registrar(r);

            if (respuesta > 0) {
                return Response.status(Response.Status.CREATED).entity("receta creada correctamente id receta: " + respuesta).build();
            } else if (respuesta == -1) {
                return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error al crear la receta").build();
            } else if (respuesta == -2) {
                return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error SQL").build();
            }
        } catch (Exception ex) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error al crear la receta " + ex.getMessage()+r.toString()).build();
        }
        return Response.ok(Response.Status.OK).build();

    }

    //curl -XDELETE http://localhost:8080/sabores_secretos/servicios/receta/borrar/8
    @DELETE
    @Path("/borrar/{id_receta}")
    public Response borrarReceta(@PathParam("id_receta") int id_receta) {
        try {
            Receta r = new Receta();
            r.setId_receta(id_receta);
            int respuesta = receta.eliminar(r);
            switch (respuesta) {
                case 0:
                    return Response.status(Response.Status.NOT_FOUND).entity("No se ha encontrado la receta").build();
                case 1:
                    return Response.status(Response.Status.OK).entity("Receta borrada").build();
                case -1:
                    return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error al borrar la receta").build();
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

    //curl -X PUT http://localhost:8080/sabores_secretos/servicios/receta/modificar/descripcion/8/Nueva%20descripcion
    @PUT
    @Path("/modificar/descripcion/{id_receta}/{nuevo}")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response modificarDescripcion(@PathParam("id_receta") int id_receta, @PathParam("nuevo") String nuevo) {
        Response.Status responseStatus = Response.Status.OK;
        
        try {
            Receta r = new Receta();
            r.setId_receta(id_receta);
            int respuesta = receta.modificarDescripcion(r, nuevo);
            switch (respuesta) {
                case 0:
                    return Response.status(Response.Status.NOT_FOUND).entity("No se ha encontrado la receta").build();
                case 1:
                    return Response.status(Response.Status.OK).entity("Receta modificada correctamente").build();
                case -1:
                    return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error al modificar la receta").build();
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

    //curl -X PUT http://localhost:8080/sabores_secretos/servicios/receta/modificar/nombreReceta/9/wazaaaaaa%20wazaaaaa
    @PUT
    @Path("/modificar/nombreReceta/{id_receta}/{nuevo}")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response modificarNombreReceta(@PathParam("id_receta") int id_receta, @PathParam("nuevo") String nuevo) {
        Response.Status responseStatus = Response.Status.OK;
        
        try {
            Receta r = new Receta();
            r.setId_receta(id_receta);
            int respuesta = receta.modificarNombreReceta(r, nuevo);
            switch (respuesta) {
                case 0:
                    return Response.status(Response.Status.NOT_FOUND).entity("No se ha encontrado la receta").build();
                case 1:
                    return Response.status(Response.Status.OK).entity("Receta modificada correctamente").build();
                case -1:
                    return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error al modificar la receta").build();
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

    //curl -X PUT http://localhost:8080/sabores_secretos/servicios/receta/modificar/tiempoReceta/9/190
    @PUT
    @Path("/modificar/tiempoReceta/{id_receta}/{nuevo}")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response modificarTiempoPreparacion(@PathParam("id_receta") int id_receta, @PathParam("nuevo") int nuevo) {
        Response.Status responseStatus = Response.Status.OK;
        try {
            Receta r = new Receta();
            r.setId_receta(id_receta);
            int respuesta = receta.modificarTiempoPreparacion(r, nuevo);
            switch (respuesta) {
                case 0:
                    return Response.status(Response.Status.NOT_FOUND).entity("No se ha encontrado la receta").build();
                case 1:
                    return Response.status(Response.Status.OK).entity("Receta modificada correctamente").build();
                case -1:
                    return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error al modificar la receta").build();
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

    //curl -X PUT http://localhost:8080/sabores_secretos/servicios/receta/modificar/numeroComensales/1/5
    @PUT
    @Path("/modificar/numeroComensales/{id_receta}/{nuevo}")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response modificarNumeroComensales(@PathParam("id_receta") int id_receta, @PathParam("nuevo") int nuevo) {
        Response.Status responseStatus = Response.Status.OK;
        try {
            Receta r = new Receta();
            r.setId_receta(id_receta);
            int respuesta = receta.modificarNumeroComensales(r, nuevo);
            switch (respuesta) {
                case 0:
                    return Response.status(Response.Status.NOT_FOUND).entity("No se ha encontrado la receta").build();
                case 1:
                    return Response.status(Response.Status.OK).entity("Receta modificada correctamente").build();
                case -1:
                    return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error al modificar la receta").build();
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

    //curl -X PUT http://localhost:8080/sabores_secretos/servicios/receta/modificar/categoria/9/Nueva%20categoria
    @PUT
    @Path("/modificar/categoria/{id_receta}/{nuevo}")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response modificarCategoria(@PathParam("id_receta") int id_receta, @PathParam("nuevo") String nuevo) {
        Response.Status responseStatus = Response.Status.OK;
        try {
            Receta r = new Receta();
            r.setId_receta(id_receta);
            int respuesta = receta.modificarCategoria(r, nuevo);
            switch (respuesta) {
                case 0:
                    return Response.status(Response.Status.NOT_FOUND).entity("No se ha encontrado la receta").build();
                case 1:
                    return Response.status(Response.Status.OK).entity("Receta modificada correctamente").build();
                case -1:
                    return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error al modificar la receta").build();
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

    //curl -X PUT http://localhost:8080/sabores_secretos/servicios/receta/modificar/origen/9/Nuevo%20origen
    @PUT
    @Path("/modificar/origen/{id_receta}/{nuevo}")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response modificarOrigen(@PathParam("id_receta") int id_receta, @PathParam("nuevo") String nuevo) {
        Response.Status responseStatus = Response.Status.OK;
        
        try {
            Receta r = new Receta();
            r.setId_receta(id_receta);
            int respuesta = receta.modificarOrigen(r, nuevo);
            switch (respuesta) {
                case 0:
                    return Response.status(Response.Status.NOT_FOUND).entity("No se ha encontrado la receta").build();
                case 1:
                    return Response.status(Response.Status.OK).entity("Receta modificada correctamente").build();
                case -1:
                    return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error al modificar la receta").build();
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

    //curl -X PUT http://localhost:8080/sabores_secretos/servicios/receta/modificar/privacidad/1/0
    @PUT
    @Path("/modificar/privacidad/{id_receta}/{nuevo}")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response modificarPrivacidad(@PathParam("id_receta") int id_receta, @PathParam("nuevo") byte nuevo) {
        Response.Status responseStatus = Response.Status.OK;
        try {
            Receta r = new Receta();
            r.setId_receta(id_receta);
            int respuesta = receta.modificarPrivacidad(r, nuevo);
            switch (respuesta) {
                case 0:
                    return Response.status(Response.Status.NOT_FOUND).entity("No se ha encontrado la receta").build();
                case 1:
                    return Response.status(Response.Status.OK).entity("Receta modificada correctamente").build();
                case -1:
                    return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error al modificar la receta").build();
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

    //curl -X PUT -H "Content-Type: application/json" -d @C:\Users\lopez\Desktop\scripts\receta.json http://localhost:8080/sabores_secretos/servicios/modificar/imagen/12
    @PUT
    @Path("/modificar/imagen/{id_receta}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response modificarImagen(@PathParam("id_receta") int id_receta, Receta receta) {
        Response.Status responseStatus = Response.Status.OK;
        try {
            Receta r = new Receta();
            r.setId_receta(id_receta);
            String nuevo = receta.getImagen();
            int respuesta = this.receta.modificarImagen(r, nuevo);
            switch (respuesta) {
                case 0:
                    return Response.status(Response.Status.NOT_FOUND).entity("No se ha encontrado la receta").build();
                case 1:
                    return Response.status(Response.Status.CREATED).entity("Receta modificada correctamente").build();
                case -1:
                    return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error al modificar la receta").build();
                case -2:
                    return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error SQL").build();
                default:
                    break;
            }
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        }
        return Response.status(responseStatus).build();
    }

}
